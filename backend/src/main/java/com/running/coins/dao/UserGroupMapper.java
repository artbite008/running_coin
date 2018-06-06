package com.running.coins.dao;

import com.running.coins.model.UserGroup;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupMapper {
    @Delete({
        "delete from usergroup",
        "where UserGroupId = #{userGroupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userGroupId);

    @Insert({
        "insert into usergroup (UserGroupId, UserOpenid, ",
        "GroupId)",
        "values (#{userGroupId,jdbcType=INTEGER}, #{userOpenid,jdbcType=VARCHAR}, ",
        "#{groupId,jdbcType=INTEGER})"
    })
    int insert(UserGroup record);

    @InsertProvider(type=UserGroupSqlProvider.class, method="insertSelective")
    int insertSelective(UserGroup record);

    @Select({
        "select",
        "UserGroupId, UserOpenid, GroupId",
        "from usergroup",
        "where UserGroupId = #{userGroupId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserOpenid", property="userOpenid", jdbcType=JdbcType.VARCHAR),
        @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    UserGroup selectByPrimaryKey(Integer userGroupId);

    @UpdateProvider(type=UserGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserGroup record);

    @Update({
        "update usergroup",
        "set UserOpenid = #{userOpenid,jdbcType=VARCHAR},",
          "GroupId = #{groupId,jdbcType=INTEGER}",
        "where UserGroupId = #{userGroupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserGroup record);


    @Select({
            "select",
            "UserGroupId, UserOpenid, GroupId",
            "from UserGroup",
            "where GroupId = #{groupId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserOpenid", property="userOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    List<UserGroup> selectByGroupId(@Param("groupId") Integer groupId);


    @Select({
            "select",
            "UserGroupId, UserOpenid, GroupId",
            "from UserGroup",
            "where GroupId = #{groupId,jdbcType=INTEGER}",
            "and UserOpenid = #{userOpenid,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserOpenid", property="userOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    UserGroup selectByGroupIdAndUserOpenId(@Param("groupId") Integer groupId,@Param("userOpenid") String userOpenid);

}