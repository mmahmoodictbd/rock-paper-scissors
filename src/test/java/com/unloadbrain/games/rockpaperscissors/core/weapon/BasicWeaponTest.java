package com.unloadbrain.games.rockpaperscissors.core.weapon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BasicWeaponTest {

    @Test
    void equalObjects() {
        var weapon1 = new BasicWeapon("weapon", "W1");
        var weapon2 = new BasicWeapon("weapon", "W1");
        var weapon3 = new BasicWeapon("anotherWeapon", "W2");

        assertEquals(weapon1, weapon2);
        assertNotEquals(weapon1, weapon3);
    }

    @Test
    void shouldHaveSameHashcodeWithMatchedLabel() {
        var weapon1 = new BasicWeapon("weapon", "W1");
        var weapon2 = new BasicWeapon("weapon", "W1");

        assertEquals(weapon1.hashCode(), weapon2.hashCode());
    }

    @Test
    void toStringShouldReturnSameAsLabel() {
        var weapon = new BasicWeapon("weapon", "W1");
        assertEquals("weapon", weapon.toString());
    }

    @Test
    void shouldReturnLabelStringWhenSymbolNotPassedAsConstructorParam() {
        var weapon = new BasicWeapon("weapon");
        assertEquals("weapon", weapon.getSymbol());
    }
}