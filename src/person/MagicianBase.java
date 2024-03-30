package person;

import behavior.CoordXY;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class MagicianBase extends PersonBase {

    private static final int COST_HEALED = 10;
    private static final int MANA_RECOVERY = 5;
    private static final int MANA_TO_HEAL = 3;

    protected int mana;
    protected int maxMana;
    private int resurrectMana;
    private PersonBase respawnTarget;

    protected MagicianBase(String name, int priority, int health, int power, int agility, int defence, int distance, int mana, CoordXY pos)
    {
        super(name, priority, health, power, agility, defence, distance, pos);
        this.mana = mana;
        this.maxMana = mana;
        this.resurrectMana = mana / 2;
        this.respawnTarget = null;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends)
    {
        history = "";

        if (health <= 0)
            return;
        mana = Math.min(mana + MANA_RECOVERY, maxMana);

        if (isWaitResurrection(friends))
            return;

        if (getNumOfDead(friends, mana >= resurrectMana) > 3)
        {
            beginResurrection(friends);
        } else {
            doHeal(friends);
        }
    }

    private void beginResurrection(ArrayList<PersonBase> friends)
    {
        PersonBase p = null;
        int max = -1;
        for (PersonBase person : friends)
        {
            if (person.getHealth() < 0 && mana >= resurrectMana)
            {
                p = person;
                break;
            }
            if (person.getHealth() == 0 && max < person.getPriority())
            {
                p = person;
                max = person.getPriority();
            }
        }

        if (p != null)
        {
            respawnTarget = p;
            if (mana >= resurrectMana)
            {
                doResurrection(p);
            } else {
                respawnTarget.health = -1;
                history = String.format(" восстанавливает ману для воскрешения %s", respawnTarget);
            }
        }
    }
    private boolean isWaitResurrection(ArrayList<PersonBase> friends)
    {
        if (respawnTarget == null || respawnTarget.getHealth() >= 0)
        {
            respawnTarget = null;
            return false;
        }
        if (mana >= resurrectMana)
        {
            doResurrection(respawnTarget);
        } else {
            history = String.format(" восстанавливает ману для воскрешения %s", respawnTarget);
        }
        return true;
    }
    private void doResurrection(PersonBase person)
    {
        if (respawnTarget.getHealth() <= 0)
        {
            person.healed(respawnTarget.getMaxHealth());
            mana -= resurrectMana;
            history = String.format(" воскресил %s", respawnTarget);
        } else {
            history = String.format(" не нашел погибшего для воскрешения!");
        }
        respawnTarget = null;
    }
    private void doHeal(ArrayList<PersonBase> friends)
    {
        int min = Integer.MAX_VALUE;
        PersonBase p = null;
        for (PersonBase friend : friends)
        {
            int hp = friend.getHealth() * 100 / friend.getMaxHealth();
            if (hp > 0 && hp < min) {
                min = hp;
                p = friend;
            }
        }
        if (p != null && min < 100)
        {
            int hp = p.getHealth();
            int needMana = (p.getMaxHealth() - hp) / MANA_TO_HEAL;
            int n = Math.min(mana, Math.min(needMana, COST_HEALED));
            mana -= n;
            p.healed(n * MANA_TO_HEAL);
            history = String.format(" вылечил %s на %d пунктов здоровья", p, p.getHealth()-hp);
        } else {
            history = String.format(" пропускает ход.");
        }
    }

    private int getNumOfDead(ArrayList<PersonBase> friends, boolean isReservation) {
//        return (int) friends.stream().filter(n -> n.getHealth() <= 0).count();
        int count = 0;
        for (PersonBase friend : friends) {
            if (friend.getHealth() == 0)
                count++;
            else if (friend.getHealth() < 0 && isReservation)
                count++;
        }
        return count;
    }
    @Override
    public int getDamage(int damage)
    {
        int hp = super.getDamage(damage);
        if (health <= 0)
        {
            if (respawnTarget != null)
            {
                if (respawnTarget.getHealth() < 0)
                    respawnTarget.health = 0;
                respawnTarget = null;
            }
        }
        return hp;
    }
}