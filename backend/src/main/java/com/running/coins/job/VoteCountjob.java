package com.running.coins.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * VoteCountjob
 *
 * @author guxiang
 * @date 2018/4/25
 */
@Configuration
@EnableScheduling
public class VoteCountjob {



    @Scheduled(cron = "59 59 23 * * ?")
    public void execute() {

    }
}
