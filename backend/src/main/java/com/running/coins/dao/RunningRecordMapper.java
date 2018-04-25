package com.running.coins.dao;

import com.running.coins.model.RunningRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RunningRecordMapper {
    @Delete({
        "delete from Running_Record",
        "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("runingRecordId") Integer runingRecordId);

    @Insert({
        "insert into Running_Record (RuningRecordId, UserGroupId, ",
        "Distance, CreationTime, ",
        "LastVotedTime, Status, ",
        "Score, SettledTime, ",
        "EarnedCoins, Comments, ",
        "Evidence)",
        "values (#{runingRecordId,jdbcType=INTEGER}, #{userGroupId,jdbcType=INTEGER}, ",
        "#{distance,jdbcType=REAL}, #{creationTime,jdbcType=TIMESTAMP}, ",
        "#{lastVotedTime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER}, ",
        "#{score,jdbcType=INTEGER}, #{settledTime,jdbcType=TIMESTAMP}, ",
        "#{earnedCoins,jdbcType=DOUBLE}, #{comments,jdbcType=VARCHAR}, ",
        "#{evidence,jdbcType=LONGVARBINARY})"
    })
    @Options(useGeneratedKeys=true, keyProperty="RuningRecordId", keyColumn="RuningRecordId")
    int insert(RunningRecord record);

    @InsertProvider(type=RunningRecordSqlProvider.class, method="insertSelective")
    int insertSelective(RunningRecord record);

    @Select({
        "select",
        "RuningRecordId, UserGroupId, Distance, CreationTime, LastVotedTime, Status, ",
        "Score, SettledTime, EarnedCoins, Comments, Evidence",
        "from Running_Record",
        "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="RuningRecordId", property="runingRecordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="Distance", property="distance", jdbcType=JdbcType.REAL),
        @Result(column="CreationTime", property="creationTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LastVotedTime", property="lastVotedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="Status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="Score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="SettledTime", property="settledTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EarnedCoins", property="earnedCoins", jdbcType=JdbcType.DOUBLE),
        @Result(column="Comments", property="comments", jdbcType=JdbcType.VARCHAR),
        @Result(column="Evidence", property="evidence", jdbcType=JdbcType.LONGVARBINARY)
    })
    RunningRecord selectByPrimaryKey(@Param("runingRecordId") Integer runingRecordId);

    @UpdateProvider(type=RunningRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RunningRecord record);

    @Update({
        "update Running_Record",
        "set UserGroupId = #{userGroupId,jdbcType=INTEGER},",
          "Distance = #{distance,jdbcType=REAL},",
          "CreationTime = #{creationTime,jdbcType=TIMESTAMP},",
          "LastVotedTime = #{lastVotedTime,jdbcType=TIMESTAMP},",
          "Status = #{status,jdbcType=INTEGER},",
          "Score = #{score,jdbcType=INTEGER},",
          "SettledTime = #{settledTime,jdbcType=TIMESTAMP},",
          "EarnedCoins = #{earnedCoins,jdbcType=DOUBLE},",
          "Comments = #{comments,jdbcType=VARCHAR},",
          "Evidence = #{evidence,jdbcType=LONGVARBINARY}",
        "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(RunningRecord record);

    @Update({
        "update Running_Record",
        "set UserGroupId = #{userGroupId,jdbcType=INTEGER},",
          "Distance = #{distance,jdbcType=REAL},",
          "CreationTime = #{creationTime,jdbcType=TIMESTAMP},",
          "LastVotedTime = #{lastVotedTime,jdbcType=TIMESTAMP},",
          "Status = #{status,jdbcType=INTEGER},",
          "Score = #{score,jdbcType=INTEGER},",
          "SettledTime = #{settledTime,jdbcType=TIMESTAMP},",
          "EarnedCoins = #{earnedCoins,jdbcType=DOUBLE},",
          "Comments = #{comments,jdbcType=VARCHAR}",
        "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RunningRecord record);

    @Select({
            "select",
            "RuningRecordId, UserGroupId, Distance, CreationTime, LastVotedTime, Status, ",
            "Score, SettledTime, EarnedCoins, Comments, Evidence",
            "from Running_Record",
            "where UserGroupId = #{userGroupId,jdbcType=INTEGER}",
            "and CreationTime >= #{start,jdbcType=DATE}",
            "and CreationTime <= #{end,jdbcType=DATE}",
            "ORDER BY CreationTime ASC"
    })
    @Results({
            @Result(column="RuningRecordId", property="runingRecordId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
            @Result(column="Distance", property="distance", jdbcType=JdbcType.REAL),
            @Result(column="CreationTime", property="creationTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="LastVotedTime", property="lastVotedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="Status", property="status", jdbcType=JdbcType.INTEGER),
            @Result(column="Score", property="score", jdbcType=JdbcType.INTEGER),
            @Result(column="SettledTime", property="settledTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="EarnedCoins", property="earnedCoins", jdbcType=JdbcType.DOUBLE),
            @Result(column="Comments", property="comments", jdbcType=JdbcType.VARCHAR),
            @Result(column="Evidence", property="evidence", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<RunningRecord> selectByUserGroupIdAndTimeRange(@Param("userGroupId") Integer userGroupId, @Param("start") Date start,  @Param("end") Date end);

}