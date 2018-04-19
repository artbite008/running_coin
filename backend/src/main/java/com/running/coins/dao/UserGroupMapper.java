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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupMapper {
    @Delete({
        "delete from UserGroup",
        "where UserGroupId = #{userGroupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userGroupId);

    @Insert({
        "insert into UserGroup (UserGroupId, UserId, ",
        "GroupId)",
        "values (#{userGroupId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{groupId,jdbcType=INTEGER})"
    })
    int insert(UserGroup record);

    @InsertProvider(type=UserGroupSqlProvider.class, method="insertSelective")
    int insertSelective(UserGroup record);

    @Select({
        "select",
        "UserGroupId, UserId, GroupId",
        "from UserGroup",
        "where UserGroupId = #{userGroupId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    UserGroup selectByPrimaryKey(Integer userGroupId);

    @Select({
            "select",
            "UserGroupId, UserId, GroupId",
            "from UserGroup",
            "where GroupId = #{groupId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    List<UserGroup> selectByGroupId(Integer groupId);

    @Select({
            "select",
            "UserGroupId, UserId, GroupId",
            "from UserGroup",
            "where GroupId = #{groupId,jdbcType=INTEGER}",
            "and UserId = #{userId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER),
            @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER)
    })
    UserGroup selectByGroupIdAndUserId(Integer groupId, Integer userId);

    @UpdateProvider(type=UserGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserGroup record);

    @Update({
        "update UserGroup",
        "set UserId = #{userId,jdbcType=INTEGER},",
          "GroupId = #{groupId,jdbcType=INTEGER}",
        "where UserGroupId = #{userGroupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserGroup record);
}