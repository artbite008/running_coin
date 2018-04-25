package com.running.coins.common.enums;

public enum VoteStatus {
    LIKE(0, "like"),
    CANCELLIKE(1, "cancelLike"),
    DISLIKE(2, "dislike"),
    CANCELDISLIKE(3, "cancelDisLike");

    private Integer code;

    private String msg;

    private VoteStatus(Integer code, String msg) {
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
