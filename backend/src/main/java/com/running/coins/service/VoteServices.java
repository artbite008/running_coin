package com.running.coins.service;

import com.running.coins.common.util.ResultUtils;
import com.running.coins.common.enums.VoteStatus;
import com.running.coins.common.util.DateUtils;
import com.running.coins.dao.RunningRecordMapper;
import com.running.coins.dao.UserGroupMapper;
import com.running.coins.dao.VoteRecordMapper;
import com.running.coins.model.VoteRecord;
import com.running.coins.model.request.VoteRequest;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.model.response.VoteStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VoteServices {

    private final static Logger logger = LoggerFactory.getLogger(VoteServices.class);

    private final
    VoteRecordMapper voteRecordMapper;
    private final
    RunningRecordMapper runningRecordMapper;
    private final
    UserGroupMapper userGroupMapper;

    @Autowired
    public VoteServices(VoteRecordMapper voteRecordMapper,
                        RunningRecordMapper runningRecordMapper,
                        UserGroupMapper userGroupMapper) {
        this.voteRecordMapper = voteRecordMapper;
        this.runningRecordMapper = runningRecordMapper;
        this.userGroupMapper = userGroupMapper;
    }

    public ResponseMessage vote(VoteRequest voteRequest) {
        int userGroupId;
        if (voteRequest.getVoteUserGroupId() == null || voteRequest.getVoteUserGroupId() == 0) {
            userGroupId = userGroupMapper.selectByGroupIdAndUserOpenId
                    (voteRequest.getGroupId(),
                            voteRequest.getVoteOpenId()).getUserGroupId();
            voteRequest.setVoteUserGroupId(userGroupId);
        } else {
            userGroupId = voteRequest.getVoteUserGroupId();
        }
        VoteRecord voteRecord = voteRecordMapper.
                selectByVoteUserIdAndRuningRecordId(voteRequest.getRunningRecordId(), userGroupId);
        if (voteRecord == null) {
            voteRecord = new VoteRecord();
            setVoteRecord(voteRequest, voteRecord);
        } else {
            setMutableField(voteRequest, voteRecord);
            voteRecord.setUpdatedTime(DateUtils.parse(new Date()));
            voteRecordMapper.updateByPrimaryKey(voteRecord);
        }
        return ResultUtils.success(voteRecord);
    }

    /**
     * query for vote record using running Record Id and user group Id
     * @param runningRecordId runningRecordId
     * @param voteUserGroupId userGroupId
     * @return VoteStatusResponse
     */
    public ResponseMessage queryVoteRecord(Integer runningRecordId, Integer voteUserGroupId) {
        VoteRecord vr = voteRecordMapper.selectByVoteUserIdAndRuningRecordId(runningRecordId, voteUserGroupId);
        int voteStatus;
        if(vr == null) {
            voteStatus = VoteStatus.NOTYET.getCode();
        } else {
            voteStatus = vr.getStatus();
        }
        return ResultUtils.success(VoteStatusResponse.builder().voteRecord(vr).voteStatus(voteStatus).build());
    }

    private void setVoteRecord(VoteRequest voteRequest, VoteRecord voteRecord) {
        if (voteRecord.getStatus() == null) {
            voteRecord.setRuningRecordId(voteRequest.getRunningRecordId());
            voteRecord.setVoteUserGroupId(voteRequest.getVoteUserGroupId());
            setMutableField(voteRequest, voteRecord);
            voteRecord.setVotedTime(DateUtils.parse(new Date()));
            voteRecordMapper.insert(voteRecord);
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
