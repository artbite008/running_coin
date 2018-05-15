package com.running.coins.model.request;

import lombok.Data;

@Data
public class SubmitUserSportRecordRequest {
    private Integer userId;
    private Float distance;
    private Integer groupId;
    private String evidence;


}
