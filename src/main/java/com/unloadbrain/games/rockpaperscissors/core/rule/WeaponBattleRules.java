package com.unloadbrain.games.rockpaperscissors.core.rule;

import com.unloadbrain.games.rockpaperscissors.core.exception.UnknownWeaponException;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapon;
import com.unloadbrain.games.rockpaperscissors.core.weapon.Weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WeaponBattleRules {

    Weapons getAvailableWeapons();

    List<WhoBeatWhoRule> getWhoBeatWhoRules();

    default Optional<Weapon> findWinnerWeapon(List<Weapon> weapons) {
        if (weapons.isEmpty() || weapons.size() == 1) {
            return weapons.stream().findFirst();
        }

        List<Weapon> winnerList = new ArrayList<>(weapons);
        List<Weapon> looserList = new ArrayList<>();

        while (true) {

            Weapon weapon1 = winnerList.get(0);
            for (int i = 1; i < winnerList.size(); i++) {
                Optional<Weapon> winner = findWinnerWeapon(weapon1, winnerList.get(i));
                if (winner.isPresent()) {
                    if (winner.get().equals(weapon1)) {
                        looserList.add(winnerList.get(i));
                    } else {
                        looserList.add(weapon1);
                    }
                    weapon1 = winner.get();
                }
            }

            if (looserList.isEmpty()) {
                break;
            } else {
                winnerList.removeAll(looserList);
            }

            if (winnerList.size() < 2) {
                break;
            }
        }

        if (winnerList.size() == 1) {
            return Optional.of(winnerList.get(0));
        }

        return Optional.empty();
    }

    private Optional<Weapon> findWinnerWeapon(Weapon weapon1, Weapon weapon2) {
        Weapons availableWeapons = getAvailableWeapons();
        if (!availableWeapons.isExist(weapon1) || !availableWeapons.isExist(weapon2)) {
            throw new UnknownWeaponException("Unknown weapon.");
        }

        if (weapon1.equals(weapon2)) {
            return Optional.empty();
        }

        for (WhoBeatWhoRule whoBeatWhoRule : getWhoBeatWhoRules()) {
            if (weapon1.equals(whoBeatWhoRule.winner()) && weapon2.equals(whoBeatWhoRule.looser())) {
                return Optional.of(weapon1);
            }
            if (weapon2.equals(whoBeatWhoRule.winner()) && weapon1.equals(whoBeatWhoRule.looser())) {
                return Optional.of(weapon2);
            }
        }

        return Optional.empty();
    }
}
