package com.running.coins.dao;

import com.running.coins.model.RunningRecord;
import com.running.coins.model.RunningRecordWithInfo;
import com.running.coins.model.transition.UserInfoBatchBean;
import com.running.coins.model.transition.UserRecord;
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
            "  User_Info.UserId as UserId,",
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
            @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER),
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


    @Select({
            "SELECT",
            "  RuningRecordId, UserGroupId, Distance, CreationTime, LastVotedTime, Status,",
            "  Score, SettledTime, EarnedCoins, Comments, Evidence",
            "FROM Running_Record",
            "WHERE UserGroupId = #{userGroupId,jdbcType=INTEGER}",
            "AND CreationTime > date_sub(now(), INTERVAL 1 HOUR)"
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
    List<RunningRecord> selectRunningRecordLatestHourByUserGroupId(@Param("userGroupId") Integer userGroupId);


    @Select({
            "SELECT\n" +
                    "  Running_Record.RuningRecordId    AS RunningRecordId,\n" +
                    "  User_Info.UserId                 AS UserId,\n" +
                    "  UserGroup.UserGroupId            AS UserGroupId,\n" +
                    "  User_Info.UserName               AS NickName,\n" +
                    "  User_Info.Coins                  AS Coins,\n" +
                    "  User_Info.Icon                   AS Icon,\n" +
                    "  Running_Record.Distance          AS Current,\n" +
                    "  Target_Distance.TargetDistance   AS Target,\n" +
                    "  VoteResult.likes                 AS Likes,\n" +
                    "  VoteResult.dislikes              AS Dislikes,\n" +
                    "  RecordHasVoted.distancePassVoted AS DistancePassVoted,\n" +
                    "  RecordWaitVoted.distanceWaitVote AS DistanceWaitVote\n" +
                    "  Running_Record.Status            AS Status\n" +
                    "\n" +
                    "FROM Running_Record\n" +
                    "  LEFT JOIN UserGroup ON UserGroup.UserGroupId = Running_Record.UserGroupId\n" +
                    "  LEFT JOIN User_Info ON UserGroup.UserId = User_Info.UserId\n" +
                    "  LEFT JOIN Target_Distance ON UserGroup.UserGroupId = Target_Distance.UserGroupId\n" +
                    "  LEFT JOIN (\n" +
                    "              SELECT\n" +
                    "                RuningRecordId,\n" +
                    "                sum(CASE WHEN Score = 1\n" +
                    "                  THEN 1\n" +
                    "                    ELSE 0 END) AS likes,\n" +
                    "                sum(CASE WHEN Score = -1\n" +
                    "                  THEN 1\n" +
                    "                    ELSE 0 END) AS dislikes\n" +
                    "              FROM Vote_Record\n" +
                    "              WHERE VotedTime >= #{start,jdbcType=TIMESTAMP} AND VotedTime <= #{end,jdbcType=TIMESTAMP}\n" +
                    "              GROUP BY RuningRecordId\n" +
                    "            ) AS VoteResult ON VoteResult.RuningRecordId = Running_Record.RuningRecordId\n" +
                    "  LEFT JOIN (\n" +
                    "              SELECT\n" +
                    "                UserGroupId,\n" +
                    "                sum(Distance) AS distancePassVoted\n" +
                    "              FROM Running_Record\n" +
                    "              WHERE CreationTime >= #{start,jdbcType=TIMESTAMP} AND CreationTime <= #{end,jdbcType=TIMESTAMP}\n" +
                    "                    AND Status = 3\n" +
                    "              GROUP BY UserGroupId\n" +
                    "            ) AS RecordHasVoted ON RecordHasVoted.UserGroupId = UserGroup.UserGroupId\n" +
                    "  LEFT JOIN (\n" +
                    "              SELECT\n" +
                    "                UserGroupId,\n" +
                    "                sum(Distance) AS distanceWaitVote\n" +
                    "              FROM Running_Record\n" +
                    "              WHERE CreationTime >= #{start,jdbcType=TIMESTAMP} AND CreationTime =< #{end,jdbcType=TIMESTAMP}\n" +
                    "                    AND Status IS NULL\n" +
                    "              GROUP BY UserGroupId\n" +
                    "            ) AS RecordWaitVoted ON RecordWaitVoted.UserGroupId = UserGroup.UserGroupId\n" +
                    "WHERE Running_Record.CreationTime >= #{start,jdbcType=TIMESTAMP} AND Running_Record.CreationTime <= #{end,jdbcType=TIMESTAMP}"
    })
    @Results({
            @Result(column="RunningRecordId", property="runningRecordId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
            @Result(column="NickName", property="nickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="Coins", property="coins", jdbcType=JdbcType.FLOAT),
            @Result(column="Icon", property="icon", jdbcType=JdbcType.VARCHAR),
            @Result(column="Current", property="current", jdbcType=JdbcType.FLOAT),
            @Result(column="Target", property="target", jdbcType=JdbcType.FLOAT),
            @Result(column="Likes", property="likes", jdbcType=JdbcType.INTEGER),
            @Result(column="Dislikes", property="dislikes", jdbcType=JdbcType.INTEGER),
            @Result(column="DistancePassVoted", property="distanceValided", jdbcType=JdbcType.FLOAT),
            @Result(column="DistanceWaitVote", property="distanceWaitValided", jdbcType=JdbcType.FLOAT),
            @Result(column="Status", property="status", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<UserRecord> selectDailyUserRecord(@Param("start")Date  start, @Param("end") Date end);
}