package person;

import behavior.CoordXY;

public class Robber extends InfantryBase {

    private static final int HEALTH = 1000;
    private static final int POWER = 70;
    private static final int AGILITY = 60;
    private static final int DEFENCE = 10;
    private static final int DISTANCE = 1;

    public Robber(String name, CoordXY pos)
    {
        super(name, 2, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, pos);
    }


    @Override
    public String toString() {
        return String.format("[Разбойник] %s", name  + " " + position.toString());
    }


    @Override
    public String getInfo() {
        return "Разбойник " + history;
    }
}