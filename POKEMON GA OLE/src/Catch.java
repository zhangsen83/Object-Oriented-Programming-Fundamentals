public class Catch
{	
	Player player;
	public Catch()
	{	
		
	}
	
	public boolean catchSuccess(Pokemon pokemon, PokeBall pokeball)
	{
		double chance=pokeball.getCatchChance();
		return Math.random()<chance;
	}
}
