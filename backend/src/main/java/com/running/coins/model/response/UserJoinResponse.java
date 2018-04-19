package com.running.coins.model.response;

import com.running.coins.model.UserInfo;
import com.running.coins.model.transition.UserRecord;
import lombok.Data;

import java.util.List;

@Data
public class UserJoinResponse {
    private UserRecord userRecord;
    private List<UserRecord> otherUsersRecord;
}
