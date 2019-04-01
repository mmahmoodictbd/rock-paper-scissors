package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.core.weapon.RockPaperScissorsWeapons;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RandomBotPlayerTest {

    @Test
    public void shouldReturnRandomChosenWeapon() {

        // Given
        Weapons weapons = new RockPaperScissorsWeapons();
        Player player = new RandomBotPlayer("Alex", weapons);

        // When
        player.chooseWeapon();

        // Then
        assertNotNull(player.getChosenWeapon());
        assertTrue(weapons.getWeaponList().contains(player.getChosenWeapon()));
    }

    @Test
    public void shouldReturnPlayerName() {

        // Given When
        Weapons weapons = new RockPaperScissorsWeapons();
        Player player = new RandomBotPlayer("Alex", weapons);

        // Then
        assertEquals("Alex", player.getName());
    }

}