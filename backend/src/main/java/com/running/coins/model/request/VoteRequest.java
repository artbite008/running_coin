package com.running.coins.model.request;

import lombok.Data;

@Data
public class VoteRequest {
    private Integer voteUserId;
    private Integer groupId;
    private Integer voteUserGroupId;
    private Integer status;
    private Integer runningRecordId;
}
