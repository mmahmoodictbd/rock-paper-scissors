package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;

import java.util.concurrent.Callable;

public class HumanPlayer implements Player {

    private final String name;
    private final Callable inputPromptFunction;
    private Weapon chosenWeapon;

    public HumanPlayer(String name) {
        this.name = name;
        this.inputPromptFunction = () -> null;
    }

    public HumanPlayer(String name, Callable<Weapon> inputPromptFunction) {
        this.name = name;
        this.inputPromptFunction = inputPromptFunction;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Weapon getChosenWeapon() {

        if (chosenWeapon == null) {
            try {
                chosenWeapon = (Weapon) inputPromptFunction.call();
            } catch (Exception ex) {
                throw new RuntimeException("Fatal error.");
            }
        }

        return chosenWeapon;
    }
}
