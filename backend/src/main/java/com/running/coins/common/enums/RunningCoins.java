package com.running.coins.common.enums;

public enum RunningCoins {
    ONE_KM(1, 1),
    TWO_KM(2, 2),
    THREE_KM(3, 3),
    FOUR_KM(4, 4),
    FIVE_KM(5, 5),
    SIX_KM(6, 6),
    SEVEN_KM(7, 7),
    EIGHT_KM(8, 8),
    NINE_KM(9, 9);

    private Integer distance;

    private Integer earnedCoins;

    RunningCoins(Integer distance, Integer earnedCoins) {
        this.distance = distance;
        this.earnedCoins = earnedCoins;
    }

    public Integer getDistance() {
        return distance;
    }

    public Integer getEarnCoins() {
        return earnedCoins;
    }
}
