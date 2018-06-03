package com.running.coins.model.request;

import lombok.Data;

@Data
public class UserJoinRequest {
    private String userName;
    private int groupId;
    private String icon;
    private String jsCode;
}
