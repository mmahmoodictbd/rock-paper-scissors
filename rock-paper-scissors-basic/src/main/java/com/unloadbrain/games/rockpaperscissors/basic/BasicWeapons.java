package com.unloadbrain.games.rockpaperscissors.basic;

import com.unloadbrain.games.rockpaperscissors.core.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.Weapons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasicWeapons implements Weapons {

    private final List<Weapon> weapons;
    private WeaponBattleRules basicWeaponBattleRules;

    public BasicWeapons() {
        weapons = Collections.unmodifiableList(Arrays.asList(
                new Weapon("Rock"),
                new Weapon("Paper"),
                new Weapon("Scissors"))
        );
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public boolean isExist(Weapon weapon) {

        for (Weapon mo : weapons) {
            if (weapon.getLabel().equals(mo.getLabel())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Weapon get(String label) {

        for (Weapon mo : weapons) {
            if (mo.getLabel().equals(label)) {
                return mo;
            }
        }

        return null;
    }

    @Override
    public WeaponBattleRules getWeaponBattleRules() {
        return basicWeaponBattleRules;
    }

    @Override
    public void setWeaponBattleRules(WeaponBattleRules weaponBattleRules) {
        this.basicWeaponBattleRules = weaponBattleRules;
    }
}
