package com.unloadbrain.games.rockpaperscissors.exception;

public class PlayerAlreadyMadeMoveException extends RuntimeException {

    public PlayerAlreadyMadeMoveException(String message) {
        super(message);
    }
}
