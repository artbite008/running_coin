package com.running.coins.service;

import com.running.coins.common.Exception.CommonException;
import com.running.coins.common.enums.ResultEnum;
import com.running.coins.common.enums.RunningCoins;
import com.running.coins.common.enums.SportRecordStatus;
import com.running.coins.common.util.DateUtils;
import com.running.coins.common.util.ResultUtils;
import com.running.coins.common.util.ThisLocalizedWeek;
import com.running.coins.dao.RunningRecordMapper;
import com.running.coins.dao.TargetDistanceMapper;
import com.running.coins.dao.UserGroupMapper;
import com.running.coins.dao.UserInfoMapper;
import com.running.coins.model.RunningRecord;
import com.running.coins.model.TargetDistance;
import com.running.coins.model.UserGroup;
import com.running.coins.model.UserInfo;
import com.running.coins.model.request.SubmitUserSportRecordRequest;
import com.running.coins.model.request.SubmitUserSportTargetRequest;
import com.running.coins.model.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    private UserInfoMapper userInfoMapper;

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
                    selectByGroupIdAndUserOpenId(submitUserSportTargetRequest.getGroupId(),
                            submitUserSportTargetRequest.getUserOpenId()).getUserGroupId();
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
            Calendar cal = Calendar.getInstance();
            int weekdayNum = cal.get(Calendar.DAY_OF_WEEK);
            if (weekdayNum==1 ||weekdayNum==7 ||weekdayNum==6  ){
                throw new CommonException(ResultEnum.Target_ERROR);
            }
            targetDistance.setCreationTime(DateUtils.parse(new Date()));
            targetDistance.setTargetDistance(Float.valueOf(submitUserSportTargetRequest.getTargetDistance()));
            targetDistanceMapper.updateByPrimaryKey(targetDistance);
        }
        return ResultUtils.success(targetDistance);
    }

    public ResponseMessage submitSportRecord(SubmitUserSportRecordRequest submitUserSportRecordRequest) {

        /** 为了晶姐啊！！！！  */
        if (submitUserSportRecordRequest.getUserOpenId() == null || "".equals(submitUserSportRecordRequest.getUserOpenId())) {
            submitUserSportRecordRequest.setUserOpenId("otvlM5Qw9YG3uyTWr5pGdkgD9cdk");
        }

        RunningRecord runningRecord = new RunningRecord();
        runningRecord.setCreationTime(DateUtils.parse(new Date()));
        runningRecord.setDistance(Float.valueOf(submitUserSportRecordRequest.getDistance()));
        UserGroup userGroup = userGroupMapper.selectByGroupIdAndUserOpenId(submitUserSportRecordRequest.getGroupId(), submitUserSportRecordRequest.getUserOpenId());
        runningRecord.setUserGroupId(userGroup.getUserGroupId());
        runningRecord.setStatus(SportRecordStatus.SUBMITTED.getCode());
        CountEarnedCoins(submitUserSportRecordRequest, runningRecord);

        /**
         * 判断单个用户最新打卡的时间 两次打卡间隔 必须超过1小时
         * 如果用户在1小时内保存了两次，则以最新的一次为准，用户可用以修改distance
         */
        List<RunningRecord> runningRecordsInOneHour = recordMapper.selectRunningRecordLatestHourByUserGroupId(runningRecord.getUserGroupId());
        if (runningRecordsInOneHour.size()!=0){
            RunningRecord runningRecordChange = runningRecordsInOneHour.get(0);
            Float oldDistance = runningRecordChange.getDistance();
            Float newDistance = Float.valueOf(submitUserSportRecordRequest.getDistance());

            runningRecordChange.setDistance(newDistance);
            int i = recordMapper.updateByPrimaryKey(runningRecordChange);

            return ResultUtils.error(400,"Change Distance From "+oldDistance+" to "+newDistance);
        }

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
