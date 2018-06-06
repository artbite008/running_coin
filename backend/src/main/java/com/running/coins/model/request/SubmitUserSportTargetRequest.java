package com.running.coins.model.request;

import lombok.Data;

@Data
public class SubmitUserSportTargetRequest {
    private String userOpenId;
    private Integer userGroupId;
    private Integer targetDistance;
    private Integer groupId;
}