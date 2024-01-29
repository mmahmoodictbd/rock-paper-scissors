package com.unloadbrain.games.rockpaperscissors.core.rule;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;

/**
 * Class to provide strength into to weapon battle rule
 */
public record WhoBeatWhoRule(Weapon winner, Weapon looser) {
}
