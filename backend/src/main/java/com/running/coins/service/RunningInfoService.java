package com.running.coins.service;

import com.running.coins.common.enums.RunningCoins;
import com.running.coins.common.util.DateUtils;
import com.running.coins.common.util.ResultUtils;
import com.running.coins.common.util.ThisLocalizedWeek;
import com.running.coins.dao.RunningRecordMapper;
import com.running.coins.dao.TargetDistanceMapper;
import com.running.coins.dao.UserGroupMapper;
import com.running.coins.model.RunningRecord;
import com.running.coins.model.TargetDistance;
import com.running.coins.model.UserGroup;
import com.running.coins.model.request.SubmitUserSportRecordRequest;
import com.running.coins.model.request.SubmitUserSportTargetRequest;
import com.running.coins.model.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
public class RunningInfoService {

    @Autowired
    TargetDistanceMapper targetDistanceMapper;
    @Autowired
    RunningRecordMapper recordMapper;
    @Autowired
    UserGroupMapper userGroupMapper;

    public ResponseMessage submitTarget(SubmitUserSportTargetRequest submitUserSportTargetRequest) {
        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        TargetDistance targetDistance = targetDistanceMapper.selectByUserGroupIdAndTimeRange(
                submitUserSportTargetRequest.getUserGroupId(),
                thisLocalizedWeek.getFirstDay(), thisLocalizedWeek.getLastDay());
        if (targetDistance == null) {
            targetDistance = new TargetDistance();
            targetDistance.setTargetDistance(Float.valueOf(submitUserSportTargetRequest.getTargetDistance()));
            targetDistance.setCreationTime(DateUtils.parse(new Date()));
            targetDistance.setUserGroupId(submitUserSportTargetRequest.getUserGroupId());
            targetDistanceMapper.insert(targetDistance);
        } else {
            targetDistance.setCreationTime(DateUtils.parse(new Date()));
            targetDistance.setTargetDistance(Float.valueOf(submitUserSportTargetRequest.getTargetDistance()));
            targetDistanceMapper.updateByPrimaryKey(targetDistance);
        }
        return ResultUtils.success();
    }

    public ResponseMessage submitSportRecord(SubmitUserSportRecordRequest submitUserSportRecordRequest) {
        RunningRecord runningRecord = new RunningRecord();
        runningRecord.setCreationTime(DateUtils.parse(new Date()));
        runningRecord.setDistance(submitUserSportRecordRequest.getDistance());
        UserGroup userGroup = userGroupMapper.selectByGroupIdAndUserId(submitUserSportRecordRequest.getGroupId(), submitUserSportRecordRequest.getUserId());
        runningRecord.setUserGroupId(userGroup.getUserGroupId());
        CountEarnedCoins(submitUserSportRecordRequest, runningRecord);
        recordMapper.insert(runningRecord);
        return ResultUtils.success();
    }

    private void CountEarnedCoins(SubmitUserSportRecordRequest submitUserSportRecordRequest, RunningRecord runningRecord) {
        if (submitUserSportRecordRequest.getDistance() <= RunningCoins.FOUR_KM.getDistance()) {
            runningRecord.setEarnedCoins(Double.valueOf(RunningCoins.FOUR_KM.getEarnCoins()));
        } else {
            runningRecord.setEarnedCoins(Double.valueOf(RunningCoins.FIVE_KM.getEarnCoins()));
        }
    }
}
