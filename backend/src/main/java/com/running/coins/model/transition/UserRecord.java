package com.running.coins.model.transition;

import lombok.Data;

import java.util.List;

@Data
public class UserRecord {
    private Integer runningRecordId;
    private Integer userId;
    private Integer userGroupId;
    private Integer groupId;
    private String nickName;
    private Double coins;
    private Float current;
    private Float target;
    private Float distance;
    private Integer likes;
    private Integer dislikes;
    private String date;
    private Float latestRecord;
    private List<Integer> achievements;
    private Integer allAchievements;
    private Integer allLikes;
    private Integer allDislikes;
    private Boolean voted;
    private String color;
    private String icon;
}
