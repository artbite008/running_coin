package com.running.coins.service;

import com.running.coins.common.enums.RunningCoins;
import com.running.coins.common.enums.SportRecordStatus;
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

    private final
    TargetDistanceMapper targetDistanceMapper;
    private final
    RunningRecordMapper recordMapper;
    private final
    UserGroupMapper userGroupMapper;

    @Autowired
    public RunningInfoService(TargetDistanceMapper targetDistanceMapper,
                              RunningRecordMapper recordMapper,
                              UserGroupMapper userGroupMapper) {
        this.targetDistanceMapper = targetDistanceMapper;
        this.recordMapper = recordMapper;
        this.userGroupMapper = userGroupMapper;
    }

    public ResponseMessage submitTarget(SubmitUserSportTargetRequest submitUserSportTargetRequest) {
        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        TargetDistance targetDistance;
        int userGroupId;
        if (submitUserSportTargetRequest.getUserGroupId() == null
                || submitUserSportTargetRequest.getUserGroupId() == 0) {
            userGroupId = userGroupMapper.
                    selectByGroupIdAndUserId(submitUserSportTargetRequest.getGroupId(),
                            submitUserSportTargetRequest.getUserId()).getUserGroupId();
        } else {
            userGroupId = submitUserSportTargetRequest.getUserGroupId();
        }
        targetDistance = targetDistanceMapper.selectByUserGroupIdAndTimeRange(
                userGroupId,
                thisLocalizedWeek.getFirstDay(), thisLocalizedWeek.getLastDay());

        if (targetDistance == null) {
            targetDistance = new TargetDistance();
            targetDistance.setTargetDistance(Float.valueOf(submitUserSportTargetRequest.getTargetDistance()));
            targetDistance.setCreationTime(DateUtils.parse(new Date()));
            targetDistance.setUserGroupId(userGroupId);
            targetDistanceMapper.insert(targetDistance);
        } else {
            targetDistance.setCreationTime(DateUtils.parse(new Date()));
            targetDistance.setTargetDistance(Float.valueOf(submitUserSportTargetRequest.getTargetDistance()));
            targetDistanceMapper.updateByPrimaryKey(targetDistance);
        }
        return ResultUtils.success(targetDistance);
    }

    public ResponseMessage submitSportRecord(SubmitUserSportRecordRequest submitUserSportRecordRequest) {
        RunningRecord runningRecord = new RunningRecord();
        runningRecord.setCreationTime(DateUtils.parse(new Date()));
        runningRecord.setDistance(Float.valueOf(submitUserSportRecordRequest.getDistance()));
        UserGroup userGroup = userGroupMapper.selectByGroupIdAndUserId(submitUserSportRecordRequest.getGroupId(), submitUserSportRecordRequest.getUserId());
        runningRecord.setUserGroupId(userGroup.getUserGroupId());
        runningRecord.setStatus(SportRecordStatus.SUBMITTED.getCode());
        CountEarnedCoins(submitUserSportRecordRequest, runningRecord);
        recordMapper.insert(runningRecord);
        return ResultUtils.success(runningRecord);
    }

    private void CountEarnedCoins(SubmitUserSportRecordRequest submitUserSportRecordRequest, RunningRecord runningRecord) {
        if (submitUserSportRecordRequest.getDistance() <= RunningCoins.FOUR_KM.getDistance()) {
            runningRecord.setEarnedCoins(Double.valueOf(RunningCoins.FOUR_KM.getEarnCoins()));
        } else {
            runningRecord.setEarnedCoins(Double.valueOf(RunningCoins.FIVE_KM.getEarnCoins()));
        }
    }
}
