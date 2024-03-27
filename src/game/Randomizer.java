package game;

import java.util.Random;

public class Randomizer {
    private static Randomizer instance;
    private final Random random;

    private Randomizer() {
        random = new Random();
    }

    public static Randomizer getInstance() {
        if (instance == null) {
            instance = new Randomizer();
        }
        return instance;
    }

    public int nextInt(int max) {
        return random.nextInt(max);
    }

    public int nextIntMinMax(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public double nextDouble() {
        return random.nextDouble();
    }
}
