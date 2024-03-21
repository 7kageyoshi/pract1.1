package person;

import behavior.CoordXY;

import java.util.ArrayList;

public abstract class MagicianBase extends PersonBase {

    protected int mana;
    protected int maxMana;

    protected MagicianBase(String name, int priority, int health, int power, int agility, int defence, int distance, int mana, CoordXY pos)
    {
        super(name, priority, health, power, agility, defence, distance, pos);
        this.mana = mana;
        this.maxMana = mana;
    }

    @Override
    public void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends) {

    }

}