package com.unloadbrain.games.rockpaperscissors.cli;

import com.unloadbrain.games.rockpaperscissors.core.Game;
import com.unloadbrain.games.rockpaperscissors.core.GameEngine;
import com.unloadbrain.games.rockpaperscissors.core.player.HumanPlayer;
import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.player.RandomBotPlayer;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.security.SecureRandom;
import java.util.Scanner;

import static com.unloadbrain.games.rockpaperscissors.core.ConsoleLogger.info;
import static java.nio.charset.StandardCharsets.UTF_8;

public class CliApp {

    private final Scanner scanner;
    private final Game game;
    private final Player player1;
    private final Player player2;

    public CliApp(Scanner scanner, Game game, Player player1, Player player2) {
        this.scanner = scanner;
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
    }

    public static void main(String[] args) {
        var scanner = new Scanner(System.in, UTF_8);
        var player1 = new HumanPlayer("You!",
                (Weapons availableWeapons) -> promptHumanPlayerWeaponChoice(availableWeapons, scanner)
        );
        var player2 = new RandomBotPlayer("Computer", new SecureRandom());
        var game = new Game(new GameEngine(new RockPaperScissorsWeapons()), player1, player2);

        new CliApp(scanner, game, player1, player2).run();
    }

    public void run() {
        do {
            printBanner();

            game.play(player1);
            info("You played " + player1.getChosenWeapon());

            game.play(player2);
            info("Computer played " + player2.getChosenWeapon());

            game.findWinner().ifPresentOrElse(
                    winner -> info("Winner is " + winner.getName()),
                    () -> info("Match is draw")
            );

            info("\n");

        } while (wantToContinuePlayPrompt(scanner));
    }

    private void printBanner() {
        info("==================================================");
        info("               Rock Paper Scissors                ");
        info("==================================================");
        info("\n");
    }

    private static Weapon promptHumanPlayerWeaponChoice(Weapons weapons, Scanner scanner) {
        Weapon weapon;
        while ((weapon = weapons.get(scanner.nextLine())) == null) {
            info("Invalid weapon. Please choose again.");
        }
        return weapon;
    }

    private boolean wantToContinuePlayPrompt(Scanner scanner) {
        info("Want to play again? y/N ");
        var yesOrNo = scanner.nextLine().trim();
        return yesOrNo.equalsIgnoreCase("Y");
    }
}
