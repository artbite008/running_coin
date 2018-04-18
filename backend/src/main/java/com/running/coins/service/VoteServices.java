package com.running.coins.service;

import com.running.coins.common.util.ResultUtils;
import com.running.coins.common.enums.VoteStatus;
import com.running.coins.common.util.DateUtils;
import com.running.coins.dao.VoteRecordMapper;
import com.running.coins.model.VoteRecord;
import com.running.coins.model.request.VoteRequest;
import com.running.coins.model.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VoteServices {

    private final static Logger logger = LoggerFactory.getLogger(VoteServices.class);

    @Autowired
    VoteRecordMapper voteRecordMapper;

    public ResponseMessage vote(VoteRequest voteRequest) {
        VoteRecord voteRecord = voteRecordMapper.
                selectByVoteUserIdAndRuningRecordId(voteRequest.getVoteUserId(),
                        voteRequest.getRunningRecordId());
        if (voteRecord == null) {
            voteRecord = new VoteRecord();
            SetVoteRecord(voteRequest, voteRecord);
        } else {

        }
        return ResultUtils.success();
    }

    private void SetVoteRecord(VoteRequest voteRequest, VoteRecord voteRecord) {
        if (voteRecord.getStatus() == null) {
            voteRecord.setRuningRecordId(voteRequest.getRunningRecordId());
            voteRecord.setVoteUserId(voteRecord.getVoteUserId());
            voteRecord.setGroupId(voteRequest.getGroupId());
            setMutableField(voteRequest, voteRecord);
            voteRecord.setVotedTime(DateUtils.parse(new Date()));
            voteRecordMapper.insert(voteRecord);
        } else {
            setMutableField(voteRequest, voteRecord);
            voteRecord.setUpdatedTime(DateUtils.parse(new Date()));
            voteRecordMapper.updateByPrimaryKey(voteRecord);
        }
    }

    private void setMutableField(VoteRequest voteRequest, VoteRecord voteRecord) {
        voteRecord.setStatus(voteRequest.getStatus());
        if (VoteStatus.LIKE.getCode().equals(voteRequest.getStatus())) {
            voteRecord.setScore(1);
        } else if (VoteStatus.DISLIKE.getCode().equals(voteRequest.getStatus())) {
            voteRecord.setScore(-1);
        } else {
            voteRecord.setScore(0);
        }
    }

}
