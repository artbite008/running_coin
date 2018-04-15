package com.running.coins.dao;

import com.running.coins.model.RunningRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RunningRecordMapper {
    @Delete({
        "delete from Running_Record",
        "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer runingRecordId);

    @Insert({
        "insert into Running_Record (RuningRecordId, UserId, ",
        "Distance, CreationTime, ",
        "LastVotedTime, Status, ",
        "Score, SettledTime, ",
        "EarnedCoins, Comments, ",
        "Evidence)",
        "values (#{runingRecordId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{distance,jdbcType=REAL}, #{creationTime,jdbcType=TIMESTAMP}, ",
        "#{lastVotedTime,jdbcType=TIMESTAMP}, #{status,jdbcType=VARCHAR}, ",
        "#{score,jdbcType=INTEGER}, #{settledTime,jdbcType=TIMESTAMP}, ",
        "#{earnedCoins,jdbcType=INTEGER}, #{comments,jdbcType=VARCHAR}, ",
        "#{evidence,jdbcType=LONGVARBINARY})"
    })
    int insert(RunningRecord record);

    @InsertProvider(type=RunningRecordSqlProvider.class, method="insertSelective")
    int insertSelective(RunningRecord record);

    @Select({
        "select",
        "RuningRecordId, UserId, Distance, CreationTime, LastVotedTime, Status, Score, ",
        "SettledTime, EarnedCoins, Comments, Evidence",
        "from Running_Record",
        "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="RuningRecordId", property="runingRecordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="Distance", property="distance", jdbcType=JdbcType.REAL),
        @Result(column="CreationTime", property="creationTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LastVotedTime", property="lastVotedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="Score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="SettledTime", property="settledTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EarnedCoins", property="earnedCoins", jdbcType=JdbcType.INTEGER),
        @Result(column="Comments", property="comments", jdbcType=JdbcType.VARCHAR),
        @Result(column="Evidence", property="evidence", jdbcType=JdbcType.LONGVARBINARY)
    })
    RunningRecord selectByPrimaryKey(Integer runingRecordId);

    @UpdateProvider(type=RunningRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RunningRecord record);

    @Update({
        "update Running_Record",
        "set UserId = #{userId,jdbcType=INTEGER},",
          "Distance = #{distance,jdbcType=REAL},",
          "CreationTime = #{creationTime,jdbcType=TIMESTAMP},",
          "LastVotedTime = #{lastVotedTime,jdbcType=TIMESTAMP},",
          "Status = #{status,jdbcType=VARCHAR},",
          "Score = #{score,jdbcType=INTEGER},",
          "SettledTime = #{settledTime,jdbcType=TIMESTAMP},",
          "EarnedCoins = #{earnedCoins,jdbcType=INTEGER},",
          "Comments = #{comments,jdbcType=VARCHAR},",
          "Evidence = #{evidence,jdbcType=LONGVARBINARY}",
        "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(RunningRecord record);

    @Update({
        "update Running_Record",
        "set UserId = #{userId,jdbcType=INTEGER},",
          "Distance = #{distance,jdbcType=REAL},",
          "CreationTime = #{creationTime,jdbcType=TIMESTAMP},",
          "LastVotedTime = #{lastVotedTime,jdbcType=TIMESTAMP},",
          "Status = #{status,jdbcType=VARCHAR},",
          "Score = #{score,jdbcType=INTEGER},",
          "SettledTime = #{settledTime,jdbcType=TIMESTAMP},",
          "EarnedCoins = #{earnedCoins,jdbcType=INTEGER},",
          "Comments = #{comments,jdbcType=VARCHAR}",
        "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RunningRecord record);
}