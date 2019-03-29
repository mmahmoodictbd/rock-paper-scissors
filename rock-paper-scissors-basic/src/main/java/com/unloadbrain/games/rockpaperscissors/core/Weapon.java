package com.unloadbrain.games.rockpaperscissors.core;

public class Weapon {

    private String label;

    public Weapon(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Weapon)) {
            return false;
        }

        Weapon other = (Weapon) obj;
        if (!other.canEqual(this)) {
            return false;
        }

        if (this.getLabel() == null ? other.getLabel() != null : !this.getLabel().equals(other.getLabel())) {
            return false;
        }

        return true;
    }

    private boolean canEqual(Object other) {
        return other instanceof Weapon;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return label;
    }
}
