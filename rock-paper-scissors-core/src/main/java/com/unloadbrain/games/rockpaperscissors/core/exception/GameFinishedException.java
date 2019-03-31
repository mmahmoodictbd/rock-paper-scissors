package com.unloadbrain.games.rockpaperscissors.core.exception;

/**
 * Throw exception when play is invoked and game is already finished.
 */
public class GameFinishedException extends RuntimeException {

    public GameFinishedException(String message) {
        super(message);
    }
}
