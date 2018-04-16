package com.running.coins.model.transition;

import lombok.Data;

import java.util.List;

@Data
public class Records {

    private UserRecord currentUserRecord;
    private List<UserRecord> userRecords;
}
