public class Stage {
    private String stageName;
    
    public Stage(String stageName)
    {
        this.stageName = stageName;
    }
    
    public String getStageName()
    {
    	return stageName;
    }
    
    public Pokemon generateStagePokemon(int stageselection)
    {
    	String name = null,type = null,rarity = null;
    	Moveset[] moves = null;
    	int hp = 0,def = 0;
    	switch(stageselection)
    	{
    	case 0://Volcano
    		name = "Charmander";
    		hp = 200; def=43;
    		type="Fire"; rarity="Rare";
    		moves = new Moveset[]
    				{
    				new Moveset("Scratch", 40, "Normal"),
    		        new Moveset("Fire Fang", 65, "Fire")		
    				};
    		break;
    	case 1://Ocean
    		name = "Squirtle";
    		hp = 190; def=65;
    		type="Water"; rarity="Rare";
    		moves = new Moveset[]
    				{
    				new Moveset("Water Gun", 40, "Water"),
    		        new Moveset("Water Pulse", 60, "Water")		
    				};
    		break;
    	case 2://Forest
    		name = "Bulbasaur";
    		hp = 215; def=49;
    		type="Grass,Posion"; rarity="Rare";
    		moves = new Moveset[]
    				{
    				new Moveset("Tackle", 40, "Normal"),
    		        new Moveset("Vine Whip", 45, "Grass")		
    				};
    		break;
    	}
    	return new Pokemon(name,hp,def,type,rarity,moves);
    }
    
}
