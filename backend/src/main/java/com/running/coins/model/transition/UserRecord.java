package com.running.coins.model.transition;

import lombok.Data;

@Data
public class UserRecord {
    private String userName;
    private String coins;
    private String currentDistance;
    private String planDistance;
    private String likes;
    private String dislikes;
    private String date;
}
