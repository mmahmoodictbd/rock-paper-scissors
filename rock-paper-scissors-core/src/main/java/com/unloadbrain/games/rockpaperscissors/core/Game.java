package com.unloadbrain.games.rockpaperscissors.core;

import com.unloadbrain.games.rockpaperscissors.core.exception.GameFinishedException;
import com.unloadbrain.games.rockpaperscissors.core.exception.PlayerAlreadyMadeMoveException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownPlayerException;
import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownWeaponException;
import com.unloadbrain.games.rockpaperscissors.core.player.Player;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides state of the game
 */
public class Game {

    private final GameEngine gameEngine;
    private final UUID id;
    private final Set<Player> players;
    private final Map<Player, Weapon> playerSelectedWeaponMap;

    private GameState state;
    private Optional<Player> winner = Optional.empty();


    public Game(GameEngine gameEngine, Player player1, Player player2) {
        this(gameEngine, new HashSet(Arrays.asList(player1, player2)));
    }

    /**
     * Instantiates a new Game.
     *
     * @param gameEngine the game engine
     * @param players    the players
     */
    public Game(GameEngine gameEngine, Set<Player> players) {
        this.id = UUID.randomUUID();
        this.state = GameState.READY_TO_PLAY;
        this.players = players;
        this.playerSelectedWeaponMap = new ConcurrentHashMap<>(players.size());
        this.gameEngine = gameEngine;
    }

    public void play(Player player) {

        player.chooseWeapon();
        Weapon weapon = player.getChosenWeapon();

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

    /**
     * Validate if player already played.
     *
     * @param player the player
     */
    protected void validateIfPlayerAlreadyPlayed(Player player) {
        if (playerSelectedWeaponMap.get(player) != null) {
            throw new PlayerAlreadyMadeMoveException("Player already made his/her move.");
        }
    }

    /**
     * Validation if weapon valid.
     *
     * @param weapon the weapon
     */
    protected void validationIfWeaponValid(Weapon weapon) {
        if (!gameEngine.getWeapons().isExist(weapon)) {
            throw new UnknownWeaponException("Unknown weapon.");
        }
    }

    /**
     * Validate if player valid.
     *
     * @param player the player
     */
    protected void validateIfPlayerValid(Player player) {
        if (!players.contains(player)) {
            throw new UnknownPlayerException("Player is not part of this game.");
        }
    }

    /**
     * Validate if finished.
     */
    protected void validateIfFinished() {
        if (isFinished()) {
            throw new GameFinishedException("Game is finished.");
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public boolean isFinished() {
        return state == GameState.FINISHED;
    }

    /**
     * Find winner optional.
     *
     * @return the optional
     */
    public Optional<Player> findWinner() {
        return winner;
    }

    private boolean hasEveryPlayerPlayed() {
        return playerSelectedWeaponMap.size() == players.size();
    }
}
