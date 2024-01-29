package com.unloadbrain.games.rockpaperscissors.core.weapon;

import java.util.Objects;

public record BasicWeapon(String label, String symbol) implements Weapon, DisplayableWeapon {

    public BasicWeapon(String label) {
        this(label, label);
    }

    public BasicWeapon {
        Objects.requireNonNull(label, "Label cannot be null");
        Objects.requireNonNull(symbol, "Symbol cannot be null");
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return label;
    }
}
