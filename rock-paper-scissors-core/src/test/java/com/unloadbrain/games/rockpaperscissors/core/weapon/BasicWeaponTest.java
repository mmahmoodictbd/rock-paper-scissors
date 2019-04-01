package com.unloadbrain.games.rockpaperscissors.core.weapon;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BasicWeaponTest {

    @Test
    public void testEqualObjects() {

        Weapon weapon1 = new BasicWeapon("weapon", "W1");
        Weapon weapon2 = new BasicWeapon("weapon", "W2");
        Weapon weapon3 = new BasicWeapon("anotherWeapon", "W2");

        assertTrue(weapon1.equals(weapon1));

        assertTrue(weapon1.equals(weapon2));
        assertTrue(weapon2.equals(weapon1));

        assertFalse(weapon1.equals(weapon3));
        assertFalse(weapon3.equals(weapon1));
    }

    @Test
    public void shouldHaveSameHashcodeWithMatchedLabel() {

        Weapon weapon1 = new BasicWeapon("weapon", "W1");
        Weapon weapon2 = new BasicWeapon("weapon", "W2");

        assertEquals(weapon1.hashCode(), weapon2.hashCode());
    }

    @Test
    public void toStringShouldReturnSameAsLabel() {

        Weapon weapon = new BasicWeapon("weapon", "W1");
        assertEquals("weapon", weapon.toString());
    }

    @Test
    public void shouldReturnLabelStringWhenSymbolNotPassedAsConstructorParam() {

        BasicWeapon weapon = new BasicWeapon("weapon");
        assertEquals("weapon", weapon.getSymbol());
    }
}