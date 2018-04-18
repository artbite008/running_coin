package com.running.coins.dao;

import com.running.coins.model.TargetDistance;
import org.apache.ibatis.jdbc.SQL;

public class TargetDistanceSqlProvider {

    public String insertSelective(TargetDistance record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("Target_Distance");
        
        if (record.getTargetDistanceId() != null) {
            sql.VALUES("TargetDistanceId", "#{targetDistanceId,jdbcType=INTEGER}");
        }
        
        if (record.getUserGroupId() != null) {
            sql.VALUES("UserGroupId", "#{userGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getCreationTime() != null) {
            sql.VALUES("CreationTime", "#{creationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getTargetDistance() != null) {
            sql.VALUES("TargetDistance", "#{targetDistance,jdbcType=REAL}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TargetDistance record) {
        SQL sql = new SQL();
        sql.UPDATE("Target_Distance");
        
        if (record.getUserGroupId() != null) {
            sql.SET("UserGroupId = #{userGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getCreationTime() != null) {
            sql.SET("CreationTime = #{creationTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getTargetDistance() != null) {
            sql.SET("TargetDistance = #{targetDistance,jdbcType=REAL}");
        }
        
        sql.WHERE("TargetDistanceId = #{targetDistanceId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}