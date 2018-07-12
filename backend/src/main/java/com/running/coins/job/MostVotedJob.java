package com.running.coins.job;

import com.running.coins.dao.MostVotedRecordMapper;
import com.running.coins.dao.UserGroupMapper;
import com.running.coins.dao.VoteRecordMapper;
import com.running.coins.model.MostVotedRecord;
import com.running.coins.model.UserGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.List;

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

    public void insertDailyVotedCount() {
        List<UserGroup> userGroups = userGroupMapper.selectByGroupId(1);

        /**
         * 统计每人每天的投票数
         */
        for (UserGroup userGroup : userGroups) {
            Integer count = voteRecordMapper.selectDailyVotedCountByGroupUserId(userGroup.getUserGroupId());
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

        /**
         * 统计应该是谁获取coin
         */


    }
}
