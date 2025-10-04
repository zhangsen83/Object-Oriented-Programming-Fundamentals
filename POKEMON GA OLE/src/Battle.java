import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Random rand;

    public Battle() {
        this.rand = new Random();
    }

    public void startBattle(Scanner scanner, Game game, Pokemon playerPokemon, Pokemon opponentPokemon) {
        System.out.println("\nBattle started between " + playerPokemon.getName() + " and " + opponentPokemon.getName());
        System.out.println("\n" + playerPokemon.getName() + " with HP: " + playerPokemon.gethealth() + "\n"
                + opponentPokemon.getName() + " with HP: " + opponentPokemon.gethealth());

        long startTime = System.currentTimeMillis(); // Start time of the battle

        while (playerPokemon.gethealth() > 0 && opponentPokemon.gethealth() > 0) {
            // Player turn
            System.out.println("Choose a skill:");
            for (int i = 0; i < playerPokemon.getMoves().length; i++) {
                Moveset moves = playerPokemon.getMoves()[i];
                System.out.println((i + 1) + ". " + moves.getPokename() + " (Power: " + moves.getAttackDmg() + ")");
            }
            int moveSelection = scanner.nextInt() - 1;
            scanner.nextLine(); // consume newline
            Moveset selectedMove = playerPokemon.getMoves()[moveSelection];

            // Calculate damage
            int damage = selectedMove.getAttackDmg();
            opponentPokemon = new Pokemon(opponentPokemon.getName(), opponentPokemon.gethealth() - damage + (damage * (opponentPokemon.getdefense() / 100)), opponentPokemon.getdefense(),
                    opponentPokemon.getType(), opponentPokemon.getRarity(), opponentPokemon.getMoves());
            System.out.println(playerPokemon.getName() + " used " + selectedMove.getPokename() + " on "
                    + opponentPokemon.getName() + "!");
            System.out.println(opponentPokemon.getName() + "\nHP: " + opponentPokemon.gethealth() + "\n");

            if (opponentPokemon.gethealth() <= 0) {
                System.out.println(opponentPokemon.getName() + " has been defeated!");
                long endTime = System.currentTimeMillis(); 
                long duration = endTime - startTime; 

                return;
            }

            // Opponent turn
            Moveset opponentSkill = opponentPokemon.getMoves()[rand.nextInt(opponentPokemon.getMoves().length)];
            int opponentDamage = opponentSkill.getAttackDmg();
            playerPokemon = new Pokemon(playerPokemon.getName(), playerPokemon.gethealth() - opponentDamage + (opponentDamage * (playerPokemon.getdefense() / 100)), playerPokemon.getdefense(),
                    playerPokemon.getType(), playerPokemon.getRarity(), playerPokemon.getMoves());
            System.out.println(opponentPokemon.getName() + " used " + opponentSkill.getPokename() + " on " + playerPokemon.getName() + "!");
            System.out.println(playerPokemon.getName() + "\nHP: " + playerPokemon.gethealth() + "\n");

            if (playerPokemon.gethealth() <= 0) {
                System.out.println(playerPokemon.getName() + " has been defeated!");
                long endTime = System.currentTimeMillis(); 
                long duration = endTime - startTime; 
                game.scoreCal(playerPokemon, opponentPokemon, duration, false); // Player lost
                return;
            }
        }
    }
}
