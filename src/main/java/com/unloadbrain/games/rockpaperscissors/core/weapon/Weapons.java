package com.unloadbrain.games.rockpaperscissors.core.weapon;

import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;

import java.util.List;
import java.util.Optional;

public interface Weapons {

    boolean isExist(Weapon weapon);

    Weapon get(String label);

    List<Weapon> getWeaponList();

    WeaponBattleRules getWeaponBattleRules();

    Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2);

}
