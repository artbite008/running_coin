package com.running.coins.service;

import com.alibaba.fastjson.JSON;
import com.running.coins.model.request.UserJoinRequest;
import com.running.coins.model.request.WeeklyReportRequest;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.model.response.WeeklyReportResponse;
import com.running.coins.model.transition.UserRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FrontServicesTest {

    @Autowired
    private FrontServices frontServices;

    @Test
    public void userJoinv2() {
        UserJoinRequest userJoinRequest = new UserJoinRequest();
        userJoinRequest.setGroupId(1);
        userJoinRequest.setOpenId("otvlM5VATjthdCeLQVfEDAzzBsro");
        //userJoinRequest.setOpenId("otvlM5Q9wQPdL196IYlwImhBuRYM");
        long v1begin = System.currentTimeMillis();
        ResponseMessage responseMessage = frontServices.userJoin(userJoinRequest);
        long v1end = System.currentTimeMillis();
        System.err.println("old: "+ (v1end-v1begin));
        System.err.println(JSON.toJSON(responseMessage));

        long v2begin = System.currentTimeMillis();
        ResponseMessage responseMessage2 = frontServices.userJoinv2(userJoinRequest);
        long v2end = System.currentTimeMillis();
        System.err.println(v2end-v2begin);
        System.err.println(JSON.toJSON(responseMessage2));

        long v3begin = System.currentTimeMillis();
        ResponseMessage responseMessage3 = frontServices.userJoinv3(userJoinRequest);
        long v3end = System.currentTimeMillis();
        System.err.println(v3end-v3begin);
        System.err.println(JSON.toJSON(responseMessage3));

    }

    @Test
    public void everyOneWeekly() {
        WeeklyReportRequest weeklyReportRequest = new WeeklyReportRequest();
        weeklyReportRequest.setGroupId(1);

        long v1begin = System.currentTimeMillis();
        ResponseMessage responseMessage = frontServices.everyOneWeekly(weeklyReportRequest);
        long v1end = System.currentTimeMillis();
        System.err.println("old: "+ (v1end-v1begin));
        System.err.println(JSON.toJSON(responseMessage));

        long v2begin = System.currentTimeMillis();
        ResponseMessage responseMessage2 = frontServices.everyOneWeekly2(weeklyReportRequest);
        long v2end = System.currentTimeMillis();
        System.err.println("parallel: "+ (v2end-v2begin));
        WeeklyReportResponse weeklyReportResponse = (WeeklyReportResponse) responseMessage2.getData();
        List<UserRecord> allWeeklyRecords = weeklyReportResponse.getAllWeeklyRecords();

        allWeeklyRecords.stream().forEach(System.out::println);
        System.err.println(JSON.toJSON(responseMessage2));
    }
}