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

    public Optional<Player> findWinner(Map<Player, Weapon> playerSelectedWeaponMap) {
        Optional<Weapon> winnerWeapon =
                weaponBattleRules.findWinnerWeapon(new ArrayList<>(playerSelectedWeaponMap.values()));
        return winnerWeapon.map(winner -> Util.getKeyByValue(playerSelectedWeaponMap, winner));
    }

    public Weapons getWeapons() {
        return weapons;
    }
}
