package com.unloadbrain.games.rockpaperscissors.core.weapon;

import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownWeaponException;
import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.rule.WhoBeatWhoRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RockPaperScissorsWeaponsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getWeaponsShouldReturn3BasicWeapons() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        assertEquals(3, weapons.getWeaponList().size());
    }

    @Test
    public void getWeaponBattleRulesShouldNotReturnNull() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        assertTrue(weapons.getWeaponBattleRules() != null);
    }

    @Test
    public void addWeaponShouldAbleToAddWeaponsInList() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        weapons.addWeapon(new BasicWeapon("NewWeapon", "NW"));
        assertEquals(4, weapons.getWeaponList().size());
    }

    @Test
    public void shouldAbleToSetWeaponBattleRules() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        WeaponBattleRules rules = new RockPaperScissorsWeapons.RockPaperScissorsWeaponBattleRules(weapons);
        weapons.setWeaponBattleRules(rules);
        assertEquals(rules, weapons.getWeaponBattleRules());
    }

    @Test
    public void isExistShouldReturnTrueFalseWhenWeaponMatchedByLabel() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        assertTrue(weapons.isExist(new BasicWeapon("Rock", "FIST")));
        assertFalse(weapons.isExist(new BasicWeapon("UnknownWeapon", "FIST")));
    }

    @Test
    public void shouldReturnWeaponByLabel() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        assertNotNull(weapons.get("Rock"));
        assertNull(weapons.get("UnknownWeapon"));
    }

    @Test
    public void shouldReturnWinnerWeapon() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        assertTrue(weapons.findWinnerWeapon(weapons.get("Rock"), weapons.get("Paper")).isPresent());
    }

    @Test
    public void shouldReturnEmptyWhenNoWinnerWeapon() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        assertFalse(weapons.findWinnerWeapon(weapons.get("Rock"), weapons.get("Rock")).isPresent());
    }

    @Test
    public void shouldReturnSameAvailableWeapons() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        WeaponBattleRules rules = weapons.getWeaponBattleRules();

        assertEquals(weapons.getWeaponList(), weapons.getWeaponBattleRules().getAvailableWeapons().getWeaponList());
    }

    @Test
    public void shouldReturnAllWhoBeatWhoRules() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        assertEquals(3, weapons.getWeaponBattleRules().getWhoBeatWhoRules().size());
    }

    @Test
    public void shouldAbleToAddWhoBeatWhoRule() {

        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();
        weapons.getWeaponBattleRules().addRule(new WhoBeatWhoRule(weapons.get("Rock"), weapons.get("Scissors")));
        assertEquals(4, weapons.getWeaponBattleRules().getWhoBeatWhoRules().size());
    }

    @Test
    public void shouldThrowExceptionWhenAddingWhoBeatWhoRuleHasUnknownWeapon() {

        // Given
        RockPaperScissorsWeapons weapons = new RockPaperScissorsWeapons();

        thrown.expect(UnknownWeaponException.class);
        thrown.expectMessage("Unknown weapon.");

        // When
        weapons.getWeaponBattleRules().addRule(
                new WhoBeatWhoRule(weapons.get("Rock"), new BasicWeapon("UnknownWeapon", "UW")));

        // Then
        // Expect test to be passed.
    }


}