package com.running.coins.model.request;

import lombok.Data;

@Data
public class SubmitUserSportRecordRequest {
    private Integer userId;
    private Integer distance;
    private Integer groupId;
    private String evidence;


}
