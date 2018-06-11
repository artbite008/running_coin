package com.running.coins.dao;

import com.running.coins.model.UserGroup;
import org.apache.ibatis.jdbc.SQL;

public class UserGroupSqlProvider {

    public String insertSelective(UserGroup record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("UserGroup");
        
        if (record.getUserGroupId() != null) {
            sql.VALUES("UserGroupId", "#{userGroupId,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("UserId", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getGroupId() != null) {
            sql.VALUES("GroupId", "#{groupId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserGroup record) {
        SQL sql = new SQL();
        sql.UPDATE("UserGroup");
        
        if (record.getUserId() != null) {
            sql.SET("UserId = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getGroupId() != null) {
            sql.SET("GroupId = #{groupId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("UserGroupId = #{userGroupId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}