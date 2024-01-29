package com.unloadbrain.games.rockpaperscissors.core.player;

import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;


public interface Player {

    String getName();

    void chooseWeapon(Weapons availableWeapons);

    Weapon getChosenWeapon();

}
