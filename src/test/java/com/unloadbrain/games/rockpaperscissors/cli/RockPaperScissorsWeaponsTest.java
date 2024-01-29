package com.unloadbrain.games.rockpaperscissors.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RockPaperScissorsWeaponsTest {

    @Test
    void getWeaponsShouldReturn3BasicWeapons() {
        var weapons = new RockPaperScissorsWeapons();
        assertEquals(3, weapons.getWeaponList().size());
    }

    @Test
    void getWeaponBattleRulesShouldNotReturnNull() {
        var weapons = new RockPaperScissorsWeapons();
        assertNotNull(weapons.getWeaponBattleRules());
    }

    @Test
    void isExistShouldReturnTrueFalseWhenWeaponMatchedByLabel() {
        var weapons = new RockPaperScissorsWeapons();
        assertTrue(weapons.isExist(new com.unloadbrain.games.rockpaperscissors.core.weapon.BasicWeapon("Rock", "FIST")));
        assertFalse(weapons.isExist(new com.unloadbrain.games.rockpaperscissors.core.weapon.BasicWeapon("UnknownWeapon", "FIST")));
    }

    @Test
    void shouldReturnWeaponByLabel() {
        var weapons = new RockPaperScissorsWeapons();
        assertNotNull(weapons.get("Rock"));
        assertNull(weapons.get("UnknownWeapon"));
    }

    @Test
    void shouldReturnWinnerWeapon() {
        var weapons = new RockPaperScissorsWeapons();
        assertTrue(weapons.findWinnerWeapon(weapons.get("Rock"), weapons.get("Paper")).isPresent());
    }

    @Test
    void shouldReturnEmptyWhenNoWinnerWeapon() {
        var weapons = new RockPaperScissorsWeapons();
        assertFalse(weapons.findWinnerWeapon(weapons.get("Rock"), weapons.get("Rock")).isPresent());
    }

    @Test
    void shouldReturnSameAvailableWeapons() {
        var weapons = new RockPaperScissorsWeapons();
        assertEquals(weapons.getWeaponList(), weapons.getWeaponBattleRules().getAvailableWeapons().getWeaponList());
    }

    @Test
    void shouldReturnAllWhoBeatWhoRules() {
        var weapons = new RockPaperScissorsWeapons();
        assertEquals(3, weapons.getWeaponBattleRules().getWhoBeatWhoRules().size());
    }
}