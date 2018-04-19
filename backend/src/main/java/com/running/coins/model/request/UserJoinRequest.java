package com.running.coins.model.request;

import lombok.Data;

@Data
public class UserJoinRequest {
    private String userName;
    private int groupId;
    private int unionId;
    private String icon;
}
