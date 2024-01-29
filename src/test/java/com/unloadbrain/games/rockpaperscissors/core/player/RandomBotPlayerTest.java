package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.cli.RockPaperScissorsWeapons;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomBotPlayerTest {

    @Test
    void shouldReturnRandomChosenWeapon() {
        // Given
        var weapons = new RockPaperScissorsWeapons();
        var player = new RandomBotPlayer("Alex", new SecureRandom());

        // When
        player.chooseWeapon(weapons);

        // Then
        assertNotNull(player.getChosenWeapon());
        assertTrue(weapons.getWeaponList().contains(player.getChosenWeapon()));
    }

    @Test
    void shouldReturnPlayerName() {
        // Given When
        var player = new RandomBotPlayer("Alex", new SecureRandom());

        // Then
        assertEquals("Alex", player.getName());
    }
}