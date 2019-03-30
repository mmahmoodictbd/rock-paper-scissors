package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.core.player.HumanPlayer;
import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.player.RandomBotPlayer;
import com.unloadbrain.games.rockpaperscissors.core.weapon.RockPaperScissorsLizardSpockWeapons;
import com.unloadbrain.games.rockpaperscissors.core.weapon.RockPaperScissorsWeapons;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void test1() {

        Player player1 = new HumanPlayer("Alex");
        Player player2 = new HumanPlayer("Bart");

        Weapons weapons = new RockPaperScissorsWeapons();

        Game game = new Game(Arrays.asList(player1, player2), weapons);
        game.play(player1, weapons.get("Rock"));
        game.play(player2, weapons.get("Paper"));
        Player winner = game.getWinner().get();
        assertEquals("Bart", winner.getName());
    }


    @Test
    public void test2() {

        Player player1 = new HumanPlayer("Alex");
        Player player2 = new HumanPlayer("Bart");

        Weapons weapons = new RockPaperScissorsLizardSpockWeapons();

        Game game = new Game(Arrays.asList(player1, player2), weapons);
        game.play(player1, weapons.get("Spock"));
        game.play(player2, weapons.get("Rock"));
        Player winner = game.getWinner().get();
        assertEquals("Alex", winner.getName());
    }

    @Test
    public void test3() {

        Weapons weapons = new RockPaperScissorsWeapons();

        Player player1 = new HumanPlayer("Alex");
        Player player2 = new RandomBotPlayer("Bart", weapons);

        Game game = new Game(Arrays.asList(player1, player2), weapons);
        game.play(player1, weapons.get("Rock"));
        game.play(player2, player2.getChosenWeapon());
        Player winner = game.getWinner().get();
        assertEquals("Bart", winner.getName());
    }

}