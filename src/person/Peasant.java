package person;

import behavior.CoordXY;

import java.util.ArrayList;

public class Peasant extends PersonBase{

    private static final int HEALTH = 500;
    private static final int POWER = 30;
    private static final int AGILITY = 30;
    private static final int DEFENCE = 0;
    private static final int DISTANCE = 1;
    private static final int FULL_BAG = 12;

    private final int bag;
    public Peasant(String name, CoordXY pos) {
        super(name, 0, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, pos);
        bag = 0;
    }
    @Override
    public void step(ArrayList<PersonBase> enemies, ArrayList<PersonBase> friends) {

    }

    @Override
    public String getInfo() {
        return "Крестьянин";
    }

    @Override
    public String toString() {
        return "[Крестьянин]{" +
                name +
                ", ❤=" + health +
                ", " + position +
                '}';
    }
}
