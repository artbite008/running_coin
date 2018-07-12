package com.running.coins.dao;

import com.running.coins.model.MostVotedRecord;
import org.apache.ibatis.jdbc.SQL;

public class MostVotedRecordSqlProvider {

    public String insertSelective(MostVotedRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("MostVoted_Record");
        
        if (record.getMostVotedId() != null) {
            sql.VALUES("MostVotedId", "#{mostVotedId,jdbcType=INTEGER}");
        }
        
        if (record.getUserGroupId() != null) {
            sql.VALUES("UserGroupId", "#{userGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getVotedCount() != null) {
            sql.VALUES("VotedCount", "#{votedCount,jdbcType=INTEGER}");
        }
        
        if (record.getVotedDate() != null) {
            sql.VALUES("VotedDate", "#{votedDate,jdbcType=DATE}");
        }
        
        if (record.getCoinGiveStatus() != null) {
            sql.VALUES("CoinGiveStatus", "#{coinGiveStatus,jdbcType=INTEGER}");
        }
        
        if (record.getGroupId() != null) {
            sql.VALUES("GroupId", "#{groupId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MostVotedRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("MostVoted_Record");
        
        if (record.getUserGroupId() != null) {
            sql.SET("UserGroupId = #{userGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getVotedCount() != null) {
            sql.SET("VotedCount = #{votedCount,jdbcType=INTEGER}");
        }
        
        if (record.getVotedDate() != null) {
            sql.SET("VotedDate = #{votedDate,jdbcType=DATE}");
        }
        
        if (record.getCoinGiveStatus() != null) {
            sql.SET("CoinGiveStatus = #{coinGiveStatus,jdbcType=INTEGER}");
        }
        
        if (record.getGroupId() != null) {
            sql.SET("GroupId = #{groupId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("MostVotedId = #{mostVotedId,jdbcType=INTEGER}");
        
        return sql.toString();
    }


}