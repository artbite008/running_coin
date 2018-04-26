package com.running.coins.job;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.running.coins.dao.RunningRecordMapper;
import com.running.coins.dao.VoteRecordMapper;
import com.running.coins.model.RunningRecord;
import com.running.coins.model.RunningRecordWithfinalScore;
import com.running.coins.model.VoteRecord;
import com.running.coins.service.RunningInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JobTest
 *
 * @author guxiang
 * @date 2018/4/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {

    @Autowired
    private RunningRecordMapper runningRecordMapper;

    @Autowired
    private VoteRecordMapper voteRecordMapper;

    @Test
    public void testJob() {
        Date date = new Date();

//        /** 1 取出所有人24小时内的跑步记录*/
//        List<RunningRecord> runningRecords = runningRecordMapper.selectRunningUserByRecordWithIn24hours(date);
//
//        /** 2 分别对跑步记录 在24小时内进行的投票的结果的清算*/
//        for (RunningRecord runningRecord : runningRecords) {
//            List<VoteRecord> voteRecords = voteRecordMapper.selectByRunningRecordIdAndLimitedTime(runningRecord.getRuningRecordId(), date);
//            int grade = 0;
//            for (VoteRecord voteRecord : voteRecords) {
//                if (voteRecord.getStatus() == -1) {
//
//                }
//            }
//        }


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
