package com.unloadbrain.games.rockpaperscissors.core.weapon;

import com.unloadbrain.games.rockpaperscissors.core.rule.AbstractWeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.rule.WhoBeatWhoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Basic Rock paper scissors weapons.
 */
public class RockPaperScissorsWeapons implements Weapons {

    private final List<Weapon> weapons;
    private WeaponBattleRules weaponBattleRules;

    public RockPaperScissorsWeapons() {
        weapons = new ArrayList<>();
        weapons.add(new BasicWeapon("Rock", "FIST"));
        weapons.add(new BasicWeapon("Paper", "OPEN_HAND"));
        weapons.add(new BasicWeapon("Scissors", "INDEX_MIDDLE_FINGER"));
        weaponBattleRules = new RockPaperScissorsWeaponBattleRules(this);
    }

    @Override
    public boolean isExist(Weapon weapon) {

        for (Weapon mo : weapons) {
            if (weapon.getLabel().equals(mo.getLabel())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Weapon get(String label) {

        for (Weapon mo : weapons) {
            if (mo.getLabel().equalsIgnoreCase(label)) {
                return mo;
            }
        }

        return null;
    }

    @Override
    public List<Weapon> getWeaponList() {
        return Collections.unmodifiableList(weapons);
    }

    @Override
    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    @Override
    public WeaponBattleRules getWeaponBattleRules() {
        return weaponBattleRules;
    }

    @Override
    public void setWeaponBattleRules(WeaponBattleRules weaponBattleRules) {
        this.weaponBattleRules = weaponBattleRules;
    }

    @Override
    public Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2) {
        return weaponBattleRules.findWinnerWeapon(Arrays.asList(weapon1, weapon2));
    }

    /**
     * Basic Rock paper scissors weapon battle rules.
     */
    static class RockPaperScissorsWeaponBattleRules extends AbstractWeaponBattleRules {

        private final Weapons weapons;
        private List<WhoBeatWhoRule> whoBeatWhoRules = new ArrayList<>();

        public RockPaperScissorsWeaponBattleRules(Weapons weapons) {
            this.weapons = weapons;
            addRule(new WhoBeatWhoRule(weapons.get("Rock"), weapons.get("Scissors")));
            addRule(new WhoBeatWhoRule(weapons.get("Scissors"), weapons.get("Paper")));
            addRule(new WhoBeatWhoRule(weapons.get("Paper"), weapons.get("Rock")));
        }

        @Override
        public Weapons getAvailableWeapons() {
            return weapons;
        }

        @Override
        public void addRule(WhoBeatWhoRule whoBeatWhoRule) {
            validateWhoBeatWhoRule(whoBeatWhoRule);
            whoBeatWhoRules.add(whoBeatWhoRule);
        }

        @Override
        public List<WhoBeatWhoRule> getWhoBeatWhoRules() {
            return Collections.unmodifiableList(whoBeatWhoRules);
        }
    }


}
