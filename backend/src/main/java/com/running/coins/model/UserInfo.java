package com.running.coins.model;

import lombok.Data;

@Data
public class UserInfo {
    private Integer userId;

    private String userName;

    private String status;

    private String role;

    private Double coins;

    private Float totalDistance;

    private String metaData;

    private String icon;

}