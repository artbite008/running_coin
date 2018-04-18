package com.running.coins.model;

import lombok.Data;

import java.util.Date;

@Data
public class RunningRecord extends RunningRecordKey {
    private Float distance;

    private Date creationTime;

    private Date lastVotedTime;

    private String status;

    private Integer score;

    private Date settledTime;

    private Double earnedCoins;

    private String comments;

    private String evidence;


}