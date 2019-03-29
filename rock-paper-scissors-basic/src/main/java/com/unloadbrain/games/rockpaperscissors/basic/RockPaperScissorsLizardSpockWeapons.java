package com.unloadbrain.games.rockpaperscissors.basic;

import com.unloadbrain.games.rockpaperscissors.core.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.Weapons;
import com.unloadbrain.games.rockpaperscissors.core.WhoBeatWhoRule;

public class RockPaperScissorsLizardSpockWeapons extends RockPaperScissorsWeapons implements Weapons {

    public RockPaperScissorsLizardSpockWeapons() {

        addWeapon(new Weapon("Lizard"));
        addWeapon(new Weapon("Spock"));

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
