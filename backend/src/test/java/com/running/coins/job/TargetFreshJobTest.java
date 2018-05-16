package com.running.coins.job;

import com.running.coins.dao.TargetDistanceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * TargetFreshJobTest
 *
 * @author guxiang
 * @date 2018/5/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetFreshJobTest {



    @Autowired
    private TargetDistanceMapper targetDistanceMapper;

    @Autowired
    private TargetFreshJob targetFreshJob;

    @Test
    public void executeTargetFresh() {
        //targetFreshJob.executeTargetFresh();
    }
}