package com.unloadbrain.games.rockpaperscissors.cli;

import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.rule.WhoBeatWhoRule;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.List;

public class RockPaperScissorsWeaponBattleRules implements WeaponBattleRules {

    private final Weapons weapons;
    private final List<WhoBeatWhoRule> whoBeatWhoRules;

    public RockPaperScissorsWeaponBattleRules(Weapons weapons) {
        this.weapons = weapons;
        this.whoBeatWhoRules = List.of(
                new WhoBeatWhoRule(weapons.get("Rock"), weapons.get("Scissors")),
                new WhoBeatWhoRule(weapons.get("Scissors"), weapons.get("Paper")),
                new WhoBeatWhoRule(weapons.get("Paper"), weapons.get("Rock"))
        );
    }

    @Override
    public Weapons getAvailableWeapons() {
        return weapons;
    }

    @Override
    public List<WhoBeatWhoRule> getWhoBeatWhoRules() {
        return whoBeatWhoRules;
    }
}