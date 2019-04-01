package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.core.weapon.BasicWeapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnChosenWeapon() {

        // Given
        Weapon weapon = new BasicWeapon("Weapon", "W");
        Player player = new HumanPlayer("Alex", () -> weapon);

        // When
        player.chooseWeapon();

        // Then
        assertEquals(weapon, player.getChosenWeapon());
    }

    @Test
    public void shouldReturnPlayerName() {

        // Given When
        Player player = new HumanPlayer("Alex");

        // Then
        assertEquals("Alex", player.getName());
    }

    @Test
    public void shouldThrowExceptionWhenCallbackHasException() {

        // Given

        Player player = new HumanPlayer("Alex", () -> {
            throw new RuntimeException("Some exception");
        });

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Fatal error.");

        // When
        player.chooseWeapon();

        // Then
        // Expect test to be passed.
    }

}