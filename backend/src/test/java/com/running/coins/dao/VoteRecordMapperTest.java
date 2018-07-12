package com.running.coins.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * VoteRecordMapperTest
 *
 * @author guxiang
 * @date 2018/6/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteRecordMapperTest {
    @Autowired
    VoteRecordMapper voteRecordMapper;

    @Test
    public void selectDailyVotedCountByGroupUserId() {
      //  Integer count = voteRecordMapper.selectDailyVotedCountByGroupUserId(7);
        //System.out.println(count);
    }
}