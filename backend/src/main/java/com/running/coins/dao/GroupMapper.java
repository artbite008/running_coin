package com.running.coins.dao;

import com.running.coins.model.Group;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapper {
    @Delete({
        "delete from Group",
        "where GroupId = #{groupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("groupId") Integer groupId);

    @Insert({
        "insert into Group (GroupId, GroupName, ",
        "MetaData)",
        "values (#{groupId,jdbcType=INTEGER}, #{groupName,jdbcType=VARCHAR}, ",
        "#{metaData,jdbcType=VARCHAR})"
    })
    int insert(Group record);

    @InsertProvider(type=GroupSqlProvider.class, method="insertSelective")
    int insertSelective(Group record);

    @Select({
        "select",
        "GroupId, GroupName, MetaData",
        "from Group",
        "where GroupId = #{groupId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="GroupName", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="MetaData", property="metaData", jdbcType=JdbcType.VARCHAR)
    })
    Group selectByPrimaryKey(Integer groupId);

    @UpdateProvider(type=GroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Group record);

    @Update({
        "update Group",
        "set GroupName = #{groupName,jdbcType=VARCHAR},",
          "MetaData = #{metaData,jdbcType=VARCHAR}",
        "where GroupId = #{groupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Group record);
}