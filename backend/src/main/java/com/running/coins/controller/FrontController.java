package com.running.coins.controller;

import com.running.coins.model.request.CurrentUserWeeklyReportRequest;
import com.running.coins.model.request.UserJoinRequest;
import com.running.coins.model.request.WeeklyReportRequest;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.service.FrontServices;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/front")
public class FrontController {

    private final
    FrontServices frontServices;

    @Autowired
    public FrontController(FrontServices frontServices) {
        this.frontServices = frontServices;
    }

    @PostMapping("/user/weekly/report")
    @ApiOperation(value = "", notes = "")
    @ApiImplicitParam(name = "currentUserWeeklyReportRequest", value = "", required = true, dataType = "CurrentUserWeeklyReportRequest")
    public ResponseMessage getUserWeeklyReport(@RequestBody CurrentUserWeeklyReportRequest currentUserWeeklyReportRequest) {
        return frontServices.currentUserWeekly(currentUserWeeklyReportRequest);
    }

    @PostMapping("/everyone/weekly/report")
    @ApiOperation(value = "", notes = "")
    @ApiImplicitParam(name = "weeklyReportRequest", required = true, dataType = "WeeklyReportRequest")
    public ResponseMessage getEveryoneWeeklyReport(@RequestBody WeeklyReportRequest weeklyReportRequest) {
        return frontServices.everyOneWeekly(weeklyReportRequest);
    }

    @ApiOperation(value = "This is a api invoked immediately when a user authorizes RunningCoins", notes = "Join the Running Club, will return an unique Id for this user")
    @ApiImplicitParam(name = "userJoinRequest", value = "Request body of user join", required = true, dataType = "UserJoinRequest")
    @PostMapping("/user/join")
    public ResponseMessage userJoin(@RequestBody UserJoinRequest userJoinRequest) {
        ResponseMessage responseMessage = frontServices.userJoin(userJoinRequest);
        return responseMessage;
    }

}
