package com.unloadbrain.games.rockpaperscissors.cli;

import com.unloadbrain.games.rockpaperscissors.core.Game;
import com.unloadbrain.games.rockpaperscissors.core.player.HumanPlayer;
import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.player.RandomBotPlayer;
import com.unloadbrain.games.rockpaperscissors.core.weapon.RockPaperScissorsWeapons;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CliApp {

    public static void main(String[] args) {

        CliApp cliApp = new CliApp();
        final Scanner scanner = new Scanner(System.in);

        Weapons weapons = new RockPaperScissorsWeapons();

        while (true) {

            cliApp.printBanner();

            Player player1 = new HumanPlayer("You!", cliApp.promptHumanPlayerWeaponChoice(scanner, weapons));
            Player player2 = new RandomBotPlayer("Computer", weapons);
            Game game = new Game(Arrays.asList(player1, player2), weapons);

            game.play(player1, player1.getChosenWeapon());
            cliApp.log("You played " + player1.getChosenWeapon());

            game.play(player2, player2.getChosenWeapon());
            cliApp.log("Computer played " + player2.getChosenWeapon());

            if (game.findWinner().isPresent()) {
                Player winner = game.findWinner().get();
                cliApp.log("Winner is " + winner.getName());
            } else {
                cliApp.log("Match is draw");
            }

            cliApp.log("\n");

            if (!cliApp.wantToContinuePlayPrompt(scanner)){
                 break;
            }
        }
    }

    private void printBanner() {
        log("==================================================");
        log("               Rock Paper Scissors                ");
        log("==================================================");
        log("\n");
    }

    private Callable promptHumanPlayerWeaponChoice(Scanner scanner, Weapons weapons) {
        return (Callable<Weapon>) () -> {

            Weapon weapon;
            while (true) {
                log("Choose from available weapons: " + weapons.getWeaponList());
                weapon = weapons.get(scanner.nextLine());
                if (weapon != null) {
                    break;
                }
                log("Invalid weapon, select from: " + weapons.getWeaponList());
            }

            return weapon;
        };
    }

    private void log(String message) {
        System.out.println(message);
    }

    private boolean wantToContinuePlayPrompt(Scanner scanner){

        log("Want to play again? y/N ");

        String yesOrNo = scanner.nextLine();
        if (yesOrNo!= null) {
            if (yesOrNo.equalsIgnoreCase("Y")) {
                return true;
            } else if (yesOrNo.equalsIgnoreCase("N")){
                return false;
            }
        }

        return wantToContinuePlayPrompt(scanner);
    }
}
