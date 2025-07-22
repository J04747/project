import java.util.Scanner;
import java.util.Random ;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PokemonDex dex = new PokemonDex();

        System.out.println("Welcome to Pokemon World");
        System.out.println("-------------------------------");
        System.out.println("Get two Pokemon to play with in your journey!");
        System.out.println("[1] Squirtle* \n[2] Bulbasaur** \n[3] Charmander**");

        int fpick, spick;

        while (true) {
            System.out.print("First Pokemon is: ");
            fpick = input.nextInt();
            System.out.print("Second Pokemon is: ");
            spick = input.nextInt();

            if (fpick < 1 || fpick > 3 || spick < 1 || spick > 3) {
                System.out.println(" Invalid choice. Please choose numbers between 1 and 3.");
            } else if (fpick == spick) {
                System.out.println(" Cannot pick the same Pokemon twice.");
            } else {
                break;
            }
        }

        String[] names = { "", "Squirtle", "Bulbasaur", "Charmander" };
        Pokemon first = dex.getPokemon(names[fpick]);
        Pokemon second = dex.getPokemon(names[spick]);

        System.out.println("\nFirst Pokemon Partner");
        System.out.println("---------------------------");
        System.out.println(first.toString());

        System.out.println("\nSecond Pokemon Partner");
        System.out.println("---------------------------");
        System.out.println(second.toString());

        // Choose enemy
        int enemy;
        System.out.println("--------------------------");
        System.out.println("Choose your enemy");
        System.out.println("[1] Leafeon \n  Difficulty: ** \n[2] Blastoise \n  Difficulty: *** \n[3] Entei\n  Difficulty: ****");

        while (true) {
            System.out.print("Enemy is: ");
            enemy = input.nextInt();
            if (enemy < 1 || enemy > 3) {
                System.out.println("Invalid choice. Please choose numbers between 1 and 3.");
            } else {
                break;
            }
        }

        String[] bossList = { "", "Leafeon", "Blastoise", "Entei" };
        Pokemon boss = dex.getPokemon(bossList[enemy]);

        // Choose which Pokémon to fight first
        int choice;
        System.out.println("Which Pokemon should fight first?");
        System.out.println("[1] " + first.getName());
        System.out.println("[2] " + second.getName());

        while (true) {
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            if (choice == 1 || choice == 2) break;
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        if (choice == 2) {
            Pokemon temp = first;
            first = second;
            second = temp;
        }

        Battle b1 = new Battle(first, boss);
        Battle b2 = new Battle(second, boss);
        PokemonBall game = new PokemonBall();

        input.nextLine(); // Clear newline before Enter prompt

       // First Battle: first vs boss
        while (!b1.isBattleOver()) {
            System.out.println("Press Enter to roll the dice...");
            input.nextLine();
            System.out.println("-------------------------");
            System.out.println(b1.attack());
            System.out.println();
        }

        // If first Pokemon loses, second enters battle
        if (!b2.isBattleOver()) {
            System.out.println(first.getName() + " has fainted! " + second.getName() + " enters the battle!");

            while (!b2.isBattleOver()) {
                System.out.println("Press Enter to roll the dice...");
                input.nextLine();
                System.out.println("-------------------------");
                System.out.println(b2.attack());
                System.out.println();
            }
        }

        if (b1.getWinner() != null && b2.getWinner() != null) {
            System.out.println(b2.getWinner());
            //Extra  Rare Boss battle 

            if (b1.callcatch()) {
              if (game.catchPokemon(b1.getBoss())){
                Random rng = new Random();
                double roll = rng.nextDouble();

                if (roll > 0.75) {
                System.out.println(" A wild Mewtwo appears!!! This is a legendary battle!");
                System.out.println("Mewtwo: 'You dare challenge me? Prepare yourself!'");

                Pokemon Big_boss = dex.getPokemon("Mewtwo");

                Battle finalBattle = new Battle(second, Big_boss);

                while (!finalBattle.isBattleOver()) {
                    System.out.println("Press Enter to roll the dice against Mewtwo...");
                    input.nextLine();
                    System.out.println("-------------------------");
                    System.out.println(finalBattle.attack());
                    System.out.println();
                }

                if (finalBattle.getWinner() != null) {
                    System.out.println("Legendary Battle Result: " + finalBattle.getWinner());

                    if (finalBattle.getWinner().equals(second.getName())) {
                        System.out.println(" Congratulations! You defeated the legendary Mewtwo!!!");
                    } else {
                        System.out.println(" Mewtwo is too powerful... Better luck next time!");
                    }
                }
            } else {
                System.out.println("No legendary Pokémon appears this time. Your journey continues...");
            }
        }
        }
    }
 }
}