package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.core.exception.GameFinishedException;
import com.unloadbrain.games.rockpaperscissors.core.exception.PlayerAlreadyMadeMoveException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownPlayerException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownWeaponException;
import com.unloadbrain.games.rockpaperscissors.core.player.HumanPlayer;
import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.weapon.BasicWeapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.RockPaperScissorsWeapons;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwExceptionWhenMakeMoveAfterGameIsFinished() {

        // Given

        Weapons weapons = new RockPaperScissorsWeapons();
        GameEngine engine = new GameEngine(weapons);

        Player player1 = mock(HumanPlayer.class);
        doNothing().when(player1).chooseWeapon();
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        Player player2 = mock(HumanPlayer.class);
        doNothing().when(player2).chooseWeapon();
        when(player2.getName()).thenReturn("Bart");
        when(player2.getChosenWeapon()).thenReturn(weapons.get("Paper"));

        Game game = new Game(engine, player1, player2);

        game.play(player1);
        game.play(player2);

        thrown.expect(GameFinishedException.class);
        thrown.expectMessage("Game is finished.");

        // When
        game.play(player2);

        // Then
        // Expect test to be passed.
    }

    @Test
    public void throwExceptionWhenUnknownPlayerMakeMove() {

        // Given

        Weapons weapons = new RockPaperScissorsWeapons();
        GameEngine engine = new GameEngine(weapons);

        Player unknownPlayer = mock(HumanPlayer.class);
        doNothing().when(unknownPlayer).chooseWeapon();
        when(unknownPlayer.getName()).thenReturn("Unknown");
        when(unknownPlayer.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        Player player1 = new HumanPlayer("Alex");
        Player player2 = new HumanPlayer("Bart");
        Game game = new Game(engine, player1, player2);

        thrown.expect(UnknownPlayerException.class);
        thrown.expectMessage("Player is not part of this game.");

        // When
        game.play(unknownPlayer);

        // Then
        // Expect test to be passed.
    }

    @Test
    public void throwExceptionWhenUnknownWeaponUsed() {

        // Given

        Weapons weapons = new RockPaperScissorsWeapons();
        GameEngine engine = new GameEngine(weapons);

        Player player1 = mock(HumanPlayer.class);
        doNothing().when(player1).chooseWeapon();
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(new BasicWeapon("UnknownWeapon", "XYZ"));

        Player player2 = new HumanPlayer("Bart");
        Game game = new Game(engine, player1, player2);

        thrown.expect(UnknownWeaponException.class);
        thrown.expectMessage("Unknown weapon.");

        // When
        game.play(player1);

        // Then
        // Expect test to be passed.
    }

    @Test
    public void throwExceptionWhenPlayerAlreadyMadeMove() {

        // Given

        Weapons weapons = new RockPaperScissorsWeapons();
        GameEngine engine = new GameEngine(weapons);

        Player player1 = mock(HumanPlayer.class);
        doNothing().when(player1).chooseWeapon();
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        Player player2 = new HumanPlayer("Bart");
        Game game = new Game(engine, player1, player2);

        thrown.expect(PlayerAlreadyMadeMoveException.class);
        thrown.expectMessage("Player already made his/her move.");

        // When
        game.play(player1);
        game.play(player1);

        // Then
        // Expect test to be passed.
    }

    @Test
    public void findWinnerOnceAllPlayerMadeTheirMove() {

        // Given

        Weapons weapons = new RockPaperScissorsWeapons();
        GameEngine engine = new GameEngine(weapons);

        Player player1 = mock(HumanPlayer.class);
        doNothing().when(player1).chooseWeapon();
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        Player player2 = mock(HumanPlayer.class);
        doNothing().when(player2).chooseWeapon();
        when(player2.getName()).thenReturn("Bart");
        when(player2.getChosenWeapon()).thenReturn(weapons.get("Scissors"));


        Game game = new Game(engine, player1, player2);
        game.play(player1);
        game.play(player2);

        // When
        Player winner = game.findWinner().get();

        // Then
        assertEquals("Alex", winner.getName());
    }

    @Test
    public void shouldReturnTrueWhenAllPlayersMadeTheirMoves() {

        // Given

        Weapons weapons = new RockPaperScissorsWeapons();
        GameEngine engine = new GameEngine(weapons);

        Player player1 = mock(HumanPlayer.class);
        doNothing().when(player1).chooseWeapon();
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        Player player2 = mock(HumanPlayer.class);
        doNothing().when(player2).chooseWeapon();
        when(player2.getName()).thenReturn("Bart");
        when(player2.getChosenWeapon()).thenReturn(weapons.get("Scissors"));

        Game game = new Game(engine, player1, player2);

        // When
        game.play(player1);
        game.play(player2);

        // Then
        assertTrue(game.isFinished());
    }

    @Test
    public void shouldReturnFalseWhenAllPlayersNotMadeTheirMovesYet() {

        // Given

        Weapons weapons = new RockPaperScissorsWeapons();
        GameEngine engine = new GameEngine(weapons);

        Player player1 = mock(HumanPlayer.class);
        doNothing().when(player1).chooseWeapon();
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        Player player2 = mock(HumanPlayer.class);
        doNothing().when(player2).chooseWeapon();
        when(player2.getName()).thenReturn("Bart");
        when(player2.getChosenWeapon()).thenReturn(weapons.get("Scissors"));

        Game game = new Game(engine, player1, player2);

        // When
        game.play(player1);

        // Then
        assertFalse(game.isFinished());
    }

}