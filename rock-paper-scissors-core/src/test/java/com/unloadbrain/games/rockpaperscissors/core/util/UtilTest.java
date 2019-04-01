package com.unloadbrain.games.rockpaperscissors.core.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UtilTest {

    @Test
    public void shouldReturnKeyByValue() {

        // Given

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        // When
        String key = Util.getKeyByValue(map, "value1");

        // Then
        assertEquals("key1", key);
    }

    @Test
    public void shouldReturnNullWhenValueNotPresent() {

        // Given

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        // When
        String key = Util.getKeyByValue(map, "value4");

        // Then
        assertNull(key);
    }

}