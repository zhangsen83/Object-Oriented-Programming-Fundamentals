import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pokebase
{
	public static List<Pokemon> getWildPokemons()
	{
		List<Pokemon> WildPokemons = new ArrayList<>();
		
		//Pokemon(name,health,defense,type,rarity,moves)
		WildPokemons.add(new Pokemon("Charizard", 150, 90, "Fire","Rare",
	            new Moveset[]
	            {
	                new Moveset("Blast Burn", 50, "Fire"),
	                new Moveset("Flame Thrower", 80, "Fire")
				}
		));
		
		WildPokemons.add(new Pokemon("Blastoise", 133, 101, "Water","Rare",
		            new Moveset[]
		            {
		                new Moveset("Hydro Pump", 40, "Water"),
		                new Moveset("Hydro Cannon", 85, "Water")
					}
		));
		
		WildPokemons.add(new Pokemon("Venusaur", 134, 84, "Grass, Posion","Rare",
		            new Moveset[]
		            {
		                new Moveset("Leaf Strom", 40, "Grass"),
		                new Moveset("Solar Beam", 70, "Grass")
					}
		));
		
		WildPokemons.add(new Pokemon("Exeggutor", 134, 78, "Grass, Psychic","Uncommon",
		            new Moveset[]
		            {
		                new Moveset("Seed Bomb", 75, "Grass"),
		                new Moveset("Confusion", 50, "Psychic")
					}
		));
		
		WildPokemons.add(new Pokemon("Mankey", 78, 40, "Fighting","Common",
		            new Moveset[]
		            {
		                new Moveset("Scratch", 30, "Normal"),
		                new Moveset("Close Comat", 90, "Fighting")
					}
		));
		
		WildPokemons.add(new Pokemon("Sandshrew", 66, 55, "Ground","Common",
		            new Moveset[]
		            {
		                new Moveset("Roll Out", 30, "Rock"),
		                new Moveset("Slash", 70, "Normal")
					}
		));
		
		WildPokemons.add(new Pokemon("Banette", 64, 65, "Ghost","Rare",
		            new Moveset[]
		            {
		                new Moveset("Shadow Sneak", 40, "Ghost"),
		                new Moveset("Shadow Ball", 80, "Ghost")
					}
		));
		
		WildPokemons.add(new Pokemon("Pikachu", 35, 40, "Electric","Common",
		            new Moveset[]
		            {
		                new Moveset("Quick Attack", 40, "Normal"),
		                new Moveset("Discharge", 80, "Electric")
					}
		));
		
		WildPokemons.add(new Pokemon("Dragonite", 91, 95, "Dragon, Flying","Rare",
		            new Moveset[]
		            {
		                new Moveset("Twister", 40, "Dragon"),
		                new Moveset("Dragon Tail", 60, "Dragon")
					}
		));
		
		return WildPokemons;

		//Pokemon randomPokemon = pokemon.getRandomPokemon(WildPokemons);
	}
	/*
	public static Pokemon getRandomPokemon(List<Pokemon> pokemonList)
    {
        Random random = new Random();
        int index = random.nextInt(pokemonList.size());
        return pokemonList.get(index);
    }
    */
}
