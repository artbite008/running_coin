package com.running.coins.model;

import lombok.Data;

import java.util.Date;

@Data
public class VoteRecord {
    private Integer voteRecordId;

    private Integer voteUserId;

    private Integer groupId;

    private Integer runingRecordId;

    private Date votedTime;

    private Date updatedTime;

    private Integer status;

    private Integer score;

    private String comments;

    }