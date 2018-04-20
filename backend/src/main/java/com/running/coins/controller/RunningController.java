package com.running.coins.controller;

import com.running.coins.model.request.SubmitUserSportRecordRequest;
import com.running.coins.model.request.SubmitUserSportTargetRequest;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.service.RunningInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningController {

    @Autowired
    RunningInfoService runningInfoService;

    @ApiOperation(value = "submit users' running records", notes = "submit records")
    @ApiImplicitParam(name = "submitUserSportRecordRequest", value = "", required = true, dataType = "SubmitUserSportRecordRequest")
    @PostMapping("/submit/sport/record")
    public ResponseMessage submitRecord (@RequestBody SubmitUserSportRecordRequest submitUserSportRecordRequest) {
        ResponseMessage responseMessage = runningInfoService.submitSportRecord(submitUserSportRecordRequest);
        return responseMessage;
    }

    @ApiOperation(value = "submit users' running target", notes = "submit target")
    @ApiImplicitParam(name = "submitUserSportTargetRequest", value = "", required = true, dataType = "SubmitUserSportTargetRequest")
    @PostMapping("/submit/sport/target")
    public ResponseMessage submitTarget (@RequestBody SubmitUserSportTargetRequest submitUserSportTargetRequest) {
        ResponseMessage responseMessage = runningInfoService.submitTarget(submitUserSportTargetRequest);
        return responseMessage;
    }

}
