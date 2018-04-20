package com.running.coins.model;

import lombok.Data;

import java.util.Date;

@Data
public class RunningRecord {
    private Integer RuningRecordId;

    private Integer userGroupId;

    private Float distance;

    private Date creationTime;

    private Date lastVotedTime;

    private Integer status;

    private Integer score;

    private Date settledTime;

    private Double earnedCoins;

    private String comments;

    private String evidence;

}