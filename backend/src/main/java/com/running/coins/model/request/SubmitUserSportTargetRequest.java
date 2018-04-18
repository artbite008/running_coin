package com.running.coins.model.request;

import lombok.Data;

@Data
public class SubmitUserSportTargetRequest {
    private Integer userId;
    private String userGroupId;
    private Integer targetDistance;
}
