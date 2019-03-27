package com.unloadbrain.games.rockpaperscissors.core;

import java.util.Collection;
import java.util.Optional;

public abstract class WeaponBattleRules<B> {

    protected Optional<Weapon> findWinnerWeapon(Collection<Weapon> weapons) {
        return findWinnerWeapon(weapons.toArray(new Weapon[weapons.size()])[0], weapons.toArray(new Weapon[weapons.size()])[1]);
    }

    abstract protected Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2);
}
