package com.unloadbrain.games.rockpaperscissors.core;

import java.util.Date;

import static com.unloadbrain.games.rockpaperscissors.core.ConsoleLogger.LogLevel.INFO;

public class ConsoleLogger {

    public static void log(LogLevel level, String message) {
        String formattedMessage = getFormattedLogMessage(level, message);
        System.out.println(formattedMessage);
    }

    private static String getFormattedLogMessage(LogLevel level, String message) {
        Date timestamp = new Date();
        return "[" + timestamp + "] " + level + ": " + message;
    }

    public static void info(String message) {
        log(INFO, message);
    }

    public enum LogLevel {
        INFO,
        WARNING,
        ERROR
    }
}
