import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Game {
    private Player player;
    private Pokemon pokemon;
    private Stage stage;
    private Battle battle;
    private Random random;
    private Scanner scanner;
    private Catch pokecatch;
    private PokeBall pokeball;
    private List<Scoreboard> scoreboard;
    private List<Pokemon> WildPokemons;

    public Game() {
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.scoreboard = new ArrayList<>(); // Initialize scoreboard
    }

    public void startGame() {
        while (true) {
            JTextField text1 = new JTextField();
            Object[] inputdialog = {"Welcome to Pokemon Ga-Ole!", "Enter your Player Name:", text1};

            ImageIcon rawLogo = new ImageIcon("src/img/GaOleLogo.png");
            ImageIcon icon = new ImageIcon(rawLogo.getImage().getScaledInstance(90, 50, Image.SCALE_SMOOTH));

            UIManager.put("OptionPane.okButtonText", "START");
            UIManager.put("OptionPane.cancelButtonText", "QUIT");

            int start = JOptionPane.showConfirmDialog(null, inputdialog, "Pokemon Ga-Ole", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
            if (start == JOptionPane.OK_OPTION) {
                String playername = text1.getText();
                if (playername.isEmpty()) {
                    continue;
                } else {
                    player = new Player(playername);

                    while (true) {
                        icon = new ImageIcon(rawLogo.getImage().getScaledInstance(170, 70, Image.SCALE_SMOOTH));
                        String[] buttonTxt = {"Volcano", "Ocean", "Forest", "Quit"};
                        int stageselection = JOptionPane.showOptionDialog(null, "Welcome to Pokemon Ga-Ole: " + playername + "!\nPlease Select Stage.\n", "Pokemon Ga-Ole",
                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon, buttonTxt, buttonTxt[0]);

                        if (stageselection == 0 || stageselection == 1 || stageselection == 2) {
                            stage = new Stage(buttonTxt[stageselection]);
                            pokemon = stage.generateStagePokemon(stageselection);

                            UIManager.put("OptionPane.okButtonText", "Catch");
                            UIManager.put("OptionPane.cancelButtonText", "Back");
                            int starter = JOptionPane.showConfirmDialog(null, "You found:\n " + pokemon.getName(), "Pokemon Ga-Ole",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

                            if (starter == JOptionPane.OK_OPTION) {
                                while (true) {
                                    JTextField prediction = new JTextField();
                                    Object[] chooseball = {"To choose Pokeball type,\nPlease guess a Number from 1 to 10.\n", prediction};

                                    UIManager.put("OptionPane.okButtonText", "GUESS");
                                    UIManager.put("OptionPane.cancelButtonText", "QUIT");
                                    int selectball = JOptionPane.showConfirmDialog(null, chooseball, "Pokemon Ga-Ole", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

                                    if (selectball == JOptionPane.OK_OPTION) {
                                        if (prediction.getText().isEmpty()) {
                                            continue;
                                        } else {
                                            if (isNumber(prediction.getText())) {
                                                int guess = Integer.parseInt(prediction.getText());
                                                int diff;
                                                if (guess >= 1 && guess <= 10) {
                                                    int guessNum = (int) (10 * Math.random());
                                                    if (guessNum > guess) {
                                                        diff = guessNum - guess;
                                                        if (diff <= 2) {
                                                            pokeball = new PokeBall("Great Ball", 0.7);
                                                        } else {
                                                            pokeball = new PokeBall("Normal Ball", 0.5);
                                                        }
                                                    } else if (guessNum < guess) {
                                                        diff = guess - guessNum;
                                                        if (diff <= 2) {
                                                            pokeball = new PokeBall("Great Ball", 0.7);
                                                        } else {
                                                            pokeball = new PokeBall("Normal Ball", 0.5);
                                                        }
                                                    } else {
                                                        pokeball = new PokeBall("Ultra Ball", 0.9);
                                                    }

                                                    pokecatch = new Catch();
                                                    if (pokecatch.catchSuccess(pokemon, pokeball)) {
                                                        player.addPokemon(pokemon);
                                                        while (true) {
                                                            String[] actions = {"Battle", "Your Team", "Scoreboard", "Quit"};
                                                            int aftercatch = JOptionPane.showOptionDialog(null, "You got the " + pokeball.getBallname() + ".\n" + "Your Catch on " + pokemon.getName() + " was successful, Trainer" + "!\nDo You want to Continue?\n", "Pokemon Ga-Ole",
                                                                    JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon, actions, actions[0]);

                                                            if (aftercatch == 0) { // Battle
                                                                try {
                                                                    for (int i = 0; i < player.getTeam().size(); i++) {
                                                                        pokemon = player.getTeam().get(i);
                                                                    }
                                                                    Object[] btn = {pokemon.getName()};
                                                                    int response = JOptionPane.showOptionDialog(null, "Select your Pokemon to fight.\n", "Pokemon Ga-Ole",
                                                                            JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon, btn, btn[0]);
                                                                    if (response == 0) {
                                                                        WildPokemons = new ArrayList<>();
                                                                        Pokemon playerPokemon = player.getTeam().get(response);
                                                                        List<Pokemon> pokebase = Pokebase.getWildPokemons();
                                                                        Pokemon opponentPokemon = pokemon.getRandomPokemon(pokebase);

                                                                        // Start the battle and track its duration
                                                                        long startTime = System.currentTimeMillis();
                                                                        battle = new Battle();
                                                                        battle.startBattle(scanner, this, playerPokemon, opponentPokemon);
                                                                        long endTime = System.currentTimeMillis();
                                                                        long duration = endTime - startTime;
                                                                        boolean playerWon = true; // or false, depending on battle outcome

                                                                        scoreCal(playerPokemon, opponentPokemon, duration, playerWon);

                                                                        // Update scoreboard in file
                                                                        updateScoreInFile();
                                                                    } else {
                                                                        System.exit(response);
                                                                    }
                                                                } catch (Exception e) {
                                                                    JOptionPane.showMessageDialog(null, "An error occurred during the battle. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            } else if (aftercatch == 1) { // View Team
                                                                String[] back = {"Back"};
                                                                if (player.getTeam().isEmpty()) {
                                                                    JOptionPane.showOptionDialog(null, "You have No Pokemon yet.\n", "Pokemon Ga-Ole",
                                                                            JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon, back, back[0]);
                                                                } else {
                                                                    for (Pokemon pokemon : player.getTeam()) {
                                                                        Object[] attributes = {"Name: " + pokemon.getName(), "HP: " + pokemon.gethealth(),
                                                                                "DEF: " + pokemon.getdefense(), "Type: " + pokemon.getType(),
                                                                                "Rarity: " + pokemon.getRarity()};
                                                                        JOptionPane.showOptionDialog(null, attributes, "Pokemon Ga-Ole",
                                                                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon, back, back[0]);
                                                                    }
                                                                }
                                                            } else if (aftercatch == 2) { // View Scoreboard
                                                                String[] back = {"Back"};
                                                                if (scoreboard.isEmpty()) {
                                                                    JOptionPane.showOptionDialog(null, "No Score available yet.\n", "Pokemon Ga-Ole",
                                                                            JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon, back, back[0]);
                                                                } else {
                                                                    List<Scoreboard> sortedScoreboard = new ArrayList<>(scoreboard);
                                                                    Collections.sort(sortedScoreboard, new Comparator<Scoreboard>() {
                                                                        @Override
                                                                        public int compare(Scoreboard s1, Scoreboard s2) {
                                                                            return Integer.compare(s2.getScore(), s1.getScore()); // Descending order
                                                                        }
                                                                    });

                                                                    StringBuilder sb = new StringBuilder();
                                                                    for (Scoreboard data : sortedScoreboard) {
                                                                        sb.append("Name: ").append(data.getPlayerName())
                                                                              .append(", Score: ").append(data.getScore())
                                                                              .append("\n");
                                                                    }
                                                                    JOptionPane.showMessageDialog(null, "Scoreboard:\n" + sb.toString(), "Pokemon Ga-Ole", JOptionPane.PLAIN_MESSAGE);
                                                                }
                                                            } else {
                                                                System.exit(aftercatch);
                                                            }
                                                        }
                                                    } else {
                                                        String[] btn = {"Try Again"};
                                                        JOptionPane.showOptionDialog(null, "You got the " + pokeball.getBallname() + ".\nBut your catch on " + pokemon.getName() + " was Unsuccessful.\nTry Again.", "Pokemon Ga-Ole",
                                                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon, btn, btn[0]);
                                                        continue;
                                                    }
                                                } else if (!isNumber(prediction.getText())) {
                                                    continue;
                                                }
                                            }
                                        }
                                    } else {
                                        System.exit(selectball);
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (start == JOptionPane.CANCEL_OPTION) {
                System.exit(start);
            }
        }
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void scoreCal(Pokemon playerPokemon, Pokemon opponentPokemon, long duration, boolean playerWon) {
        int score = 0;

        if (playerWon) {
            score += 100; // Base score for a win
            if (duration < 30000) { // Less than 30 seconds
                score += 50; // Bonus for a short battle
            } else if (duration < 60000) { // Less than 1 minute
                score += 20; // Small bonus
            }
        } else {
            score -= 50; // Penalty for losing
        }

        // Update the scoreboard
        boolean found = false;
        for (Scoreboard s : scoreboard) {
            if (s.getPlayerName().equals(player.getPlayerName())) {
                s.setScore(s.getScore() + score);
                found = true;
                break;
            }
        }

        if (!found) {
            scoreboard.add(new Scoreboard(player.getPlayerName(), score));
        }


        System.out.println("You score: " + score);
        JOptionPane.showMessageDialog(null, "Score updated: " + score);
    }

    private void updateScoreInFile() {
        List<Scoreboard> sortedScoreboard = new ArrayList<>(scoreboard);
        Collections.sort(sortedScoreboard, new Comparator<Scoreboard>() {
            @Override
            public int compare(Scoreboard s1, Scoreboard s2) {
                return Integer.compare(s2.getScore(), s1.getScore()); // Descending order
            }
        });

        // Read existing scores from file
		List<String> existingScores = new ArrayList<>();
		File file = new File("scores.txt");
		if (file.exists()) {
		    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            existingScores.add(line);
		        }
		    } catch (IOException e) {
		        JOptionPane.showMessageDialog(null, "Error reading the score file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}

		// Write updated scores to file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
		    for (String existingScore : existingScores) {
		        writer.write(existingScore);
		        writer.newLine();
		    }
		    for (Scoreboard data : sortedScoreboard) {
		        writer.write("Player: " + data.getPlayerName() + " - Score: " + data.getScore());
		        writer.newLine();
		    }
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(null, "Error writing to the score file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
}
