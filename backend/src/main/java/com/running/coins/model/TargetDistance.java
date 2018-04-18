package com.running.coins.model;

import java.util.Date;

public class TargetDistance {
    private Integer targetDistanceId;

    private Integer userGroupId;

    private Date creationTime;

    private Float targetDistance;

    public Integer getTargetDistanceId() {
        return targetDistanceId;
    }

    public void setTargetDistanceId(Integer targetDistanceId) {
        this.targetDistanceId = targetDistanceId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Float getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(Float targetDistance) {
        this.targetDistance = targetDistance;
    }
}