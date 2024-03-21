package behavior;

import java.util.Random;

public class HeroesNames {
    private static Random rnd;
    private static String[] names = {"Антон", "Елена", "Николай", "Максим", "Геннадий", "Юлия",
            "Александер", "Алексей", "Наталья", "Кристина", "Владислав", "Артур", "Тимофей"
    };
    static {
        rnd = new Random();
    }

    public static String getName(int index)
    {
        if (index >= names.length)
            index = 0;
        return names[index];
    }

    public static String getRandomName()
    {
        return names[rnd.nextInt(names.length)];
    }
}