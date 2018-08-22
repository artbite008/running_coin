package com.running.coins.job;

import com.running.coins.common.util.DateUtils;
import com.running.coins.common.util.ThisLocalizedWeek;
import com.running.coins.dao.DailyMostVotedRecordMapper;
import com.running.coins.dao.MostVotedRecordMapper;
import com.running.coins.dao.UserGroupMapper;
import com.running.coins.dao.VoteRecordMapper;
import com.running.coins.model.DailyMostVotedRecord;
import com.running.coins.model.MostVotedRecord;
import com.running.coins.model.UserGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * MostVotedJob
 *
 * @author guxiang
 * @date 2018/6/17
 */
@Configuration
@EnableScheduling
@Slf4j
public class MostVotedJob {
    @Autowired
    private VoteRecordMapper voteRecordMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private MostVotedRecordMapper mostVotedRecordMapper;

    @Autowired
    private DailyMostVotedRecordMapper dailyMostVotedRecordMapper;

    public void insertDailyVotedCount() {
        List<UserGroup> userGroups = userGroupMapper.selectByGroupId(1);
        /**
         * 统计每人每天的投票数
         */
        for (UserGroup userGroup : userGroups) {
            Integer count = voteRecordMapper.selectDailyVotedCountByGroupUserId(userGroup.getUserGroupId(),new Date());
            MostVotedRecord mostVotedRecord = mostVotedRecordMapper.selectByVotedDateAndUserGroupId(new Date(), userGroup.getUserGroupId());
            if (mostVotedRecord == null) {
                mostVotedRecord = new MostVotedRecord();
                mostVotedRecord.setGroupId(1);
                mostVotedRecord.setUserGroupId(userGroup.getUserGroupId());
                mostVotedRecord.setVotedCount(count);
                mostVotedRecord.setCoinGiveStatus(0);
                mostVotedRecord.setVotedDate(new Date());
                mostVotedRecordMapper.insertSelective(mostVotedRecord);
            }else {
                mostVotedRecord.setVotedCount(count);
                mostVotedRecordMapper.updateByPrimaryKeySelective(mostVotedRecord);
            }
        }

        calculateThePersonToGetCoin();
    }
    /**
     * 统计应该是谁获取coin
     */
    public void calculateThePersonToGetCoin(){
        final ThisLocalizedWeek chinaWeek = new ThisLocalizedWeek(Locale.CHINA);
        List<MostVotedRecord> mostVotedRecords = mostVotedRecordMapper.selectThePersonShouldBeAward(chinaWeek.getFirstDay(), chinaWeek.getLastDay(), new Date());
        for (MostVotedRecord mostVotedRecord : mostVotedRecords) {
            DailyMostVotedRecord dailyMostVotedRecord = dailyMostVotedRecordMapper.selectByawardDateAndMostVotedUserGroupId(new Date(), mostVotedRecord.getUserGroupId());
            if (dailyMostVotedRecord == null) {
                dailyMostVotedRecord = new DailyMostVotedRecord();
                dailyMostVotedRecord.setMostVotedUserGroupId(mostVotedRecord.getUserGroupId());
                dailyMostVotedRecord.setEarnCoin(1);
                dailyMostVotedRecord.setAwardDate(new Date());
                dailyMostVotedRecordMapper.insertSelective(dailyMostVotedRecord);
            }else {
                /** 感觉这句不要也可以诶  不过强迫症就加一下吧(#^.^#)*/
                dailyMostVotedRecord.setEarnCoin(1);
                dailyMostVotedRecordMapper.updateByPrimaryKey(dailyMostVotedRecord);
            }
        }

    }
}
