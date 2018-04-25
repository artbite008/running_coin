package com.running.coins.model.response;

import com.running.coins.model.transition.UserRecord;
import lombok.Data;

import java.util.List;

@Data
public class WeeklyReportResponse {
    private String timeRange;
    List<UserRecord> allWeeklyRecords;
}
