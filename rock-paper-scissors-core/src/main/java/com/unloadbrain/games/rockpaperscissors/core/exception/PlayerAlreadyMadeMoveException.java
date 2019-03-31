package com.unloadbrain.games.rockpaperscissors.core.exception;

public class PlayerAlreadyMadeMoveException extends RuntimeException {

    public PlayerAlreadyMadeMoveException(String message) {
        super(message);
    }
}
