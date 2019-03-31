package com.unloadbrain.games.rockpaperscissors.core.rule;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;

/**
 * Class to provide strength into to weapon battle rule
 */
public class WhoBeatWhoRule {

    private final Weapon winner;
    private final Weapon looser;

    /**
     * Instantiates a new Who beat who rule.
     *
     * @param winner the winner
     * @param looser the looser
     */
    public WhoBeatWhoRule(Weapon winner, Weapon looser) {
        this.winner = winner;
        this.looser = looser;
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public Weapon getWinner() {
        return winner;
    }

    /**
     * Gets looser.
     *
     * @return the looser
     */
    public Weapon getLooser() {
        return looser;
    }
}
