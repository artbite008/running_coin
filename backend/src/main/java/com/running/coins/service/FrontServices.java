package com.running.coins.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.running.coins.common.enums.ResultEnum;
import com.running.coins.common.enums.RunningProgress;
import com.running.coins.common.enums.VoteStatus;
import com.running.coins.common.util.DateUtils;
import com.running.coins.common.util.ResultUtils;
import com.running.coins.common.util.ThisLocalizedWeek;
import com.running.coins.dao.*;
import com.running.coins.model.*;
import com.running.coins.model.request.CurrentUserWeeklyReportRequest;
import com.running.coins.model.request.UserJoinRequest;
import com.running.coins.model.request.WeeklyReportRequest;
import com.running.coins.model.response.CurrentUserWeeklyReportResponse;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.model.response.UserJoinResponse;
import com.running.coins.model.response.WeeklyReportResponse;
import com.running.coins.model.transition.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.running.coins.common.util.DateUtils.parseForFrontEnd1;

@Service
public class FrontServices {

    private final static Logger logger = LoggerFactory.getLogger(FrontServices.class);

    ImmutableMap<String, Integer> immutableDaysMap = ImmutableMap.<String, Integer>builder()
            .put("MON", 0)
            .put("TUE", 1)
            .put("WED", 2)
            .put("THU", 3)
            .put("FRI", 4)
            .put("SAT", 5)
            .put("SUN", 6)
            .build();

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    TargetDistanceMapper targetDistanceMapper;

    @Autowired
    RunningRecordMapper runningRecordMapper;

    @Autowired
    VoteRecordMapper voteRecordMapper;

    public ResponseMessage userJoin(UserJoinRequest userJoinRequest) {
        UserJoinResponse userJoinResponse = new UserJoinResponse();
        ResponseMessage responseMessage = new ResponseMessage();
        UserInfo userInfo;
        UserGroup userGroup;
        try {
            userInfo = userInfoMapper.selectByPrimaryKey(userJoinRequest.getUnionId());
            if (userInfo == null) {
                userGroup = new UserGroup();
                userInfo = new UserInfo();
                SetUserInfo(userJoinRequest, userInfo);
                SetUserGroup(userJoinRequest, userGroup);
                userInfoMapper.insert(userInfo);
                userGroupMapper.insert(userGroup);
            }

            setUserJoinResponse(userJoinRequest, userInfo, userJoinResponse);

            responseMessage.setMsg(ResultEnum.SUCCESS.getMsg());
            responseMessage.setCode(ResultEnum.SUCCESS.getCode());
            responseMessage.setData(userJoinResponse);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            responseMessage.setMsg("Error occurs in inserting into DB" + e.getMessage());
            responseMessage.setCode(ResultEnum.INSERT_ERROR.getCode());
            responseMessage.setData(userJoinResponse);
        }
        return responseMessage;
    }

    public ResponseMessage currentUserWeekly(CurrentUserWeeklyReportRequest currentUserWeeklyReportRequest) {
        CurrentUserWeeklyReportResponse currentUserWeeklyReportResponse = new CurrentUserWeeklyReportResponse();
        UserGroup userGroup = userGroupMapper.selectByGroupIdAndUserId(currentUserWeeklyReportRequest.getGroupId(), currentUserWeeklyReportRequest.getUserId());
        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        Date start = thisLocalizedWeek.getFirstDay();
        Date end = thisLocalizedWeek.getLastDay();
        List<RunningRecord> runningRecords = runningRecordMapper.selectByUserGroupIdAndTimeRange(
                userGroup.getUserGroupId(),
                DateUtils.parse(start),
                DateUtils.parse(end));
        String rangeOfTime = parseForFrontEnd1(start) + " to " + parseForFrontEnd1(end);
        currentUserWeeklyReportResponse.setTimeRange(rangeOfTime);
        List<UserRecord> currentUserWeeklyReportList = new LinkedList<>();
        for (RunningRecord runningRecord : runningRecords) {
            UserRecord userRecord = new UserRecord();
            userRecord.setRunningRecordId(runningRecord.getRuningRecordId());
            userRecord.setDate(DateUtils.parseForFrontEnd(runningRecord.getCreationTime()) + "");
            userRecord.setDistance(runningRecord.getDistance());
            List<VoteRecord> voteRecords = voteRecordMapper.selectByRuningRecordId(runningRecord.getRuningRecordId());
            int likes = 0;
            int dislikes = 0;
            for (VoteRecord voteRecord : voteRecords) {
                if (voteRecord.getStatus().equals(VoteStatus.LIKE.getCode())) {
                    likes++;
                } else if (voteRecord.equals(VoteStatus.DISLIKE.getCode())) {
                    dislikes++;
                }
            }
            userRecord.setLikes(likes);
            userRecord.setDislikes(dislikes);
            currentUserWeeklyReportList.add(userRecord);
        }
        currentUserWeeklyReportResponse.setUserRecords(currentUserWeeklyReportList);
        return ResultUtils.success(currentUserWeeklyReportResponse);
    }

    public ResponseMessage everyOneWeekly(WeeklyReportRequest weeklyReportRequest) {
        WeeklyReportResponse weeklyReportResponse = new WeeklyReportResponse();
        List<UserRecord> userRecords = Lists.newLinkedList();
        List<UserGroup> userGroups = userGroupMapper.selectByGroupId(weeklyReportRequest.getGroupId());
        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        for (UserGroup userGroup : userGroups) {
            List<Integer> achievements = new ArrayList<>(Collections.nCopies(7, 0));
            Float allAchievements = 0f;
            int allLikes = 0;
            int allDislikes = 0;
            Map<Integer, Float> dayAchievementMap = Maps.newHashMap();
            UserRecord userRecord = new UserRecord();
            Date start = thisLocalizedWeek.getFirstDay();
            Date end = thisLocalizedWeek.getLastDay();
            List<RunningRecord> runningRecords = runningRecordMapper.selectByUserGroupIdAndTimeRange(
                    userGroup.getUserGroupId(),
                    DateUtils.parse(start),
                    DateUtils.parse(end));
            for (RunningRecord runningRecord : runningRecords) {
                allAchievements += runningRecord.getDistance();
                List<VoteRecord> voteRecords = voteRecordMapper.selectByRuningRecordId(runningRecord.getRuningRecordId());
                for (VoteRecord voteRecord : voteRecords) {
                    if (voteRecord.getStatus().equals(VoteStatus.LIKE.getCode())) {
                        allLikes++;
                    } else if (voteRecord.equals(VoteStatus.DISLIKE.getCode())) {
                        allDislikes++;
                    }
                }
                String createDate = parseForFrontEnd1(runningRecord.getCreationTime()).toUpperCase();
                Integer whichDay = immutableDaysMap.get(createDate.substring(11));
                if (dayAchievementMap.containsKey(whichDay)) {
                    float temp = dayAchievementMap.get(whichDay);
                    dayAchievementMap.put(whichDay, temp + runningRecord.getDistance());
                } else {
                    dayAchievementMap.put(whichDay, runningRecord.getDistance());
                }
                achievements.set(whichDay, dayAchievementMap.get(whichDay).intValue());
            }
            userRecord.setUserGroupId(userGroup.getUserGroupId());
            userRecord.setUserId(userGroup.getUserId());
            userRecord.setGroupId(userGroup.getGroupId());
            userRecord.setLikes(allLikes);
            userRecord.setDislikes(allDislikes);
            userRecord.setAchievements(achievements);
            userRecord.setAllAchievements(allAchievements.intValue());
            userRecords.add(userRecord);
        }
        weeklyReportResponse.setAllWeeklyRecords(userRecords);
        return ResultUtils.success(weeklyReportResponse);
    }

    private void setUserJoinResponse(UserJoinRequest userJoinRequest, UserInfo userInfo, UserJoinResponse userJoinResponse) {
        List<UserRecord> userRecords = new LinkedList<>();
        List<UserGroup> usersInGroup = userGroupMapper.selectByGroupId(userJoinRequest.getGroupId());
        for (UserGroup userInGroup : usersInGroup) {
            float current = 0l;
            final ThisLocalizedWeek chinaWeek = new ThisLocalizedWeek(Locale.CHINA);
            int likes = 0;
            int dislikes = 0;
            float lastRecord = 0l;
            UserRecord userRecord = new UserRecord();

            UserInfo userInformation = userInfoMapper.selectByPrimaryKey(userInGroup.getUserId());
            List<RunningRecord> runningRecords = runningRecordMapper.selectByUserGroupIdAndTimeRange(userInGroup.getUserGroupId(), chinaWeek.getFirstDay(), chinaWeek.getLastDay());
            TargetDistance targetDistance = targetDistanceMapper.selectByUserGroupIdAndTimeRange(userInGroup.getUserGroupId(), chinaWeek.getFirstDay(), chinaWeek.getLastDay());

            for (int i = 0; i < runningRecords.size(); i++) {
                List<VoteRecord> voteRecords = voteRecordMapper.selectByRuningRecordId(runningRecords.get(i).getRuningRecordId());
                for (int j = 0; j < voteRecords.size(); j++) {
                    if (voteRecords.get(j).getStatus().equals(VoteStatus.LIKE.getCode())) {
                        likes++;
                    } else if (voteRecords.get(j).getStatus().equals(VoteStatus.DISLIKE.getCode())) {
                        dislikes++;
                    }
                }
                current += runningRecords.get(i).getDistance();
                if (i == runningRecords.size() - 1) {
                    lastRecord = runningRecords.get(i).getDistance();
                }
            }
            userRecord = setUserRecords(userInGroup, userRecord, current, userInformation, targetDistance, lastRecord, likes, dislikes);

            if (userInfo.getUserId().equals(userInGroup.getUserId())) {
                userJoinResponse.setUserRecord(userRecord);
            } else {
                userRecords.add(userRecord);
            }
        }
        userJoinResponse.setOtherUsersRecord(userRecords);
    }

    private UserRecord setUserRecords(UserGroup userInGroup, UserRecord userRecord, float current, UserInfo userInformation, TargetDistance targetDistance, float lastRecord, int likes, int dislikes) {
        float rate;
        if (targetDistance != null) {
            rate = ((current / (targetDistance.getTargetDistance())) * 100);
            userRecord.setTarget(targetDistance.getTargetDistance());
        } else {
            rate = 0l;
        }
        if (rate <= RunningProgress.ERROR.getCode()) {
            userRecord.setColor(RunningProgress.ERROR.getMsg());
        } else if (rate > RunningProgress.ERROR.getCode() && rate <= RunningProgress.WARNING.getCode()) {
            userRecord.setColor(RunningProgress.WARNING.getMsg());
        } else if (rate > RunningProgress.WARNING.getCode() && rate <= RunningProgress.SUCCESS.getCode()) {
            userRecord.setColor(RunningProgress.WARNING.getMsg());
        } else {
            userRecord.setColor(RunningProgress.SUCCESS.getMsg());
        }
        userRecord.setUserGroupId(userInGroup.getUserGroupId());
        userRecord.setUserId(userInformation.getUserId());
        userRecord.setNickName(userInformation.getUserName());
        userRecord.setCoins(userInformation.getCoins());
        userRecord.setLatestRecord(lastRecord);
        userRecord.setLikes(likes);
        userRecord.setDislikes(dislikes);
        return userRecord;
    }

    private void SetUserGroup(UserJoinRequest userJoinRequest, UserGroup userGroup) {
        userGroup.setGroupId(userJoinRequest.getGroupId());
        userGroup.setUserId(userJoinRequest.getUnionId());
    }

    private void SetUserInfo(UserJoinRequest userJoinRequest, UserInfo userInfo) {
        userInfo.setUserId(userJoinRequest.getUnionId());
        userInfo.setUserName(userJoinRequest.getUserName());
        userInfo.setCoins(0d);
        userInfo.setRole("member");
        userInfo.setStatus("active");
        userInfo.setTotalDistance(0.00f);
        userInfo.setIcon(userJoinRequest.getIcon());
    }

}
