package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.function.Function;

public class HumanPlayer implements Player {

    private final String name;
    private final Function<Weapons, Weapon> inputPromptFunction;
    private Weapon chosenWeapon;

    public HumanPlayer(String name) {
        this.name = name;
        this.inputPromptFunction = (Weapons availableWeapons) -> null;
    }

    public HumanPlayer(String name, Function<Weapons, Weapon> inputPromptFunction) {
        this.name = name;
        this.inputPromptFunction = inputPromptFunction;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void chooseWeapon(Weapons availableWeapons) {
        chosenWeapon = inputPromptFunction.apply(availableWeapons);
    }

    @Override
    public Weapon getChosenWeapon() {
        return chosenWeapon;
    }
}
