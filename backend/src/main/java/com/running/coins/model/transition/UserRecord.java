package com.running.coins.model.transition;

import lombok.Data;

import java.util.List;

@Data
public class UserRecord {
    private Integer runningRecordId;
    private Integer userId;
    private Integer groupId;
    private String nickName;
    private Double coins;
    private String current;
    private String target;
    private String likes;
    private String dislikes;
    private String date;
    private String latestRecord;
    private List<Integer> achievements;
    private Integer allAchievements;
    private Integer allLikes;
    private Integer allDislikes;
    private boolean voted;
}
