package person;

import behavior.CoordXY;

import java.util.ArrayList;

public abstract class ShooterBase extends PersonBase {

    protected int ammo;
    protected int level;
    protected int effectiveDistance;

    protected ShooterBase(String name, int priority, int health, int power, int agility, int defence, int distance, int ammo, int effectiveDistance, CoordXY pos)
    {
        super(name, priority, health, power, agility, defence, distance, pos);
        this.ammo = ammo;
        this.effectiveDistance = effectiveDistance;
        this.level = 1;
    }

    protected void shot(PersonBase target)
    {
        ammo--;
        float dist = position.distanceTo(target.position);
        int damage = getRound(power, 10) + (power / 10) * level;
        if (dist > effectiveDistance)
            damage *= 0.5f;
        else if (dist < effectiveDistance)
            damage *= 1.2f;

        boolean critical = (this.agility/3) >= rnd.nextInt(100);
        if (critical)
        {
            damage *= 2.0f;
        }
        int res = target.getDamage(damage);
    }

    @Override
    public void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends)
    {
        if (health <= 0 || ammo <= 0)
        {
            return;
        }
        PersonBase target = this.findNearestPerson(enemies);
        if (target != null)
        {
            shot(target);
        }
    }

}