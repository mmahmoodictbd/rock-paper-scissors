package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;


public interface Player {

    String getName();

    void chooseWeapon();

    Weapon getChosenWeapon();

}
