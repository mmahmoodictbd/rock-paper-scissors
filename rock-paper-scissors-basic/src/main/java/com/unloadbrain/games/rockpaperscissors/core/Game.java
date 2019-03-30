package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.core.exception.GameFinishedException;
import com.unloadbrain.games.rockpaperscissors.core.exception.PlayerAlreadyMadeMoveException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownPlayerException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownWeaponException;
import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.rule.WeaponBattleRules;
import com.unloadbrain.games.rockpaperscissors.core.util.Util;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    private final String id;
    private final List<Player> players;
    private final Weapons weapons;
    private final WeaponBattleRules weaponBattleRules;
    private final Map<Player, Weapon> playerSelectedWeaponMap;
    private GameState state;
    private Optional<Player> winner = Optional.empty();

    public Game(List<Player> players, Weapons weapons) {
        this.id = UUID.randomUUID().toString();
        this.state = GameState.READY_TO_PLAY;
        this.players = players;
        this.weapons = weapons;
        this.weaponBattleRules = weapons.getWeaponBattleRules();
        this.playerSelectedWeaponMap = new ConcurrentHashMap<>(players.size());
    }

    public String getId() {
        return id;
    }

    public boolean isFinished() {
        return state == GameState.FINISHED;
    }

    public Optional<Player> findWinner() {
        return winner;
    }

    protected void setState(GameState state) {
        this.state = state;
    }

    protected void setWinner(Optional<Player> winner) {
        this.winner = winner;
    }

    public Optional<Player> getWinner() {
        return winner;
    }

    public void play(Player player, Weapon weapon) {

        if (isFinished()) {
            throw new GameFinishedException("Game is finished.");
        }

        if (!players.contains(player)) {
            throw new UnknownPlayerException("Player is not part of this game.");
        }

        if (!weapons.isExist(weapon)) {
            throw new UnknownWeaponException("Unknown weapon.");
        }

        if (playerSelectedWeaponMap.get(player) != null) {
            throw new PlayerAlreadyMadeMoveException("Player already made his/her move.");
        }

        playerSelectedWeaponMap.put(player, weapon);

        if (hasEveryPlayerPlayed()) {
            determineWinner();
            setState(GameState.FINISHED);
        } else {
            setState(GameState.WAITING_FOR_MOVE);
        }
    }

    protected void determineWinner() {

        Optional<Weapon> winnerWeapon
                = weaponBattleRules.findWinnerWeapon(new ArrayList<>(playerSelectedWeaponMap.values()));
        if (winnerWeapon.isPresent()) {
            setWinner(Optional.of(Util.getKeyByValue(playerSelectedWeaponMap, winnerWeapon.get())));
        }
    }

    private boolean hasEveryPlayerPlayed() {
        return playerSelectedWeaponMap.size() == players.size();
    }

    public enum GameState {

        // When game started
        READY_TO_PLAY,

        // Waiting for move
        WAITING_FOR_MOVE,

        // No more move left
        FINISHED;

    }

}
