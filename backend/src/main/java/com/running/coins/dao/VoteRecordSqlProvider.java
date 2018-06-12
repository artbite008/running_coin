package com.running.coins.dao;

import com.running.coins.model.VoteRecord;
import org.apache.ibatis.jdbc.SQL;

public class VoteRecordSqlProvider {

    public String insertSelective(VoteRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("Vote_Record");
        
        if (record.getVoteRecordId() != null) {
            sql.VALUES("VoteRecordId", "#{voteRecordId,jdbcType=INTEGER}");
        }
        
        if (record.getVoteUserGroupId() != null) {
            sql.VALUES("VoteUserGroupId", "#{voteUserGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getRuningRecordId() != null) {
            sql.VALUES("RuningRecordId", "#{runingRecordId,jdbcType=INTEGER}");
        }
        
        if (record.getVotedTime() != null) {
            sql.VALUES("VotedTime", "#{votedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedTime() != null) {
            sql.VALUES("UpdatedTime", "#{updatedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("Status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getScore() != null) {
            sql.VALUES("Score", "#{score,jdbcType=INTEGER}");
        }
        
        if (record.getComments() != null) {
            sql.VALUES("Comments", "#{comments,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VoteRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("Vote_Record");
        
        if (record.getVoteUserGroupId() != null) {
            sql.SET("VoteUserGroupId = #{voteUserGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getRuningRecordId() != null) {
            sql.SET("RuningRecordId = #{runingRecordId,jdbcType=INTEGER}");
        }
        
        if (record.getVotedTime() != null) {
            sql.SET("VotedTime = #{votedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatedTime() != null) {
            sql.SET("UpdatedTime = #{updatedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("Status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getScore() != null) {
            sql.SET("Score = #{score,jdbcType=INTEGER}");
        }
        
        if (record.getComments() != null) {
            sql.SET("Comments = #{comments,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("VoteRecordId = #{voteRecordId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}