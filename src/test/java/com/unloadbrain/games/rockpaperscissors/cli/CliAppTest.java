package com.unloadbrain.games.rockpaperscissors.cli;

import com.unloadbrain.games.rockpaperscissors.core.Game;
import com.unloadbrain.games.rockpaperscissors.core.GameEngine;
import com.unloadbrain.games.rockpaperscissors.core.player.HumanPlayer;
import com.unloadbrain.games.rockpaperscissors.core.player.RandomBotPlayer;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CliAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testCliApp() {
        testCliApp("Rock", "Scissors", "Winner is You!");
        testCliApp("Scissors", "Paper", "Winner is You!");
        testCliApp("Paper", "Rock", "Winner is You!");

        testCliApp("Scissors", "Rock", "Winner is Computer");
        testCliApp("Paper", "Scissors", "Winner is Computer");
        testCliApp("Rock", "Paper", "Winner is Computer");

        testCliApp("Rock", "Rock", "Match is draw");
        testCliApp("Paper", "Paper", "Match is draw");
        testCliApp("Scissors", "Scissors", "Match is draw");
    }

    void testCliApp(String humanPlayerChosenWeapon, String computerChosenWeapon, String expectedOutput) {
        // Given
        provideInput("N\n");
        var scanner = new Scanner(System.in, UTF_8);

        var weapons = new RockPaperScissorsWeapons();
        var player1 = new HumanPlayer("You!", (Weapons availableWeapons) -> weapons.get(humanPlayerChosenWeapon));
        var player2 = new RandomBotPlayer("Computer", new SecureRandom()) {
            @Override
            public Weapon getChosenWeapon() {
                return weapons.get(computerChosenWeapon);
            }
        };
        var game = new Game(new GameEngine(weapons), player1, player2);

        // When
        new CliApp(scanner, game, player1, player2).run();

        // Then
        var consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains(String.format("You played %s", humanPlayerChosenWeapon)));
        assertTrue(consoleOutput.contains(String.format("Computer played %s", computerChosenWeapon)));
        assertTrue(consoleOutput.contains(expectedOutput));
    }

    void provideInput(String data) {
        var testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
