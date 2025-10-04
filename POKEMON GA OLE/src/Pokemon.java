import java.util.List;
import java.util.Random;

public class Pokemon
{
	private String name;
	private int health;
	private int defense;
	private String type;
	private String rarity;
	private Moveset[] moves;
	//private Pokebase pokemonlist;
	//private List <Pokemon> pokemonlist;
	
	public Pokemon(String name, int health, int defense, String type, String rarity, Moveset[] moves)
	{
		this.name=name;
		this.health=health;
        this.defense = defense;
        this.type = type;
        this.rarity = rarity;
        this.moves=moves;
	}
	
	public String getName()
	{
		return name;
	}
	public int gethealth()
	{
		return health;
	}
	public int getdefense()
	{
		return defense;
	}
	
	
	public String getType()
	{
		return type;
	}
	public String getRarity()
	{
		return rarity;
	}
	
	public Moveset[] getMoves()
	{
		return moves;
	}
	
	public Pokemon getRandomPokemon(List<Pokemon> pokemonList)
    {
        Random random = new Random();
        int index = random.nextInt(pokemonList.size());
        return pokemonList.get(index);
    }
}
