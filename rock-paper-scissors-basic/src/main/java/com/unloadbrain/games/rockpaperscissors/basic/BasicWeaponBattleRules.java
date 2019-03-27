package com.unloadbrain.games.rockpaperscissors.basic;

import com.unloadbrain.games.rockpaperscissors.core.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.Weapons;
import com.unloadbrain.games.rockpaperscissors.exception.UnknownWeaponException;

import java.util.Optional;

public class BasicWeaponBattleRules extends WeaponBattleRules<BasicWeapons> {

    private final Weapons weapons;

    public BasicWeaponBattleRules(Weapons weapons) {
        this.weapons = weapons;
    }

    @Override
    protected Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2) {

        if (!weapons.isExist(weapon1) || !weapons.isExist(weapon2)) {
            throw new UnknownWeaponException("Unknown weapon.");
        }

        if (weapon1.getLabel().equals(weapon2.getLabel())) {
            return Optional.empty();
        }

        if (weapon1.getLabel().equals("Rock")) {
            if (weapon2.getLabel().equals("Scissors")) {
                return Optional.of(weapon1);
            }
            if (weapon2.getLabel().equals("Paper")) {
                return Optional.of(weapon2);
            }
        }

        if (weapon1.getLabel().equals("Scissors")) {
            if (weapon2.getLabel().equals("Paper")) {
                return Optional.of(weapon1);
            }
            if (weapon2.getLabel().equals("Rock")) {
                return Optional.of(weapon2);
            }
        }

        if (weapon1.getLabel().equals("Paper")) {
            if (weapon2.getLabel().equals("Rock")) {
                return Optional.of(weapon1);
            }
            if (weapon2.getLabel().equals("Scissors")) {
                return Optional.of(weapon2);
            }
        }

        return Optional.empty();
    }


}
