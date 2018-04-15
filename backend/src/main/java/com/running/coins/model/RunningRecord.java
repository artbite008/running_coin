package com.running.coins.model;

import java.util.Date;

public class RunningRecord {
    private Integer runingRecordId;

    private Integer userId;

    private Float distance;

    private Date creationTime;

    private Date lastVotedTime;

    private String status;

    private Integer score;

    private Date settledTime;

    private Integer earnedCoins;

    private String comments;

    private byte[] evidence;

    public Integer getRuningRecordId() {
        return runingRecordId;
    }

    public void setRuningRecordId(Integer runingRecordId) {
        this.runingRecordId = runingRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastVotedTime() {
        return lastVotedTime;
    }

    public void setLastVotedTime(Date lastVotedTime) {
        this.lastVotedTime = lastVotedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getSettledTime() {
        return settledTime;
    }

    public void setSettledTime(Date settledTime) {
        this.settledTime = settledTime;
    }

    public Integer getEarnedCoins() {
        return earnedCoins;
    }

    public void setEarnedCoins(Integer earnedCoins) {
        this.earnedCoins = earnedCoins;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public byte[] getEvidence() {
        return evidence;
    }

    public void setEvidence(byte[] evidence) {
        this.evidence = evidence;
    }
}