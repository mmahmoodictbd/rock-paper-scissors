package com.unloadbrain.games.rockpaperscissors.core;

import java.util.Collection;
import java.util.Optional;

public interface WeaponBattleRules {

    Weapons getAvailableWeapons();

    Optional<Weapon> findWinnerWeapon(Collection<Weapon> weapons);

    void addRule(WhoBeatWhoRule whoBeatWhoRule);

    Collection<WhoBeatWhoRule> getWhoBeatWhoRules();
}
