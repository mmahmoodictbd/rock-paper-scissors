package com.unloadbrain.games.rockpaperscissors.core.rule;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.List;
import java.util.Optional;

public interface WeaponBattleRules {

    Weapons getAvailableWeapons();

    Optional<Weapon> findWinnerWeapon(List<Weapon> weapons);

    void addRule(WhoBeatWhoRule whoBeatWhoRule);

    List<WhoBeatWhoRule> getWhoBeatWhoRules();
}
