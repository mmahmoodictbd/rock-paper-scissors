package com.unloadbrain.games.rockpaperscissors.core.weapon;

import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;

import java.util.List;
import java.util.Optional;

/**
 * The interface Weapons.
 */
public interface Weapons {

    /**
     * Is exist boolean.
     *
     * @param weapon the weapon
     * @return the boolean
     */
    boolean isExist(Weapon weapon);

    /**
     * Get weapon.
     *
     * @param label the label
     * @return the weapon
     */
    Weapon get(String label);

    /**
     * Gets weapon list.
     *
     * @return the weapon list
     */
    List<Weapon> getWeaponList();

    /**
     * Add weapon.
     *
     * @param weapon the weapon
     */
    void addWeapon(Weapon weapon);

    /**
     * Gets weapon battle rules.
     *
     * @return the weapon battle rules
     */
    WeaponBattleRules getWeaponBattleRules();

    /**
     * Sets weapon battle rules.
     *
     * @param weaponBattleRules the weapon battle rules
     */
    void setWeaponBattleRules(WeaponBattleRules weaponBattleRules);

    /**
     * Find winner weapon optional.
     *
     * @param weapon1 the weapon 1
     * @param weapon2 the weapon 2
     * @return the optional
     */
    Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2);

}
