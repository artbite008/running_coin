package com.running.coins.model.request;

import lombok.Data;

@Data
public class SubmitUserSportTargetRequest {
    private Integer userId;
    private Integer userGroupId;
    private Integer targetDistance;
}
