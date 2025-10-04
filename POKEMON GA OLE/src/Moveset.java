
public class Moveset
{
	private String pokename;
	private int attackDmg;
	private String moveType;
	
	public Moveset(String pokename, int attackDmg, String moveType)
	{
		this.pokename = pokename;
		this.attackDmg = attackDmg;
		this.moveType = moveType;
	}
	
	public String getPokename()
	{
		return pokename;
	}
	
	public int getAttackDmg()
	{
		return attackDmg;
	}
	
	public String getMoveType()
	{
		return moveType;
	}
	
}
