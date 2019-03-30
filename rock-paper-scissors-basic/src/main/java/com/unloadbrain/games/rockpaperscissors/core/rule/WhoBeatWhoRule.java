package com.unloadbrain.games.rockpaperscissors.core.rule;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;

public class WhoBeatWhoRule {

    private final Weapon winner;
    private final Weapon looser;

    public WhoBeatWhoRule(Weapon winner, Weapon looser) {
        this.winner = winner;
        this.looser = looser;
    }

    public Weapon getWinner() {
        return winner;
    }

    public Weapon getLooser() {
        return looser;
    }
}
