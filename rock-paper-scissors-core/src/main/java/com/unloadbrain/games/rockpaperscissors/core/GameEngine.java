package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.util.Util;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class GameEngine {

    private final Weapons weapons;
    private final WeaponBattleRules weaponBattleRules;

    public GameEngine(Weapons weapons) {
        this.weapons = weapons;
        this.weaponBattleRules = weapons.getWeaponBattleRules();
    }

    protected Optional<Player> findWinner(Map<Player, Weapon> playerSelectedWeaponMap) {

        Optional<Weapon> winnerWeapon
                = weaponBattleRules.findWinnerWeapon(new ArrayList<>(playerSelectedWeaponMap.values()));

        if (winnerWeapon.isPresent()) {
            return Optional.of(Util.getKeyByValue(playerSelectedWeaponMap, winnerWeapon.get()));
        }

        return Optional.empty();
    }

    public Weapons getWeapons() {
        return weapons;
    }
}
