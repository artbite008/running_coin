package com.running.coins.controller;

import com.running.coins.model.request.SubmitUserSportRecordRequest;
import com.running.coins.model.response.ResponseMessage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningController {

    @ApiOperation(value = "submit users' running records", notes = "submit records")
    @ApiImplicitParam(name = "voteRequest", value = "Request body of user list", required = true, dataType = "VoteRequest")
    @PostMapping("/submit/sport/record")
    public ResponseMessage submitRecord (@RequestBody SubmitUserSportRecordRequest submitUserSportRecordRequest) {
        return null;
    }

    @ApiOperation(value = "submit users' running target", notes = "submit target")
    @ApiImplicitParam(name = "voteRequest", value = "Request body of user list", required = true, dataType = "VoteRequest")
    @PostMapping("/submit/sport/record")
    public ResponseMessage submitTarget (@RequestBody SubmitUserSportRecordRequest submitUserSportRecordRequest) {
        return null;
    }

}
