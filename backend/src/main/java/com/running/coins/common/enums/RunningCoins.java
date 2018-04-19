package com.running.coins.common.enums;

public enum RunningCoins {
    ONE_KM(1, 1),
    TWO_KM(2, 1),
    THREE_KM(3, 1),
    FOUR_KM(4, 1),
    FIVE_KM(5, 2),
    SIX_KM(6, 2),
    SEVEN_KM(7, 2),
    EIGHT_KM(8, 2),
    NINE_KM(9, 2);

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

    public Integer findValueOfKM(Integer KM) {
        for (RunningCoins runningCoins : values()) {
            if (runningCoins.getDistance().equals(KM)) {
                return runningCoins.getEarnCoins();
            }
        }
        return null;
    }
}
