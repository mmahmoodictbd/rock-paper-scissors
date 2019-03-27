//package com.unloadbrain.games.rockpaperscissors;
//
//import java.util.List;
//
//public class ExpirableGame extends Game {
//
//
//    private final long inactivityExpiredInMillis;
//    private long lastUpdated;
//
//    public ExpirableGame(List<Player> players, long inactivityExpiredInMillis) {
//        super(players);
//        this.inactivityExpiredInMillis = inactivityExpiredInMillis;
//        this.lastUpdated = System.currentTimeMillis();
//    }
//
//    public boolean isExpired() {
//        return System.currentTimeMillis() - lastUpdated > inactivityExpiredInMillis;
//    }
//
//}
