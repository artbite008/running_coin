package com.running.coins.common.enums;

public enum WeekDays {
    Mon(1, "Mon"),
    TUE(2, "TUE"),
    WED(3, "WED"),
    THU(4, "THU"),
    FRI(5, "FRI"),
    SAT(6, "SAT"),
    SUN(7, "SUN");

    private Integer num;

    private String day;

    private WeekDays(Integer num, String day) {
        this.num = num;
        this.day = day;
    }

    public Integer getNum() {
        return num;
    }

    public String getDay() {
        return day;
    }
}
