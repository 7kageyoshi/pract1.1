package person;

import behavior.CoordXY;
public class Sniper extends ShooterBase {

    private static final int HEALTH = 800;
    private static final int POWER = 30;
    private static final int AGILITY = 30;
    private static final int DEFENCE = 5;
    private static final int DISTANCE = 16;
    private static final int ARROWS = 12;
    private static final int EFFECTIVE_DISTANCE = 4;


    public Sniper(String name, CoordXY pos)
    {
        super(name, 3, HEALTH, POWER, AGILITY, DEFENCE, DISTANCE, ARROWS, EFFECTIVE_DISTANCE, pos);
    }

    @Override
    public String toString()
    {
        return String.format("[Снайпер] %s", name  + " " + position.toString());
    }

    @Override
    public String getInfo() {
        return "Снайпер";
    }
}