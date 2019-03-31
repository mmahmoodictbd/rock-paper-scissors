package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class RandomBotPlayer implements Player {

    private final Random randomGenerator;
    private final String name;
    private Weapon chosenWeapon;

    public RandomBotPlayer(String name, Weapons availableWeapons) {
        this.name = name;

        this.randomGenerator = new SecureRandom();
        List<Weapon> availableWeaponList = availableWeapons.getWeaponList();
        this.chosenWeapon = availableWeaponList.get(randomGenerator.nextInt(availableWeaponList.size()));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Weapon getChosenWeapon() {
        return this.chosenWeapon;
    }

}
