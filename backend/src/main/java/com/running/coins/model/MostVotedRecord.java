package com.running.coins.model;

import java.util.Date;

public class MostVotedRecord {
    private Integer mostVotedId;

    private Integer userGroupId;

    private Integer votedCount;

    private Date votedDate;

    private Integer coinGiveStatus;

    private Integer groupId;

    public Integer getMostVotedId() {
        return mostVotedId;
    }

    public void setMostVotedId(Integer mostVotedId) {
        this.mostVotedId = mostVotedId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getVotedCount() {
        return votedCount;
    }

    public void setVotedCount(Integer votedCount) {
        this.votedCount = votedCount;
    }

    public Date getVotedDate() {
        return votedDate;
    }

    public void setVotedDate(Date votedDate) {
        this.votedDate = votedDate;
    }

    public Integer getCoinGiveStatus() {
        return coinGiveStatus;
    }

    public void setCoinGiveStatus(Integer coinGiveStatus) {
        this.coinGiveStatus = coinGiveStatus;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}