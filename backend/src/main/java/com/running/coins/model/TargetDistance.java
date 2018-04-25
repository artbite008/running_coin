package com.running.coins.model;

import lombok.Data;

import java.util.Date;

@Data
public class TargetDistance {
    private Integer TargetDistanceId;

    private Integer userGroupId;

    private Date creationTime;

    private Float targetDistance;

}