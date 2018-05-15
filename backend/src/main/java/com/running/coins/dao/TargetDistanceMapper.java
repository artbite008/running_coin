package com.running.coins.dao;

import com.running.coins.model.TargetDistance;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TargetDistanceMapper {
    @Delete({
        "delete from Target_Distance",
        "where TargetDistanceId = #{targetDistanceId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("targetDistanceId") Integer targetDistanceId);

    @Insert({
        "insert into Target_Distance (TargetDistanceId, UserGroupId, ",
        "CreationTime, TargetDistance)",
        "values (#{targetDistanceId,jdbcType=INTEGER}, #{userGroupId,jdbcType=INTEGER}, ",
        "#{creationTime,jdbcType=TIMESTAMP}, #{targetDistance,jdbcType=REAL})"
    })
    @Options(useGeneratedKeys=true, keyProperty="TargetDistanceId", keyColumn="TargetDistanceId")
    int insert(TargetDistance record);

    @InsertProvider(type=TargetDistanceSqlProvider.class, method="insertSelective")
    int insertSelective(TargetDistance record);

    @Select({
        "select",
        "TargetDistanceId, UserGroupId, CreationTime, TargetDistance",
        "from Target_Distance",
        "where TargetDistanceId = #{targetDistanceId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="TargetDistanceId", property="targetDistanceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="CreationTime", property="creationTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="TargetDistance", property="targetDistance", jdbcType=JdbcType.REAL)
    })
    TargetDistance selectByPrimaryKey(@Param("targetDistanceId") Integer targetDistanceId);

    @UpdateProvider(type=TargetDistanceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TargetDistance record);

    @Update({
        "update Target_Distance",
        "set UserGroupId = #{userGroupId,jdbcType=INTEGER},",
          "CreationTime = #{creationTime,jdbcType=TIMESTAMP},",
          "TargetDistance = #{targetDistance,jdbcType=REAL}",
        "where TargetDistanceId = #{targetDistanceId,jdbcType=INTEGER}"
    })
    @Options(useGeneratedKeys=true, keyProperty="TargetDistanceId", keyColumn="TargetDistanceId")
    int updateByPrimaryKey(TargetDistance record);

    @Select({
            "select",
            "TargetDistanceId, UserGroupId, CreationTime, TargetDistance",
            "from Target_Distance",
            "where UserGroupId = #{userGroupId,jdbcType=INTEGER}",
            "and CreationTime >= #{start,jdbcType=DATE}",
            "and CreationTime <= #{end,jdbcType=DATE}"
    })
    @Results({
            @Result(column="TargetDistanceId", property="targetDistanceId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
            @Result(column="CreationTime", property="creationTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="TargetDistance", property="targetDistance", jdbcType=JdbcType.REAL)
    })
    TargetDistance selectByUserGroupIdAndTimeRange(@Param("userGroupId") Integer userGroupId,@Param("start") Date start, @Param("end")Date end);


    @Select({
            "select",
            "TargetDistanceId, UserGroupId, CreationTime, TargetDistance",
            "from Target_Distance",
            "and CreationTime >= #{start,jdbcType=DATE}",
            "and CreationTime <= #{end,jdbcType=DATE}"
    })
    @Results({
            @Result(column="TargetDistanceId", property="targetDistanceId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
            @Result(column="CreationTime", property="creationTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="TargetDistance", property="targetDistance", jdbcType=JdbcType.REAL)
    })
    List<TargetDistance> selectByTimeRange(@Param("start") Date start, @Param("end")Date end);
}