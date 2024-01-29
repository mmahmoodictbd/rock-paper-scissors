package com.unloadbrain.games.rockpaperscissors.cli;

import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.weapon.BasicWeapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.List;
import java.util.Optional;

/**
 * Basic Rock paper scissors weapons.
 */
public class RockPaperScissorsWeapons implements Weapons {

    private final List<Weapon> weapons;
    private final WeaponBattleRules weaponBattleRules;

    public RockPaperScissorsWeapons() {
        weapons = List.of(
                new BasicWeapon("Rock", "FIST"),
                new BasicWeapon("Paper", "OPEN_HAND"),
                new BasicWeapon("Scissors", "INDEX_MIDDLE_FINGER")
        );
        weaponBattleRules = new RockPaperScissorsWeaponBattleRules(this);
    }

    @Override
    public boolean isExist(Weapon weapon) {
        return weapons.stream().anyMatch(mo -> weapon.getLabel().equals(mo.getLabel()));
    }

    @Override
    public Weapon get(String label) {
        return weapons.stream()
                .filter(mo -> mo.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Weapon> getWeaponList() {
        return weapons;
    }

    @Override
    public WeaponBattleRules getWeaponBattleRules() {
        return weaponBattleRules;
    }

    @Override
    public Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2) {
        return weaponBattleRules.findWinnerWeapon(List.of(weapon1, weapon2));
    }
}
