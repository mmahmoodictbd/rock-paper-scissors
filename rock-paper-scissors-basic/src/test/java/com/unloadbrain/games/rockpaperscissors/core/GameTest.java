package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.basic.BasicWeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.basic.BasicWeapons;
import com.unloadbrain.games.rockpaperscissors.core.player.HumanPlayer;
import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void test() {


        Player player1 = new HumanPlayer("Alex");
        Player player2 = new HumanPlayer("Bart");

        Weapons weapons = new BasicWeapons();
        BasicWeaponBattleRules rules = new BasicWeaponBattleRules(weapons);
        weapons.setWeaponBattleRules(rules);

        Game game = new Game(Arrays.asList(player1, player2), weapons);
        game.play(player1, weapons.get("Rock"));
        game.play(player2, weapons.get("Paper"));
        Player winner = game.getWinner().get();
        assertEquals("Bart", winner.getName());
    }

}