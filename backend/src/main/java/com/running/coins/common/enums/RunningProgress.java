package com.running.coins.common.enums;

public enum RunningProgress {
    ERROR(25, "error"),
    WARNING(50, "warning"),
    PRIMARY(75, "primary"),
    SUCCESS(90, "success");

    private Integer code;

    private String msg;

    private RunningProgress(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
