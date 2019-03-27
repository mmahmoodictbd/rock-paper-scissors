package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.util.Util;
import com.unloadbrain.games.rockpaperscissors.exception.GameFinishedException;
import com.unloadbrain.games.rockpaperscissors.exception.PlayerAlreadyMadeMoveException;
import com.unloadbrain.games.rockpaperscissors.exception.UnknownPlayerException;
import com.unloadbrain.games.rockpaperscissors.exception.UnknownWeaponException;

import java.util.Collections;
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
    private final Map<Player, Weapon> playerSelectedMoveOptionMap;
    private GameState state;
    private Optional<Player> winner = Optional.empty();

    public Game(List<Player> players, Weapons weapons) {
        this.id = UUID.randomUUID().toString();
        this.state = GameState.INIT;
        this.players = Collections.unmodifiableList(players);
        this.weapons = weapons;
        this.weaponBattleRules = weapons.getWeaponBattleRules();
        this.playerSelectedMoveOptionMap = new ConcurrentHashMap<>(players.size());
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

        if (playerSelectedMoveOptionMap.get(player) != null) {
            throw new PlayerAlreadyMadeMoveException("Player already made his/her move.");
        }

        playerSelectedMoveOptionMap.put(player, weapon);

        if (hasEveryPlayerPlayed()) {
            determineWinner();
            setState(GameState.FINISHED);
        }
    }

    protected void determineWinner() {

        Optional<Weapon> winnerWeapon = weaponBattleRules.findWinnerWeapon(playerSelectedMoveOptionMap.values());
        if (winnerWeapon.isPresent()) {
            setWinner(Optional.of(Util.getKeyByValue(playerSelectedMoveOptionMap, winnerWeapon.get())));
        }
    }

    private boolean hasEveryPlayerPlayed() {
        return playerSelectedMoveOptionMap.size() == players.size();
    }
}
