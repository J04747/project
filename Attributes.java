public class Attributes {
    static String[] type = { "Fire", "Water", "Grass","Psycho" };
    static double[][] chart = new double[4][4];

    // Automatically initialize the chart when the class loads
    static {
        initializeChart();
    }

    public static void initializeChart() {
        chart[0] = new double[]{1, 0.5, 2,1};   // Fire vs [Fire, Water, Grass]
        chart[1] = new double[]{2, 1, 0.5,1};   // Water vs ...
        chart[2] = new double[]{0.5, 2, 1,1};   // Grass vs ...
        chart[3] = new double[]{2,2,2,2}; // Psycho vs ...
    }

    public static double findMultiplier(String attacker, String defender) {
        int attackerIndex = -1;
        int defenderIndex = -1;

        for (int i = 0; i < type.length; i++) {
            if (type[i].equalsIgnoreCase(attacker)) {
                attackerIndex = i;
            }
            if (type[i].equalsIgnoreCase(defender)) {
                defenderIndex = i;
            }
        }

        // Safety check
        if (attackerIndex == -1 || defenderIndex == -1) {
            System.out.println("Invalid type! Attacker: " + attacker + ", Defender: " + defender);
            return 1.0; // Neutral if type not found
        }

        return chart[attackerIndex][defenderIndex];
    }
}


