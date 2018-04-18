package com.running.coins.controller;

import com.running.coins.model.request.VoteRequest;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.service.VoteServices;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {

    @Autowired
    VoteServices voteServices;

    @ApiOperation(value = "Vote for users' running records", notes = "vote")
    @ApiImplicitParam(name = "voteRequest", value = "Request body of user list", required = true, dataType = "VoteRequest")
    @PostMapping("/vote")
    public ResponseMessage vote(@RequestBody VoteRequest voteRequest) {
        ResponseMessage responseMessage = voteServices.vote(voteRequest);
        return responseMessage;
    }

}
