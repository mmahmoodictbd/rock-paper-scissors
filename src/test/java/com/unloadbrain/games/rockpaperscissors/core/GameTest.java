package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.cli.RockPaperScissorsWeapons;
import com.unloadbrain.games.rockpaperscissors.core.exception.GameFinishedException;
import com.unloadbrain.games.rockpaperscissors.core.exception.PlayerAlreadyMadeMoveException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownPlayerException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownWeaponException;
import com.unloadbrain.games.rockpaperscissors.core.player.HumanPlayer;
import com.unloadbrain.games.rockpaperscissors.core.weapon.BasicWeapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GameTest {

    private Weapons weapons;
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        weapons = new RockPaperScissorsWeapons();
        engine = new GameEngine(weapons);
    }

    @Test
    void throwExceptionWhenMakeMoveAfterGameIsFinished() {
        // Given
        var player1 = mock(HumanPlayer.class);
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        var player2 = mock(HumanPlayer.class);
        when(player2.getName()).thenReturn("Bart");
        when(player2.getChosenWeapon()).thenReturn(weapons.get("Paper"));

        var game = new Game(engine, player1, player2);

        game.play(player1);
        game.play(player2);

        // When and Then
        assertThrows(GameFinishedException.class, () -> game.play(player2));
    }

    @Test
    void throwExceptionWhenUnknownPlayerMakeMove() {
        // Given
        var unknownPlayer = mock(HumanPlayer.class);
        when(unknownPlayer.getName()).thenReturn("Unknown");
        when(unknownPlayer.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        var player1 = new HumanPlayer("Alex");
        var player2 = new HumanPlayer("Bart");
        var game = new Game(engine, player1, player2);

        // When and Then
        var exception = assertThrows(UnknownPlayerException.class, () -> game.play(unknownPlayer));
        assertTrue(exception.getMessage().contains("Player is not part of this game."));
    }

    @Test
    void throwExceptionWhenUnknownWeaponUsed() {
        // Given
        var player1 = mock(HumanPlayer.class);
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(new BasicWeapon("UnknownWeapon", "XYZ"));

        var player2 = new HumanPlayer("Bart");
        var game = new Game(engine, player1, player2);

        // When and Then
        var exception = assertThrows(UnknownWeaponException.class, () -> game.play(player1));
        assertTrue(exception.getMessage().contains("Unknown weapon."));
    }

    @Test
    void throwExceptionWhenPlayerAlreadyMadeMove() {
        // Given
        var player1 = mock(HumanPlayer.class);
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        var player2 = new HumanPlayer("Bart");
        var game = new Game(engine, player1, player2);

        // When and Then
        game.play(player1);
        var exception = assertThrows(PlayerAlreadyMadeMoveException.class, () -> game.play(player1));
        assertTrue(exception.getMessage().contains("Player already made his/her move."));
    }

    @Test
    void findWinnerOnceAllPlayerMadeTheirMove() {
        // Given
        var player1 = mock(HumanPlayer.class);
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        var player2 = mock(HumanPlayer.class);
        when(player2.getName()).thenReturn("Bart");
        when(player2.getChosenWeapon()).thenReturn(weapons.get("Scissors"));

        var game = new Game(engine, player1, player2);
        game.play(player1);
        game.play(player2);

        // When
        var winner = game.findWinner().orElse(null);

        // Then
        assertNotNull(winner);
        assertEquals("Alex", winner.getName());
    }

    @Test
    void shouldReturnTrueWhenAllPlayersMadeTheirMoves() {
        // Given
        var weapons = new RockPaperScissorsWeapons();
        var engine = new GameEngine(weapons);

        var player1 = mock(HumanPlayer.class);
        doNothing().when(player1).chooseWeapon(weapons);
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        var player2 = mock(HumanPlayer.class);
        doNothing().when(player2).chooseWeapon(weapons);
        when(player2.getName()).thenReturn("Bart");
        when(player2.getChosenWeapon()).thenReturn(weapons.get("Scissors"));

        var game = new Game(engine, player1, player2);

        // When
        game.play(player1);
        game.play(player2);

        // Then
        assertTrue(game.isFinished());
    }

    @Test
    void shouldReturnFalseWhenAllPlayersNotMadeTheirMovesYet() {
        // Given
        var weapons = new RockPaperScissorsWeapons();
        var engine = new GameEngine(weapons);

        var player1 = mock(HumanPlayer.class);
        doNothing().when(player1).chooseWeapon(weapons);
        when(player1.getName()).thenReturn("Alex");
        when(player1.getChosenWeapon()).thenReturn(weapons.get("Rock"));

        var player2 = mock(HumanPlayer.class);
        doNothing().when(player2).chooseWeapon(weapons);
        when(player2.getName()).thenReturn("Bart");
        when(player2.getChosenWeapon()).thenReturn(weapons.get("Scissors"));

        var game = new Game(engine, player1, player2);

        // When
        game.play(player1);

        // Then
        assertFalse(game.isFinished());
    }

    @Test
    void shouldReturnId() {
        // Given
        var weapons = new RockPaperScissorsWeapons();
        var engine = new GameEngine(weapons);

        // When
        var game = new Game(engine, mock(HumanPlayer.class), mock(HumanPlayer.class));

        // Then
        assertNotNull(game.getId());
    }
}