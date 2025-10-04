import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Player
{
	private String playerName;
	private int score;
	private List<Pokemon>team;
	
	public Player(String playerName)
	{
		this.playerName=playerName;
		this.score=0;
		this.team=new ArrayList<Pokemon>();
	}
	
	public String getPlayerName()
	{
		return playerName;
	}
	public void setPlayerName(String player)
	{
		playerName=player;
	}
	
	public int getScore()
	{
		return score;
	}
	public void setScore(int score)
	{
		this.score=score;
	}
	
	public List<Pokemon>getTeam()
	{
		return team;
	}
	
	//Add or Remove Pokemon to/from Team
	public void addPokemon(Pokemon pokemon)
	{
			team.add(pokemon);
	}
	public void removePokemon(Pokemon pokemon)
	{
		team.remove(pokemon);
	}
}
