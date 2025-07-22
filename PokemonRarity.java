import java.util.Random;

public class PokemonRarity {
    private static final double[][] ranges = {
        {0.00, 0.25},
        {0.20, 0.40},
        {0.35, 0.55},
        {0.40, 0.75},
        {0.70, 0.99}
    };

    private static final Random random = new Random();

    public static double getCatchDifficulty(int rarity) {
        if (rarity >= 1 && rarity <= 5) {
            double min = ranges[rarity - 1][0];
            double max = ranges[rarity - 1][1];
            return min + (max - min) * random.nextDouble();
        }
        return 1.0; // fallback for invalid rarity
    }
}


