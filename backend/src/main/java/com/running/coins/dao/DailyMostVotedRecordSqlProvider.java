package com.running.coins.dao;

import com.running.coins.model.DailyMostVotedRecord;
import org.apache.ibatis.jdbc.SQL;

public class DailyMostVotedRecordSqlProvider {

    public String insertSelective(DailyMostVotedRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("DailyMostVoted_Record");
        
        if (record.getDailyMostRecordId() != null) {
            sql.VALUES("DailyMostRecordId", "#{dailyMostRecordId,jdbcType=INTEGER}");
        }
        
        if (record.getMostVotedUserGroupId() != null) {
            sql.VALUES("MostVotedUserGroupId", "#{mostVotedUserGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getEarnCoin() != null) {
            sql.VALUES("EarnCoin", "#{earnCoin,jdbcType=INTEGER}");
        }
        
        if (record.getWeekilyStatus() != null) {
            sql.VALUES("WeekilyStatus", "#{weekilyStatus,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(DailyMostVotedRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("DailyMostVoted_Record");
        
        if (record.getMostVotedUserGroupId() != null) {
            sql.SET("MostVotedUserGroupId = #{mostVotedUserGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getEarnCoin() != null) {
            sql.SET("EarnCoin = #{earnCoin,jdbcType=INTEGER}");
        }
        
        if (record.getWeekilyStatus() != null) {
            sql.SET("WeekilyStatus = #{weekilyStatus,jdbcType=INTEGER}");
        }
        
        sql.WHERE("DailyMostRecordId = #{dailyMostRecordId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}