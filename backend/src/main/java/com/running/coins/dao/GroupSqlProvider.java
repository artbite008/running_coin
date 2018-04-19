package com.running.coins.dao;

import com.running.coins.model.Group;
import org.apache.ibatis.jdbc.SQL;

public class GroupSqlProvider {

    public String insertSelective(Group record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("Group");
        
        if (record.getGroupId() != null) {
            sql.VALUES("GroupId", "#{groupId,jdbcType=INTEGER}");
        }
        
        if (record.getGroupName() != null) {
            sql.VALUES("GroupName", "#{groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getMetaData() != null) {
            sql.VALUES("MetaData", "#{metaData,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Group record) {
        SQL sql = new SQL();
        sql.UPDATE("Group");
        
        if (record.getGroupName() != null) {
            sql.SET("GroupName = #{groupName,jdbcType=VARCHAR}");
        }
        
        if (record.getMetaData() != null) {
            sql.SET("MetaData = #{metaData,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("GroupId = #{groupId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}