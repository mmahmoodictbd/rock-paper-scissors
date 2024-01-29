package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.cli.RockPaperScissorsWeapons;
import com.unloadbrain.games.rockpaperscissors.core.weapon.BasicWeapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HumanPlayerTest {

    @Test
    void shouldReturnChosenWeapon() {
        // Given
        var player = new HumanPlayer("Alex", (availableWeapons) -> availableWeapons.get("Rock"));

        // When

        player.chooseWeapon(new RockPaperScissorsWeapons());

        // Then
        assertEquals(new BasicWeapon("Rock", "FIST"), player.getChosenWeapon());
    }

    @Test
    void shouldReturnPlayerName() {
        // Given When
        var player = new HumanPlayer("Alex");

        // Then
        assertEquals("Alex", player.getName());
    }

    @Test
    void shouldThrowExceptionWhenCallbackHasException() {
        var exception = assertThrows(RuntimeException.class, () -> {

            // Given
            var player = new HumanPlayer("Alex", (weapons) -> {
                throw new RuntimeException("Some exception");
            });

            // When
            player.chooseWeapon(new RockPaperScissorsWeapons());

        });

        // Then
        assertTrue(exception.getMessage().contains("Some exception"));
    }

}