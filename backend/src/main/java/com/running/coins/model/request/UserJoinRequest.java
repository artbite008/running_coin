package com.running.coins.model.request;

import lombok.Data;

@Data
public class UserJoinRequest {
    private String userName;
    private Integer groupId;
    private String icon;
    private String openId;
}
