package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.exception.UnknownWeaponException;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractWeaponBattleRules implements WeaponBattleRules {

    public Optional<Weapon> findWinnerWeapon(Collection<Weapon> weapons) {
        return findWinnerWeapon(weapons.toArray(new Weapon[weapons.size()])[0], weapons.toArray(new Weapon[weapons.size()])[1]);
    }

    protected Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2) {

        Weapons availableWeapons = getAvailableWeapons();

        if (!availableWeapons.isExist(weapon1) || !availableWeapons.isExist(weapon2)) {
            throw new UnknownWeaponException("Unknown weapon.");
        }

        if (weapon1.equals(weapon2)) {
            return Optional.empty();
        }

        for (WhoBeatWhoRule whoBeatWhoRule : getWhoBeatWhoRules()) {
            if (weapon1.equals(whoBeatWhoRule.getWinner()) && weapon2.equals(whoBeatWhoRule.getLooser())) {
                return Optional.of(weapon1);
            }
            if (weapon2.equals(whoBeatWhoRule.getWinner()) && weapon1.equals(whoBeatWhoRule.getLooser())) {
                return Optional.of(weapon2);
            }
        }

        return Optional.empty();
    }
    
}
