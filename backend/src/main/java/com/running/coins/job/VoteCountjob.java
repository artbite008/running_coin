package com.running.coins.job;

import com.running.coins.common.util.DateUtils;
import com.running.coins.dao.RunningRecordMapper;
import com.running.coins.dao.VoteRecordMapper;
import com.running.coins.model.RunningRecord;
import com.running.coins.model.RunningRecordWithfinalScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * VoteCountjob
 *
 * @author guxiang
 * @date 2018/4/25
 */
@Configuration
@EnableScheduling
public class VoteCountjob {


    @Autowired
    private VoteRecordMapper voteRecordMapper;

    @Autowired
    private RunningRecordMapper runningRecordMapper;
    /**
     * 设置定时任务  每天 23:00:00 统计
     */
    @Scheduled(cron = "00 00 23 * * ?")
    public void executeVoteCount() {

        List<RunningRecordWithfinalScore> runningRecordWithfinalScores = runningRecordMapper.selectRunningRecordWithfinalScoreIn24hours();

        for (RunningRecordWithfinalScore runningRecordWithfinalScore : runningRecordWithfinalScores) {
            System.out.println(runningRecordWithfinalScore);

            RunningRecord runningRecord = runningRecordWithfinalScore;
            runningRecord.setScore(runningRecordWithfinalScore.getFinalscore());
            if (runningRecord.getScore()==null){
                runningRecord.setStatus(1);
            }else if (runningRecord.getScore()<=0){
                runningRecord.setStatus(2);
            }else if (runningRecord.getScore()>0){
                runningRecord.setStatus(3);
            }

            runningRecordMapper.updateByPrimaryKey(runningRecord);
        }

    }
}
