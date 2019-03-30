package com.unloadbrain.games.rockpaperscissors.core.weapon;

public class BasicWeapon implements Weapon, DisplayableWeapon {

    private String label;
    private String symbol;

    public BasicWeapon(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BasicWeapon)) {
            return false;
        }

        BasicWeapon other = (BasicWeapon) obj;
        if (!other.canEqual(this)) {
            return false;
        }

        if (this.getLabel() == null ? other.getLabel() != null : !this.getLabel().equals(other.getLabel())) {
            return false;
        }

        return true;
    }

    private boolean canEqual(Object other) {
        return other instanceof BasicWeapon;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
