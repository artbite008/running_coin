package com.running.coins.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * MostVotedJobTest
 *
 * @author guxiang
 * @date 2018/6/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MostVotedJobTest {

    @Autowired
    private MostVotedJob mostVotedJob;

    @Test
    public void insertDailyVotedCount() {
        mostVotedJob.insertDailyVotedCount();
    }

    @Test
    public void calculateThePersonToGetCoin() {
        mostVotedJob.calculateThePersonToGetCoin();
    }
}