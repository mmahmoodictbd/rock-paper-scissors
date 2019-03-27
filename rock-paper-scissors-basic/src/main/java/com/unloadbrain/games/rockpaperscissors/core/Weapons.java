package com.unloadbrain.games.rockpaperscissors.core;

public interface Weapons {

    WeaponBattleRules getWeaponBattleRules();

    void setWeaponBattleRules(WeaponBattleRules weaponBattleRules);

    boolean isExist(Weapon weapon);

    Weapon get(String label);
}
