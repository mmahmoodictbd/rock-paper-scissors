package com.unloadbrain.games.rockpaperscissors.core;

import java.util.Optional;

public interface Weapons {

    boolean isExist(Weapon weapon);

    Weapon get(String label);

    void addWeapon(Weapon weapon);

    WeaponBattleRules getWeaponBattleRules();

    void setWeaponBattleRules(WeaponBattleRules weaponBattleRules);

    Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2);

}
