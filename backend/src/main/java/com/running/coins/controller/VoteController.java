package com.running.coins.controller;

import com.running.coins.model.request.VoteRequest;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.service.VoteServices;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteController {

    private final
    VoteServices voteServices;

    @Autowired
    public VoteController(VoteServices voteServices) {
        this.voteServices = voteServices;
    }

    @ApiOperation(value = "Vote for users' running records", notes = "vote")
    @ApiImplicitParam(name = "voteRequest", value = "Request body of user list", required = true, dataType = "VoteRequest")
    @PostMapping("/vote")
    public ResponseMessage vote(@RequestBody VoteRequest voteRequest) {
        ResponseMessage responseMessage = voteServices.vote(voteRequest);
        return responseMessage;
    }

    @ApiOperation(value = "get user' voting records", notes = "vote")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "runningRecordId", value = "running record Id which may have been voted", required = true, dataType = "int"),
            @ApiImplicitParam(name = "userGroupId", value = "vote record which binds to userGroupId", required = true, dataType = "int")
    })
    @GetMapping("/vote")
    public ResponseMessage getVoteRecord(@RequestParam("runningRecordId") int runningRecordId,
                                         @RequestParam("voteUserGroupId") int voteUserGroupId) {
        return voteServices.queryVoteRecord(runningRecordId, voteUserGroupId);
    }
}
