package com.running.coins.job;

import com.running.coins.dao.RunningRecordMapper;
import com.running.coins.dao.VoteRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JobTest
 *
 * @author guxiang
 * @date 2018/4/26
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JobTest {

    @Autowired
    private RunningRecordMapper runningRecordMapper;

    @Autowired
    private VoteRecordMapper voteRecordMapper;

    /**
     * 防止 maven package 的时候执行 test
     */
 /*  @Test
    public void testJob() {
        Date date = new Date();


        List<RunningRecordWithInfo> runningRecordWithfinalScores = runningRecordMapper.selectRunningRecordWithInfoScoreIn24hours();

        for (RunningRecordWithInfo runningRecordWithfinalScore : runningRecordWithfinalScores) {
            System.out.println(runningRecordWithfinalScore);

            RunningRecord runningRecord = runningRecordWithfinalScore;
            runningRecord.setScore(runningRecordWithfinalScore.getFinalScore());
            if (runningRecord.getScore()==null){
                runningRecord.setStatus(1);
            }else if (runningRecord.getScore()<=0){
                runningRecord.setStatus(2);
            }else if (runningRecord.getScore()>0){
                runningRecord.setStatus(3);
            }

            runningRecordMapper.updateByPrimaryKey(runningRecord);
        }


    }*/
}
