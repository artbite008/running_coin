package com.running.coins.job;

import com.running.coins.common.util.DateUtils;
import com.running.coins.common.util.ThisLocalizedWeek;
import com.running.coins.dao.TargetDistanceMapper;
import com.running.coins.model.TargetDistance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * TargetFreshJob
 *
 * @author guxiang
 * @date 2018/5/15
 */
@Configuration
@EnableScheduling
@Slf4j
public class TargetFreshJob {


    @Autowired
    private TargetDistanceMapper targetDistanceMapper;

    /**
     * 设置定时任务  每周一 00:00:01 执行
     */
    @Scheduled(cron = "1 0 0 ? * 1")
    public void executeTargetFresh() {

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("Fresh Target Job start ："+df.format(day));

        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);

        List<TargetDistance> targetDistances = targetDistanceMapper.selectByTimeRange(thisLocalizedWeek.getPreFirstDay(), thisLocalizedWeek.getPreLastDay());

        for (TargetDistance targetDistance : targetDistances) {


            /**
             * 为了保证数据一周只能有一次 先删除然后再插入
             */
            List<TargetDistance> targetDistanceolds = targetDistanceMapper.selectByUserGroupIdAndTimeRangeReturnList(targetDistance.getUserGroupId(), thisLocalizedWeek.getFirstDay(), thisLocalizedWeek.getLastDay());
            if (targetDistanceolds!=null){
                for (TargetDistance targetDistanceold : targetDistanceolds) {
                    targetDistanceMapper.deleteByPrimaryKey(targetDistanceold.getTargetDistanceId());
                }
            }

            targetDistance.setCreationTime(new Date());
            targetDistance.setTargetDistanceId(null);
            log.info("Generate Target"+ targetDistance.toString());
            targetDistanceMapper.insert(targetDistance);
        }

        day = new Date();
        log.info("Fresh Target Job Finished ："+df.format(day));

    }
}
