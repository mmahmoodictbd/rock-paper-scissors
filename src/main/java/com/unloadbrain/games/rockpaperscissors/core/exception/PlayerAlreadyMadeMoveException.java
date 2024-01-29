package com.unloadbrain.games.rockpaperscissors.core.exception;

/**
 * Throw exception when player already made his/her move.
 */
public class PlayerAlreadyMadeMoveException extends RuntimeException {

    public PlayerAlreadyMadeMoveException(String message) {
        super(message);
    }
}
