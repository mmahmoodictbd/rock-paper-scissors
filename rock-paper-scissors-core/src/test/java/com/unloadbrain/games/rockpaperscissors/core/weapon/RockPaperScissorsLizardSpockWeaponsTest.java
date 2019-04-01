package com.unloadbrain.games.rockpaperscissors.core.weapon;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RockPaperScissorsLizardSpockWeaponsTest {

    @Test
    public void getWeaponsShouldReturn6BasicWeapons() {

        RockPaperScissorsLizardSpockWeapons weapons = new RockPaperScissorsLizardSpockWeapons();
        assertEquals(5, weapons.getWeaponList().size());
    }
}