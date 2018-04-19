package com.running.coins.model.request;

import lombok.Data;

@Data
public class CurrentUserWeeklyReportRequest {
    private Integer userId;
    private Integer groupId;
}
