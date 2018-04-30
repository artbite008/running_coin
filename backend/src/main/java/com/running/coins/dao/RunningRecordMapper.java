package com.running.coins.dao;

import com.running.coins.model.RunningRecord;
import com.running.coins.model.RunningRecordWithInfo;
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
            "and CreationTime >= #{start,jdbcType=TIMESTAMP}",
            "and CreationTime <= #{end,jdbcType=TIMESTAMP}",
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



    @Select({
            "select",
            "RuningRecordId, UserGroupId, Distance, CreationTime, LastVotedTime, Status, ",
            "Score, SettledTime, EarnedCoins, Comments, Evidence",
            "from Running_Record",
            "And CreationTime > date_sub(#{nowtime,jdbcType=TIMESTAMP},INTERVAL 24 HOUR)",
            "And CreationTime < #{nowtime,jdbcType=TIMESTAMP})"
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
    List<RunningRecord> selectRunningUserByRecordWithIn24hours(@Param("nowtime") Date nowtime);



    @Select({
            "SELECT",
            "  runningrecordResult.RuningRecordId AS RuningRecordId,",
            "  FinalScore,",
            "  UserName,",
            "  runningrecordResult.UserGroupId AS  UserGroupId,",
            "  Distance,",
            "  CreationTime,",
            "  LastVotedTime,",
            "  runningrecordResult.Status  as Status,",
            "  Score,",
            "  SettledTime,",
            "  EarnedCoins,",
            "  Comments,",
            "  Evidence",
            "FROM (",
            "       SELECT",
            "         RuningRecordId,",
            "         sum(Score) AS FinalScore",
            "       FROM Vote_Record",
            "       WHERE VotedTime > date_sub(now(), INTERVAL 24 HOUR)",
            "       GROUP BY RuningRecordId",
            "     ) AS voteResult RIGHT JOIN (",
            "                                  SELECT *",
            "                                  FROM Running_Record",
            "                                  WHERE CreationTime > date_sub(now(), INTERVAL 24 HOUR)",
            "                                        AND CreationTime < now()",
            "                                ) AS runningrecordResult",
            "    ON voteResult.RuningRecordId = runningrecordResult.RuningRecordId",
            "LEFT JOIN UserGroup",
            "   on UserGroup.UserGroupId = runningrecordResult.UserGroupId",
            "LEFT JOIN User_Info",
            "  on User_Info.UserId= UserGroup.UserId;"
    })
    @Results({
            @Result(column="finalScore", property="finalScore", jdbcType=JdbcType.INTEGER),
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
    List<RunningRecordWithInfo> selectRunningRecordWithInfoScoreIn24hours();


}