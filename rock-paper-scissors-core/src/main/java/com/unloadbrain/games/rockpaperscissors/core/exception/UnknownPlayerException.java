package com.unloadbrain.games.rockpaperscissors.core.exception;

/**
 * Throw this exception when unknown player try to make a move.
 */
public class UnknownPlayerException extends RuntimeException {

    public UnknownPlayerException(String message) {
        super(message);
    }
}
