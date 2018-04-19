package com.running.coins.model.response;

import com.running.coins.model.transition.UserRecord;
import lombok.Data;

import java.util.List;

@Data
public class CurrentUserWeeklyReportResponse {
    private String timeRange;
    private List<UserRecord> userRecords;
}
