package com.running.coins.job;

import com.running.coins.common.util.DateUtils;
import com.running.coins.common.util.ThisLocalizedWeek;
import com.running.coins.dao.*;
import com.running.coins.model.*;
import com.running.coins.model.transition.MailBean;
import com.running.coins.model.transition.UserInfoBatchBean;
import com.running.coins.service.MailService;
import freemarker.template.Template;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.*;

/**
 * VoteCountjob
 *
 * @author guxiang
 * @date 2018/4/25
 */
@Configuration
@EnableScheduling
public class VoteCountJob {


    @Autowired
    private VoteRecordMapper voteRecordMapper;

    @Autowired
    private RunningRecordMapper runningRecordMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private MostVotedJob mostVotedJob;
    @Autowired
    private DailyMostVotedRecordMapper dailyMostVotedRecordMapper;

    @Autowired
    private MostVotedRecordMapper mostVotedRecordMapper;



    /**
     * 设置定时任务  每天 23:00:00 统计
     */
    @Scheduled(cron = "00 00 23 * * ?")
    public void executeVoteCount() {

        System.err.println("begin to start Job");
        List<RunningRecordWithInfo> runningRecordWithInfos = runningRecordMapper.selectRunningRecordWithInfoScoreIn24hours();

        List<MailBean> mailBeanList = new ArrayList();
        for (RunningRecordWithInfo runningRecordWithInfo : runningRecordWithInfos) {
            System.out.println(runningRecordWithInfo);

            MailBean mailBean = new MailBean();
            mailBean.setDistance(runningRecordWithInfo.getDistance());
            mailBean.setUsername(runningRecordWithInfo.getUsername());
            mailBean.setCreationTime(DateUtils.parseForFrontEnd2(runningRecordWithInfo.getCreationTime()));


            RunningRecord runningRecord = runningRecordWithInfo;
            runningRecord.setScore(runningRecordWithInfo.getFinalScore());
            if (runningRecord.getScore() == null) {
                runningRecord.setStatus(1);
                mailBean.setStatus("Expired");
            } else if (runningRecord.getScore() <= 0) {
                runningRecord.setStatus(2);
                mailBean.setStatus("Rejected");
            } else if (runningRecord.getScore() > 0) {
                runningRecord.setStatus(3);
                mailBean.setStatus("Passed");
                mailBean.setEarnedCoins(runningRecordWithInfo.getEarnedCoins());
            }

            mailBeanList.add(mailBean);
            runningRecordMapper.updateByPrimaryKey(runningRecord);
        }


        mostVotedJob.insertDailyVotedCount();


        List<DailyVotedCountVo> dailyVotedCountVos = mostVotedRecordMapper.selectByVotedDate(new Date());

        dailyVotedCountVos.stream().sorted((Comparator.comparing(DailyVotedCountVo::getVotedDate)));

        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        List<WeeklyAwardedReportVo> weeklyAwardedReportVos = dailyMostVotedRecordMapper.selectWeeklyAwardedRecord(thisLocalizedWeek.getFirstDay(), thisLocalizedWeek.getLastDay());

        List<UserInfoBatchBean> userInfoBatchBeans = userInfoMapper.selectUserTotalInfo();
        for (UserInfoBatchBean userInfoBatchBean : userInfoBatchBeans) {
            UserInfo userInfo = userInfoMapper.selectByOpenId(userInfoBatchBean.getUserOpenId());
            userInfo.setTotalDistance(userInfoBatchBean.getTotalDistance());
            userInfo.setCoins(userInfoBatchBean.getTotalCoins());
            userInfoMapper.updateByPrimaryKey(userInfo);
        }
        List<UserInfo> userInfos = userInfoMapper.selectAllUser();
        try {
            mailService.sendMessageMail(mailBeanList,userInfos,dailyVotedCountVos,weeklyAwardedReportVos, "RunningClub Report", "message.ftl");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.err.println("send email finished");
    }

}
