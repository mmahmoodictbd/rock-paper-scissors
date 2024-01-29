package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.Random;

/**
 * Random bot player.
 */
public class RandomBotPlayer implements Player {

    private final Random randomGenerator;
    private final String name;
    private Weapon chosenWeapon;

    public RandomBotPlayer(String name, Random randomGenerator) {
        this.name = name;
        this.randomGenerator = randomGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void chooseWeapon(Weapons availableWeapons) {
        var availableWeaponList = availableWeapons.getWeaponList();
        chosenWeapon = availableWeaponList.get(randomGenerator.nextInt(availableWeaponList.size()));
    }

    @Override
    public Weapon getChosenWeapon() {
        return chosenWeapon;
    }
}
