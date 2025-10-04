import java.util.ArrayList;
import java.util.List;

public class Scoreboard
{
	private static String playerName;
	private static int score;
	private Player player;
	private List<Scoreboard>scoreboard;
	private static Game game;
	
	public Scoreboard(String playerName,int score)
	{
		this.playerName=playerName;
		this.score=score;
	}
	
	public static List<Scoreboard> getScoreboard()
	{
		List<Scoreboard>scoreboard=new ArrayList<>();
		//scoreboard.add(new Scoreboard (playerName,score));
		return scoreboard;
	}
	
	public static void setScoreboard(List<Scoreboard>scoreboard)
	{
		
    }
	
	public String getPlayerName()
	{
		return playerName;
	}
	
	public int getScore()
	{
		return score;
	}
	public void setScore(int score)
	{
		this.score=score;
	}
}
