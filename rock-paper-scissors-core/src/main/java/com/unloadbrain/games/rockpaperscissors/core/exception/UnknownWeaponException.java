package com.unloadbrain.games.rockpaperscissors.core.exception;

/**
 * Throw this exception when unknown weapon is used by a player.
 */
public class UnknownWeaponException extends RuntimeException {

    public UnknownWeaponException(String message) {
        super(message);
    }
}
