package com.running.coins.model;

import java.util.Date;

public class VoteRecord {
    private Integer voteRecordId;

    private Integer voteUserGroupId;

    private Integer runingRecordId;

    private Date votedTime;

    private Date updatedTime;

    private Integer status;

    private Integer score;

    private String comments;

    public Integer getVoteRecordId() {
        return voteRecordId;
    }

    public void setVoteRecordId(Integer voteRecordId) {
        this.voteRecordId = voteRecordId;
    }

    public Integer getVoteUserGroupId() {
        return voteUserGroupId;
    }

    public void setVoteUserGroupId(Integer voteUserGroupId) {
        this.voteUserGroupId = voteUserGroupId;
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

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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