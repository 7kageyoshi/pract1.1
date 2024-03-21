package person;

import behavior.CoordXY;

import java.util.ArrayList;

public abstract class InfantryBase extends PersonBase {

    protected int level;

    protected InfantryBase(String name, int priority, int health, int power, int agility, int defence, int distance, CoordXY pos)
    {
        super(name, priority, health, power, agility, defence, distance, pos);
        level = 1;
    }

    private boolean isMoved(CoordXY pos, ArrayList<PersonBase> persons)
    {
        for (PersonBase p : persons)
        {
            if (p.position.equal(pos))
                return false;
        }
        return true;
    }

    private void move(PersonBase target, ArrayList<PersonBase> friends)
    {
        int[] px = {1, 0, -1, 0};
        int[] py = {0, 1, 0, -1};

        CoordXY newPos = new CoordXY(position.getX(),position.getY());
        int minIdx = -1;
        float minDist = Float.MAX_VALUE;
        for (int i = 0; i < 4; i++)
        {
            newPos.setXY(position.getX()+px[i], position.getY()+py[i]);
            if (isMoved(newPos, friends))
            {
                float dist = position.fastDistance(target.position, px[i], py[i]);
                if (dist < minDist)
                {
                    minIdx = i;
                    minDist = dist;                }
            }
        }
        if (minIdx == -1.0f)
            return;

        position.increment(px[minIdx], py[minIdx]);

        history = "move to " + position;
    }

    private void attack(PersonBase target, boolean isMoved)
    {
        int damage = getRound(power, 10) + (power / 10) * level;
        boolean critical = (this.agility/3) >= rnd.nextInt(100);
        if (critical)
        {
            damage *= 2.0f;
        }
        if (isMoved)
            damage /= 2;

        int res = target.getDamage(damage);
        history = history + "attack " + target.name + " set " + res + "damage";
    }

    @Override
    public void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends)
    {
        history = "";
        PersonBase target = this.findNearestPerson(enemies);
        if (health <= 0 || target == null)
            return;

        if (position.distanceTo(target.position) < 1.5f)
        {
            attack(target, false);
        } else {
            move(target, friends);
            if (position.distanceTo(target.position) < 1.5f)
            {
                attack(target, true);
            }
        }

    }
}