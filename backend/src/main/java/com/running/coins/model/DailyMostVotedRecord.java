package com.running.coins.model;

public class DailyMostVotedRecord {
    private Integer dailyMostRecordId;

    private Integer mostVotedUserGroupId;

    private Integer earnCoin;

    private Integer weekilyStatus;

    public Integer getDailyMostRecordId() {
        return dailyMostRecordId;
    }

    public void setDailyMostRecordId(Integer dailyMostRecordId) {
        this.dailyMostRecordId = dailyMostRecordId;
    }

    public Integer getMostVotedUserGroupId() {
        return mostVotedUserGroupId;
    }

    public void setMostVotedUserGroupId(Integer mostVotedUserGroupId) {
        this.mostVotedUserGroupId = mostVotedUserGroupId;
    }

    public Integer getEarnCoin() {
        return earnCoin;
    }

    public void setEarnCoin(Integer earnCoin) {
        this.earnCoin = earnCoin;
    }

    public Integer getWeekilyStatus() {
        return weekilyStatus;
    }

    public void setWeekilyStatus(Integer weekilyStatus) {
        this.weekilyStatus = weekilyStatus;
    }
}