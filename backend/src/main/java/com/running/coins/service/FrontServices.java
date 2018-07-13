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
import jdk.nashorn.internal.ir.IfNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.running.coins.common.util.DateUtils.parseForFrontEnd1;

@Service
@Slf4j
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
            .put("星期一", 0)
            .put("星期二", 1)
            .put("星期三", 2)
            .put("星期四", 3)
            .put("星期五", 4)
            .put("星期六", 5)
            .put("星期日", 6)
            .build();

    private final
    UserInfoMapper userInfoMapper;

    private final
    UserGroupMapper userGroupMapper;

    private final
    TargetDistanceMapper targetDistanceMapper;

    private final
    RunningRecordMapper runningRecordMapper;

    private final
    VoteRecordMapper voteRecordMapper;

    @Autowired
    public FrontServices(UserInfoMapper userInfoMapper,
                         UserGroupMapper userGroupMapper,
                         TargetDistanceMapper targetDistanceMapper,
                         RunningRecordMapper runningRecordMapper,
                         VoteRecordMapper voteRecordMapper) {
        this.userInfoMapper = userInfoMapper;
        this.userGroupMapper = userGroupMapper;
        this.targetDistanceMapper = targetDistanceMapper;
        this.runningRecordMapper = runningRecordMapper;
        this.voteRecordMapper = voteRecordMapper;
    }

    public ResponseMessage userJoin(UserJoinRequest userJoinRequest) {
        log.info(userJoinRequest.toString());
        UserJoinResponse userJoinResponse = new UserJoinResponse();
        ResponseMessage responseMessage = new ResponseMessage();
        UserInfo userInfo;
        UserGroup userGroup;
        TargetDistance targetDistance;
        try {
            userInfo = userInfoMapper.selectByOpenId(userJoinRequest.getOpenId());

            setUserJoinResponse(userJoinRequest, userInfo, userJoinResponse);

            responseMessage.setMsg(ResultEnum.SUCCESS.getMsg());
            responseMessage.setCode(ResultEnum.SUCCESS.getCode());
            responseMessage.setData(userJoinResponse);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            responseMessage.setMsg("Error occurs in inserting into DB" + e.getMessage() + " or " + e);
            responseMessage.setCode(ResultEnum.INSERT_ERROR.getCode());
            responseMessage.setData(userJoinResponse);
        }
        return responseMessage;
    }

    public ResponseMessage userJoinv3(UserJoinRequest userJoinRequest) {
        log.info(userJoinRequest.toString());
        UserJoinResponse userJoinResponse = new UserJoinResponse();
        ResponseMessage responseMessage = new ResponseMessage();
        UserInfo userInfo;
        UserGroup userGroup;
        TargetDistance targetDistance;
        try {
            userInfo = userInfoMapper.selectByOpenId(userJoinRequest.getOpenId());

            setUserJoinResponseV2(userJoinRequest, userInfo, userJoinResponse);

            responseMessage.setMsg(ResultEnum.SUCCESS.getMsg());
            responseMessage.setCode(ResultEnum.SUCCESS.getCode());
            responseMessage.setData(userJoinResponse);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            responseMessage.setMsg("Error occurs in inserting into DB" + e.getMessage() + " or " + e);
            responseMessage.setCode(ResultEnum.INSERT_ERROR.getCode());
            responseMessage.setData(userJoinResponse);
        }
        return responseMessage;
    }

    public ResponseMessage currentUserWeekly(CurrentUserWeeklyReportRequest currentUserWeeklyReportRequest) {
        CurrentUserWeeklyReportResponse currentUserWeeklyReportResponse = new CurrentUserWeeklyReportResponse();
        UserGroup userGroup = userGroupMapper.selectByGroupIdAndUserOpenId(currentUserWeeklyReportRequest.getGroupId(), currentUserWeeklyReportRequest.getUserOpenId());
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
                } else if (voteRecord.getStatus().equals(VoteStatus.DISLIKE.getCode())) {
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
        Date start = thisLocalizedWeek.getFirstDay();
        Date end = thisLocalizedWeek.getLastDay();
        String rangeOfTime = parseForFrontEnd1(start) + " to " + parseForFrontEnd1(end);

        for (UserGroup userGroup : userGroups) {
            handleWeeklyUserInGroup(userRecords, start, end, userGroup);
        }
        weeklyReportResponse.setTimeRange(rangeOfTime);
        weeklyReportResponse.setAllWeeklyRecords(userRecords);
        return ResultUtils.success(weeklyReportResponse);
    }

    public ResponseMessage everyOneWeekly2(WeeklyReportRequest weeklyReportRequest) {
        WeeklyReportResponse weeklyReportResponse = new WeeklyReportResponse();
        List<UserRecord> userRecords = Lists.newLinkedList();
        List<UserGroup> userGroups = userGroupMapper.selectByGroupId(weeklyReportRequest.getGroupId());
        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        Date start = thisLocalizedWeek.getFirstDay();
        Date end = thisLocalizedWeek.getLastDay();
        String rangeOfTime = parseForFrontEnd1(start) + " to " + parseForFrontEnd1(end);

        userGroups.stream().parallel().forEach(userGroup -> {
            handleWeeklyUserInGroup(userRecords, start, end, userGroup);
        });
        weeklyReportResponse.setTimeRange(rangeOfTime);
        weeklyReportResponse.setAllWeeklyRecords(userRecords);
        return ResultUtils.success(weeklyReportResponse);
    }

    private void handleWeeklyUserInGroup(List<UserRecord> userRecords, Date start, Date end, UserGroup userGroup) {
        List<Integer> achievements = new ArrayList<>(Collections.nCopies(7, 0));
        UserInfo userInfo = userInfoMapper.selectByOpenId(userGroup.getUserOpenid());
        TargetDistance targetDistance = targetDistanceMapper.selectByUserGroupIdAndTimeRange(userGroup.getUserGroupId(), start, end);
        Float allAchievements = 0f;
        int allLikes = 0;
        int allDislikes = 0;
        Map<Integer, Float> dayAchievementMap = Maps.newHashMap();
        UserRecord userRecord = new UserRecord();
        List<RunningRecord> runningRecords = runningRecordMapper.selectByUserGroupIdAndTimeRange(
                userGroup.getUserGroupId(),
                DateUtils.parse(start),
                DateUtils.parse(end));
        for (RunningRecord runningRecord : runningRecords) {
            if (runningRecord.getStatus() == 3) {
                allAchievements += runningRecord.getDistance();
            }

            List<VoteRecord> voteRecords = voteRecordMapper.selectByRuningRecordId(runningRecord.getRuningRecordId());
            for (VoteRecord voteRecord : voteRecords) {
                if (voteRecord.getStatus().equals(VoteStatus.LIKE.getCode())) {
                    allLikes++;
                } else if (voteRecord.getStatus().equals(VoteStatus.DISLIKE.getCode())) {
                    allDislikes++;
                }
            }
            String createDate = parseForFrontEnd1(runningRecord.getCreationTime()).toUpperCase();
            Integer whichDay = immutableDaysMap.get(createDate.substring(11));
            if (dayAchievementMap.containsKey(whichDay)) {
                float temp = dayAchievementMap.get(whichDay);
                if (runningRecord.getStatus() == 3) {
                    dayAchievementMap.put(whichDay, temp + runningRecord.getDistance());
                }
            } else {
                if (runningRecord.getStatus() == 3) {
                    dayAchievementMap.put(whichDay, runningRecord.getDistance());
                }
            }
            if (dayAchievementMap.get(whichDay) != null) {
                achievements.set(whichDay, dayAchievementMap.get(whichDay).intValue());
            } else {
                achievements.set(whichDay, 0);
            }

        }
        userRecord.setUserGroupId(userGroup.getUserGroupId());
        userRecord.setUserOpenId(userGroup.getUserOpenid());
        userRecord.setGroupId(userGroup.getGroupId());
        userRecord.setLikes(allLikes);
        userRecord.setDislikes(allDislikes);
        userRecord.setAchievements(achievements);
        userRecord.setAllAchievements(allAchievements.intValue());
        userRecord.setNickName(userInfo.getUserName());
        userRecord.setIcon(userInfo.getIcon());
        userRecord.setTarget(targetDistance == null ? 0 : targetDistance.getTargetDistance());
        userRecords.add(userRecord);
    }

    private void setUserJoinResponse(UserJoinRequest userJoinRequest, UserInfo userInfo, UserJoinResponse userJoinResponse) {
        List<UserRecord> userRecords = new LinkedList<>();
        List<UserGroup> usersInGroup = userGroupMapper.selectByGroupId(userJoinRequest.getGroupId());
        for (UserGroup userInGroup : usersInGroup) {
            float current;
            float distanceValided = 0f;
            float distanceWaitValided = 0f;
            float distanceRejected = 0f;
            float overallDoneDistance = 0f;
            final ThisLocalizedWeek chinaWeek = new ThisLocalizedWeek(Locale.CHINA);
            int likes = 0;
            int dislikes = 0;
            float lastRecord = 0f;
            int status = 0;
            UserRecord userRecord = null;

            UserInfo userInformation = userInfoMapper.selectByOpenId(userInGroup.getUserOpenid());
            List<RunningRecord> runningRecords = runningRecordMapper.selectByUserGroupIdAndTimeRange(userInGroup.getUserGroupId(), chinaWeek.getFirstDay(), chinaWeek.getLastDay());
            TargetDistance targetDistance = targetDistanceMapper.selectByUserGroupIdAndTimeRange(userInGroup.getUserGroupId(), chinaWeek.getFirstDay(), chinaWeek.getLastDay());
            if (runningRecords != null && runningRecords.size() > 0) {
                lastRecord = runningRecords.get(runningRecords.size() - 1).getDistance();
            }

            List<UserRecord> tempRecords = Lists.newLinkedList();
            for (int i = 0; i < runningRecords.size(); i++) {
                userRecord = new UserRecord();

                List<VoteRecord> voteRecords = voteRecordMapper.selectByRuningRecordId(runningRecords.get(i).getRuningRecordId());
                for (int j = 0; j < voteRecords.size(); j++) {
                    if (voteRecords.get(j).getStatus().equals(VoteStatus.LIKE.getCode())) {
                        likes++;
                    } else if (voteRecords.get(j).getStatus().equals(VoteStatus.DISLIKE.getCode())) {
                        dislikes++;
                    }
                }
                if (i == 0) {
                    for (int k = 0; k < runningRecords.size(); k++) {

                        overallDoneDistance += runningRecords.get(k).getDistance();

                        if (runningRecords.get(k).getStatus() == 3) {
                            distanceValided += runningRecords.get(k).getDistance();
                        } else if (runningRecords.get(k).getStatus() == 0) {
                            distanceWaitValided += runningRecords.get(k).getDistance();
                        } else if (runningRecords.get(k).getStatus() == 2) {
                            distanceRejected += runningRecords.get(k).getDistance();
                        }
                    }
                }

                status = runningRecords.get(i).getStatus();
                current = runningRecords.get(i).getDistance();
                userRecord.setRunningRecordId(runningRecords.get(i).getRuningRecordId());
                userRecord.setCurrent(current);
                userRecord = setUserRecords(userInGroup, userRecord, overallDoneDistance, distanceValided, distanceWaitValided, distanceRejected, userInformation, targetDistance, lastRecord, likes, dislikes, status);
                if (!userInfo.getOpenId().equals(userInGroup.getUserOpenid()) && userRecord.getStatus() == 0) {
                    tempRecords.add(userRecord);
                }
            }

            // if no userRecord, enrich userRecord using placeholder info
            if (null == userRecord) {
                userRecord = new UserRecord();
                userRecord = setUserRecords(userInGroup,
                        userRecord,
                        overallDoneDistance,
                        distanceValided,
                        distanceWaitValided,
                        distanceRejected,
                        userInformation,
                        targetDistance,
                        lastRecord,
                        likes,
                        dislikes,
                        status);
            }
            if (userInfo.getOpenId().equals(userInGroup.getUserOpenid())) {
                if (null != targetDistance) { // add null check
                    userRecord.setTarget(targetDistance.getTargetDistance());
                }
                userJoinResponse.setUserRecord(userRecord);
            } else {
                userRecords.addAll(tempRecords);
            }
        }
        userJoinResponse.setOtherUsersRecord(userRecords);
    }

    /**
     * Green  跑步过之后
     *
     * @param userInGroup
     * @param userRecord
     * @param overallDoneDistance
     * @param userInformation
     * @param targetDistance
     * @param lastRecord
     * @param likes
     * @param dislikes
     * @return
     */
    private UserRecord setUserRecords(UserGroup userInGroup, UserRecord userRecord, float overallDoneDistance, float distancevalided, float distanceWaitvalided, float distanceRejected, UserInfo userInformation, TargetDistance targetDistance, float lastRecord, int likes, int dislikes, int status) {
        float rate;
        if (targetDistance != null) {
            rate = ((overallDoneDistance / (targetDistance.getTargetDistance())) * 100);
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
        userRecord.setOverallDoneDistance(overallDoneDistance);
        userRecord.setUserGroupId(userInGroup.getUserGroupId());
        userRecord.setUserOpenId(userInformation.getOpenId());
        userRecord.setNickName(userInformation.getUserName());
        userRecord.setCoins(userInformation.getCoins());
        userRecord.setLatestRecord(lastRecord);
        userRecord.setLikes(likes);
        userRecord.setDislikes(dislikes);
        userRecord.setIcon(userInformation.getIcon());
        userRecord.setDistanceValided(distancevalided);
        userRecord.setDistanceWaitValided(distanceWaitvalided);
        userRecord.setDistanceRejected(distanceRejected);
        userRecord.setStatus(status);
        return userRecord;
    }

    private void setUserGroup(UserJoinRequest userJoinRequest, UserGroup userGroup) {
        userGroup.setGroupId(userJoinRequest.getGroupId());
        userGroup.setUserOpenid(userJoinRequest.getOpenId());
    }

    private void setUserInfo(UserJoinRequest userJoinRequest, UserInfo userInfo) {
        userInfo.setUserName(userJoinRequest.getUserName());
        userInfo.setCoins(0d);
        userInfo.setRole("member");
        userInfo.setStatus("active");
        userInfo.setTotalDistance(0.00f);
        userInfo.setIcon(userJoinRequest.getIcon());
    }


    public ResponseMessage userJoinv2(UserJoinRequest userJoinRequest) {
        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        List<UserRecord> userRecords = runningRecordMapper.selectDailyUserRecord(thisLocalizedWeek.getFirstDay(), thisLocalizedWeek.getLastDay());

        UserJoinResponse userJoinResponse = new UserJoinResponse();
        /** 主要是为了手机没有传入openid的问题*/
        if (userJoinRequest.getOpenId() == null || "".equals(userJoinRequest.getOpenId())) {
            userJoinResponse.setOtherUsersRecord(userRecords);
            return ResultUtils.success(userJoinResponse);
        }

        /** 对 null 值处理 */
        userRecords = userRecords.stream()
                .map(e -> {
                    if (e.getDislikes() == null) {
                        e.setDislikes(0);
                    }
                    if (e.getLikes() == null) {
                        e.setLikes(0);
                    }
                    return e;
                }).collect(Collectors.toList());


        List<UserRecord> collectUserRecord = userRecords.stream().filter(e -> e.getUserOpenId().equals(userJoinRequest.getOpenId())).collect(Collectors.toList());


        if (collectUserRecord.size() > 0) {
            userJoinResponse.setUserRecord(collectUserRecord.get(0));
        } else {

            /** 如果我这周有打卡，那么就拿这个这周打开的数据*/
            UserRecord userRecord1 = runningRecordMapper.selectDailyUserRecordWithOpenId(thisLocalizedWeek.getFirstDay(), thisLocalizedWeek.getLastDay(), userJoinRequest.getOpenId());

            if (userRecord1 != null) {
                if (userRecord1.getDislikes() == null) {
                    userRecord1.setDislikes(0);
                }
                if (userRecord1.getLikes() == null) {
                    userRecord1.setLikes(0);
                }
                userJoinResponse.setUserRecord(userRecord1);
            } else {
                /** sql查询出没『我』的信息的时候 拼凑出0*/
                UserInfo userInfo = userInfoMapper.selectByOpenId(userJoinRequest.getOpenId());
                UserGroup userGroup = userGroupMapper.selectByGroupIdAndUserOpenId(1, userJoinRequest.getOpenId());
                TargetDistance targetDistance = targetDistanceMapper.selectByUserGroupIdAndTimeRange(userGroup.getUserGroupId(), thisLocalizedWeek.getFirstDay(), thisLocalizedWeek.getLastDay());
                UserRecord userRecord = new UserRecord();
                userRecord.setDislikes(0);
                userRecord.setLikes(0);
                userRecord.setCurrent((float) 0);
                userRecord.setDistanceValided((float) 0);
                userRecord.setTarget(targetDistance.getTargetDistance());
                userRecord.setUserGroupId(userGroup.getUserGroupId());
                userRecord.setIcon(userInfo.getIcon());
                userRecord.setNickName(userInfo.getUserName());
                userRecord.setUserOpenId(userInfo.getOpenId());
                userJoinResponse.setUserRecord(userRecord);
            }

        }

        List<UserRecord> collectOtherUserRecord = userRecords.stream().filter(e -> !e.getUserOpenId().equals(userJoinRequest.getOpenId())).collect(Collectors.toList());
        userJoinResponse.setOtherUsersRecord(collectOtherUserRecord);

        return ResultUtils.success(userJoinResponse);
    }


    private void setUserJoinResponseV2(UserJoinRequest userJoinRequest, UserInfo userInfo, UserJoinResponse userJoinResponse) {
        List<UserRecord> userRecords = new LinkedList<>();
        List<UserGroup> usersInGroup = userGroupMapper.selectByGroupId(userJoinRequest.getGroupId());
        usersInGroup.stream().parallel().forEach(userInGroup -> {
            handleUserRecord(userInfo, userJoinResponse, userRecords, userInGroup);
        });
        userJoinResponse.setOtherUsersRecord(userRecords);
    }

    private void handleUserRecord(UserInfo userInfo, UserJoinResponse userJoinResponse, List<UserRecord> userRecords, UserGroup userInGroup) {
        float current;
        float distanceValided = 0f;
        float distanceWaitValided = 0f;
        float distanceRejected = 0f;
        float overallDoneDistance = 0f;
        final ThisLocalizedWeek chinaWeek = new ThisLocalizedWeek(Locale.CHINA);
        int likes = 0;
        int dislikes = 0;
        float lastRecord = 0f;
        int status = 0;
        UserRecord userRecord = null;

        UserInfo userInformation = userInfoMapper.selectByOpenId(userInGroup.getUserOpenid());
        List<RunningRecord> runningRecords = runningRecordMapper.selectByUserGroupIdAndTimeRange(userInGroup.getUserGroupId(), chinaWeek.getFirstDay(), chinaWeek.getLastDay());
        TargetDistance targetDistance = targetDistanceMapper.selectByUserGroupIdAndTimeRange(userInGroup.getUserGroupId(), chinaWeek.getFirstDay(), chinaWeek.getLastDay());
        if (runningRecords != null && runningRecords.size() > 0) {
            lastRecord = runningRecords.get(runningRecords.size() - 1).getDistance();
        }

        List<UserRecord> tempRecords = Lists.newLinkedList();
        for (int i = 0; i < runningRecords.size(); i++) {
            userRecord = new UserRecord();

            List<VoteRecord> voteRecords = voteRecordMapper.selectByRuningRecordId(runningRecords.get(i).getRuningRecordId());
            for (int j = 0; j < voteRecords.size(); j++) {
                if (voteRecords.get(j).getStatus().equals(VoteStatus.LIKE.getCode())) {
                    likes++;
                } else if (voteRecords.get(j).getStatus().equals(VoteStatus.DISLIKE.getCode())) {
                    dislikes++;
                }
            }
            if (i == 0) {
                for (int k = 0; k < runningRecords.size(); k++) {

                    overallDoneDistance += runningRecords.get(k).getDistance();

                    if (runningRecords.get(k).getStatus() == 3) {
                        distanceValided += runningRecords.get(k).getDistance();
                    } else if (runningRecords.get(k).getStatus() == 0) {
                        distanceWaitValided += runningRecords.get(k).getDistance();
                    } else if (runningRecords.get(k).getStatus() == 2) {
                        distanceRejected += runningRecords.get(k).getDistance();
                    }
                }
            }

            status = runningRecords.get(i).getStatus();
            current = runningRecords.get(i).getDistance();
            userRecord.setRunningRecordId(runningRecords.get(i).getRuningRecordId());
            userRecord.setCurrent(current);
            userRecord = setUserRecords(userInGroup, userRecord, overallDoneDistance, distanceValided, distanceWaitValided, distanceRejected, userInformation, targetDistance, lastRecord, likes, dislikes, status);
            if (!userInfo.getOpenId().equals(userInGroup.getUserOpenid()) && userRecord.getStatus() == 0) {
                tempRecords.add(userRecord);
            }
        }

        // if no userRecord, enrich userRecord using placeholder info
        if (null == userRecord) {
            userRecord = new UserRecord();
            userRecord = setUserRecords(userInGroup,
                    userRecord,
                    overallDoneDistance,
                    distanceValided,
                    distanceWaitValided,
                    distanceRejected,
                    userInformation,
                    targetDistance,
                    lastRecord,
                    likes,
                    dislikes,
                    status);
        }
        if (userInfo.getOpenId().equals(userInGroup.getUserOpenid())) {
            if (null != targetDistance) { // add null check
                userRecord.setTarget(targetDistance.getTargetDistance());
            }
            userJoinResponse.setUserRecord(userRecord);
        } else {
            userRecords.addAll(tempRecords);
        }
    }
}
