package com.running.coins.dao;

import com.running.coins.model.UserGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserGroupMapper {
    @Delete({
        "delete from User_Group",
        "where GroupId = #{groupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer groupId);

    @Insert({
        "insert into User_Group (GroupId, GroupName, ",
        "MetaData)",
        "values (#{groupId,jdbcType=INTEGER}, #{groupName,jdbcType=VARCHAR}, ",
        "#{metaData,jdbcType=VARCHAR})"
    })
    int insert(UserGroup record);

    @InsertProvider(type=UserGroupSqlProvider.class, method="insertSelective")
    int insertSelective(UserGroup record);

    @Select({
        "select",
        "GroupId, GroupName, MetaData",
        "from User_Group",
        "where GroupId = #{groupId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="GroupName", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="MetaData", property="metaData", jdbcType=JdbcType.VARCHAR)
    })
    UserGroup selectByPrimaryKey(Integer groupId);

    @UpdateProvider(type=UserGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserGroup record);

    @Update({
        "update User_Group",
        "set GroupName = #{groupName,jdbcType=VARCHAR},",
          "MetaData = #{metaData,jdbcType=VARCHAR}",
        "where GroupId = #{groupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserGroup record);
}