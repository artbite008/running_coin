package com.running.coins.dao;

import com.running.coins.model.RunningRecord;
import org.apache.ibatis.jdbc.SQL;

public class RunningRecordSqlProvider {

    public String insertSelective(RunningRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("Running_Record");
        
        if (record.getRuningRecordId() != null) {
            sql.VALUES("RuningRecordId", "#{runingRecordId,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("UserId", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getGroupId() != null) {
            sql.VALUES("GroupId", "#{groupId,jdbcType=INTEGER}");
        }
        
        if (record.getDistance() != null) {
            sql.VALUES("Distance", "#{distance,jdbcType=REAL}");
        }
        
        if (record.getCreationTime() != null) {
            sql.VALUES("CreationTime", "#{creationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastVotedTime() != null) {
            sql.VALUES("LastVotedTime", "#{lastVotedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("Status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getScore() != null) {
            sql.VALUES("Score", "#{score,jdbcType=INTEGER}");
        }
        
        if (record.getSettledTime() != null) {
            sql.VALUES("SettledTime", "#{settledTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEarnedCoins() != null) {
            sql.VALUES("EarnedCoins", "#{earnedCoins,jdbcType=INTEGER}");
        }
        
        if (record.getComments() != null) {
            sql.VALUES("Comments", "#{comments,jdbcType=VARCHAR}");
        }
        
        if (record.getEvidence() != null) {
            sql.VALUES("Evidence", "#{evidence,jdbcType=LONGVARBINARY}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RunningRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("Running_Record");
        
        if (record.getDistance() != null) {
            sql.SET("Distance = #{distance,jdbcType=REAL}");
        }
        
        if (record.getCreationTime() != null) {
            sql.SET("CreationTime = #{creationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastVotedTime() != null) {
            sql.SET("LastVotedTime = #{lastVotedTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("Status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getScore() != null) {
            sql.SET("Score = #{score,jdbcType=INTEGER}");
        }
        
        if (record.getSettledTime() != null) {
            sql.SET("SettledTime = #{settledTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEarnedCoins() != null) {
            sql.SET("EarnedCoins = #{earnedCoins,jdbcType=INTEGER}");
        }
        
        if (record.getComments() != null) {
            sql.SET("Comments = #{comments,jdbcType=VARCHAR}");
        }
        
        if (record.getEvidence() != null) {
            sql.SET("Evidence = #{evidence,jdbcType=LONGVARBINARY}");
        }
        
        sql.WHERE("RuningRecordId = #{runingRecordId,jdbcType=INTEGER}");
        sql.WHERE("UserId = #{userId,jdbcType=INTEGER}");
        sql.WHERE("GroupId = #{groupId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}