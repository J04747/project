import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PokemonBall {
    private final List<Catch> balls = new ArrayList<>();
    private final Random rng = new Random();

    public PokemonBall() {
        balls.add(new Catch("Poke Ball",   0.6, 1.00));
        balls.add(new Catch("Great Ball",  0.2, 0.40));
        balls.add(new Catch("Ultra Ball",  0.15, 0.75));
        balls.add(new Catch("Master Ball", 0.05, 1.00));
    }

    public Catch getRandomBall() {
        double roll = rng.nextDouble();
        double cumulative = 0.0;

        for (Catch ball : balls) {
            cumulative += ball.getGetBallRate();
            if (roll < cumulative) {
                return ball;
            }
        }

        return balls.get(balls.size() - 1);
    }

    // ✅ Accept a Pokemon object as input
    public boolean catchPokemon(Pokemon pokemon) {
        int rarity = pokemon.getRarity();  
        Catch chosen = getRandomBall();
        double chance = PokemonRarity.getCatchDifficulty(rarity); 
        boolean success = chance < chosen.getCatchingRate();

        System.out.printf(
            "You got !%s!  \n %s\n",
            chosen.getName(),
            success ? "!Caught!" : "Escaped!"
        );
        return success;
    }
}


