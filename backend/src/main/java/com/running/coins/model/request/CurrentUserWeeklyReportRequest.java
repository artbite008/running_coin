package com.running.coins.model.request;

import lombok.Data;

@Data
public class CurrentUserWeeklyReportRequest {
    private String userOpenId;
    private Integer groupId;
}
