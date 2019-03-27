package com.unloadbrain.games.rockpaperscissors.core.util;

import java.util.Map;
import java.util.Objects;

public class Util {

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
