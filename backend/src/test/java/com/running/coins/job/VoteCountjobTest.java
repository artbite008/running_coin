package com.running.coins.job;

import com.running.coins.dao.RunningRecordMapper;
import com.running.coins.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * VoteCountjobTest
 *
 * @author guxiang
 * @date 2018/4/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteCountjobTest {


    @Autowired
    private VoteCountJob voteCountjob;

    /**
     * for maven test
     */
    @Test
    public void executeVoteCount() {
       voteCountjob.executeVoteCount();
    }
}