
public class PokeBall
{
	private String ballname;
    private double catchChance;

    public PokeBall(String ballname,double catchChance)
    {
        this.ballname = ballname;
        this.catchChance = catchChance;
    }

    public String getBallname() {
        return ballname;
    }

    public double getCatchChance() {
        return catchChance;
    }
}
