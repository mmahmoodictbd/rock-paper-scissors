package com.unloadbrain.games.rockpaperscissors.core.weapon;

import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.rule.WhoBeatWhoRule;

public class RockPaperScissorsLizardSpockWeapons extends RockPaperScissorsWeapons implements Weapons {

    public RockPaperScissorsLizardSpockWeapons() {

        addWeapon(new BasicWeapon("Lizard", "LIZARD"));
        addWeapon(new BasicWeapon("Spock", "SPOCK"));

        WeaponBattleRules rules = getWeaponBattleRules();
        rules.addRule(new WhoBeatWhoRule(rules.getAvailableWeapons().get("Rock"), rules.getAvailableWeapons().get("Lizard")));
        rules.addRule(new WhoBeatWhoRule(rules.getAvailableWeapons().get("Lizard"), rules.getAvailableWeapons().get("Spock")));
        rules.addRule(new WhoBeatWhoRule(rules.getAvailableWeapons().get("Spock"), rules.getAvailableWeapons().get("Scissors")));
        rules.addRule(new WhoBeatWhoRule(rules.getAvailableWeapons().get("Scissors"), rules.getAvailableWeapons().get("Lizard")));
        rules.addRule(new WhoBeatWhoRule(rules.getAvailableWeapons().get("Lizard"), rules.getAvailableWeapons().get("Paper")));
        rules.addRule(new WhoBeatWhoRule(rules.getAvailableWeapons().get("Paper"), rules.getAvailableWeapons().get("Spock")));
        rules.addRule(new WhoBeatWhoRule(rules.getAvailableWeapons().get("Spock"), rules.getAvailableWeapons().get("Rock")));

    }


}
