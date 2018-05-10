package com.running.coins.model;

public class TempUserInfoForOpenId {
    private Integer oldUserId;

    private String openId;

    private String sessionKey;

    public Integer getOldUserId() {
        return oldUserId;
    }

    public void setOldUserId(Integer oldUserId) {
        this.oldUserId = oldUserId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey == null ? null : sessionKey.trim();
    }
}