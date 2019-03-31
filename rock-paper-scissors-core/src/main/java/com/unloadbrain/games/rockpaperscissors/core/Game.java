package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.core.exception.GameFinishedException;
import com.unloadbrain.games.rockpaperscissors.core.exception.PlayerAlreadyMadeMoveException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownPlayerException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownWeaponException;
import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    private final GameEngine gameEngine;
    private final UUID id;
    private final Set<Player> players;
    private final Map<Player, Weapon> playerSelectedWeaponMap;

    private GameState state;
    private Optional<Player> winner = Optional.empty();

    public Game(GameEngine gameEngine, Set<Player> players) {
        this.id = UUID.randomUUID();
        this.state = GameState.READY_TO_PLAY;
        this.players = players;
        this.playerSelectedWeaponMap = new ConcurrentHashMap<>(players.size());
        this.gameEngine = gameEngine;
    }

    public void play(Player player, Weapon weapon) {

        validateIfFinished();
        validateIfPlayerValid(player);
        validationIfWeaponValid(weapon);
        validateIfPlayerAlreadyPlayed(player);

        playerSelectedWeaponMap.put(player, weapon);

        if (hasEveryPlayerPlayed()) {
            winner = gameEngine.findWinner(playerSelectedWeaponMap);
            state = GameState.FINISHED;
        } else {
            state = GameState.WAITING_FOR_MOVE;
        }
    }

    protected void validateIfPlayerAlreadyPlayed(Player player) {
        if (playerSelectedWeaponMap.get(player) != null) {
            throw new PlayerAlreadyMadeMoveException("Player already made his/her move.");
        }
    }

    protected void validationIfWeaponValid(Weapon weapon) {
        if (!gameEngine.getWeapons().isExist(weapon)) {
            throw new UnknownWeaponException("Unknown weapon.");
        }
    }

    protected void validateIfPlayerValid(Player player) {
        if (!players.contains(player)) {
            throw new UnknownPlayerException("Player is not part of this game.");
        }
    }

    protected void validateIfFinished() {
        if (isFinished()) {
            throw new GameFinishedException("Game is finished.");
        }
    }

    public String getId() {
        return id.toString();
    }

    public boolean isFinished() {
        return state == GameState.FINISHED;
    }

    public Optional<Player> findWinner() {
        return winner;
    }

    private boolean hasEveryPlayerPlayed() {
        return playerSelectedWeaponMap.size() == players.size();
    }
}
