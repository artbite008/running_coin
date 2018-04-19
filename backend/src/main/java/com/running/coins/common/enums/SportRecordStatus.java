package com.running.coins.common.enums;

public enum SportRecordStatus {
    SUBMITTED(0, "submitted"),
    EXPIRED(1, "expired"),
    REJECTED(2, "rejected"),
    PASSED(3, "passed");

    private Integer code;

    private String msg;

    private SportRecordStatus(Integer code, String msg) {
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
