import java.util.HashMap;

public class PokemonDex {
    private HashMap<String, Pokemon> dex;

    public PokemonDex() {
        dex = new HashMap<>();

        // Grass-type Pokémon
        dex.put("Bulbasaur", new Pokemon("Bulbasaur", 100, 20, 2, "Grass"));
        dex.put("Venusaur", new Pokemon("Venusaur", 180, 35, 4, "Grass"));
        dex.put("Leafeon", new Pokemon("Leafeon", 130, 25, 3, "Grass"));
        dex.put("Roserade", new Pokemon("Roserade", 150, 30, 4, "Grass"));
        dex.put("Celebi", new Pokemon("Celebi", 70, 100, 5, "Grass")); 

        // Water-type Pokémon
        dex.put("Squirtle", new Pokemon("Squirtle", 110, 20, 1, "Water"));
        dex.put("Blastoise", new Pokemon("Blastoise", 200, 30, 4, "Water"));
        dex.put("Gyarados", new Pokemon("Gyarados", 190, 40, 4, "Water"));
        dex.put("Greninja", new Pokemon("Greninja", 160, 75, 5, "Water"));
        dex.put("Milotic", new Pokemon("Milotic", 170, 30, 3, "Water"));

        // Fire-type Pokémon
        dex.put("Charmander", new Pokemon("Charmander", 100, 25, 1, "Fire"));
        dex.put("Charizard", new Pokemon("Charizard", 150, 50, 4, "Fire"));
        dex.put("Arcanine", new Pokemon("Arcanine", 180, 40, 3, "Fire"));
        dex.put("Blaziken", new Pokemon("Blaziken", 170, 45, 4, "Fire"));
        dex.put("Entei", new Pokemon("Entei", 300, 40, 5, "Fire")); 

        // Extra Pokémon
        dex.put("Mewtwo", new Pokemon("Mewtwo", 500, 40, 5, "Psycho"));
    }

    // Getter
    public Pokemon getPokemon(String name) {
        return dex.get(name);
    }

    // Setter / Updater
    public void setPokemon(String name, Pokemon pokemon) {
        dex.put(name, pokemon);
    }

    
}
