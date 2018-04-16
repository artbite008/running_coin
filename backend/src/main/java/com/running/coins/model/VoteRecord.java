package com.running.coins.model;

import java.util.Date;

public class VoteRecord {
    private Integer voteRecordId;

    private Integer voteUserId;

    private Integer runingRecordId;

    private Date votedTime;

    private Date canceledTime;

    private String status;

    private Integer score;

    private String comments;

    public Integer getVoteRecordId() {
        return voteRecordId;
    }

    public void setVoteRecordId(Integer voteRecordId) {
        this.voteRecordId = voteRecordId;
    }

    public Integer getVoteUserId() {
        return voteUserId;
    }

    public void setVoteUserId(Integer voteUserId) {
        this.voteUserId = voteUserId;
    }

    public Integer getRuningRecordId() {
        return runingRecordId;
    }

    public void setRuningRecordId(Integer runingRecordId) {
        this.runingRecordId = runingRecordId;
    }

    public Date getVotedTime() {
        return votedTime;
    }

    public void setVotedTime(Date votedTime) {
        this.votedTime = votedTime;
    }

    public Date getCanceledTime() {
        return canceledTime;
    }

    public void setCanceledTime(Date canceledTime) {
        this.canceledTime = canceledTime;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}