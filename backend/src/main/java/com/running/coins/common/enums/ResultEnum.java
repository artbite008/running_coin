package com.running.coins.common.enums;

public enum ResultEnum {
    /*RECORDTIME_ERROR(400,"RecordTime Error: you must record after one hour"),*/
    UNKNOWN_ERROR(100, "Unknown Error"),
    SYSTEM_ERROR(200, "System Error"),
    INSERT_ERROR(300, "Insert Error"),
    SUCCESS(0, "success");

    private Integer code;

    private String msg;

    private ResultEnum(Integer code, String msg) {
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
