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
    @Options(useGeneratedKeys = true, keyProperty = "RuningRecordId", keyColumn = "RuningRecordId")
    int insert(RunningRecord record);

    @InsertProvider(type = RunningRecordSqlProvider.class, method = "insertSelective")
    int insertSelective(RunningRecord record);

    @Select({
            "select",
            "RuningRecordId, UserGroupId, Distance, CreationTime, LastVotedTime, Status, ",
            "Score, SettledTime, EarnedCoins, Comments, Evidence",
            "from Running_Record",
            "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "RuningRecordId", property = "runingRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "UserGroupId", property = "userGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "Distance", property = "distance", jdbcType = JdbcType.REAL),
            @Result(column = "CreationTime", property = "creationTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "LastVotedTime", property = "lastVotedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "Score", property = "score", jdbcType = JdbcType.INTEGER),
            @Result(column = "SettledTime", property = "settledTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "EarnedCoins", property = "earnedCoins", jdbcType = JdbcType.DOUBLE),
            @Result(column = "Comments", property = "comments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Evidence", property = "evidence", jdbcType = JdbcType.LONGVARBINARY)
    })
    RunningRecord selectByPrimaryKey(@Param("runingRecordId") Integer runingRecordId);

    @UpdateProvider(type = RunningRecordSqlProvider.class, method = "updateByPrimaryKeySelective")
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
            @Result(column = "RuningRecordId", property = "runingRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "UserGroupId", property = "userGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "Distance", property = "distance", jdbcType = JdbcType.REAL),
            @Result(column = "CreationTime", property = "creationTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "LastVotedTime", property = "lastVotedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "Score", property = "score", jdbcType = JdbcType.INTEGER),
            @Result(column = "SettledTime", property = "settledTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "EarnedCoins", property = "earnedCoins", jdbcType = JdbcType.DOUBLE),
            @Result(column = "Comments", property = "comments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Evidence", property = "evidence", jdbcType = JdbcType.LONGVARBINARY)
    })
    List<RunningRecord> selectByUserGroupIdAndTimeRange(@Param("userGroupId") Integer userGroupId, @Param("start") Date start, @Param("end") Date end);


    @Select({
            "select",
            "RuningRecordId, UserGroupId, Distance, CreationTime, LastVotedTime, Status, ",
            "Score, SettledTime, EarnedCoins, Comments, Evidence",
            "from Running_Record",
            "And CreationTime > date_sub(#{nowtime,jdbcType=TIMESTAMP},INTERVAL 24 HOUR)",
            "And CreationTime < #{nowtime,jdbcType=TIMESTAMP})"
    })
    @Results({
            @Result(column = "RuningRecordId", property = "runingRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "UserGroupId", property = "userGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "Distance", property = "distance", jdbcType = JdbcType.REAL),
            @Result(column = "CreationTime", property = "creationTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "LastVotedTime", property = "lastVotedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "Score", property = "score", jdbcType = JdbcType.INTEGER),
            @Result(column = "SettledTime", property = "settledTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "EarnedCoins", property = "earnedCoins", jdbcType = JdbcType.DOUBLE),
            @Result(column = "Comments", property = "comments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Evidence", property = "evidence", jdbcType = JdbcType.LONGVARBINARY)
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
            "  on User_Info.OpenId= UserGroup.UserOpenId;"
    })
    @Results({
            @Result(column = "finalScore", property = "finalScore", jdbcType = JdbcType.INTEGER),
            @Result(column = "RuningRecordId", property = "runingRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "UserGroupId", property = "userGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "UserId", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "Distance", property = "distance", jdbcType = JdbcType.REAL),
            @Result(column = "CreationTime", property = "creationTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "LastVotedTime", property = "lastVotedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "Score", property = "score", jdbcType = JdbcType.INTEGER),
            @Result(column = "SettledTime", property = "settledTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "EarnedCoins", property = "earnedCoins", jdbcType = JdbcType.DOUBLE),
            @Result(column = "Comments", property = "comments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Evidence", property = "evidence", jdbcType = JdbcType.LONGVARBINARY)
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
            @Result(column = "RuningRecordId", property = "runingRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "UserGroupId", property = "userGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "Distance", property = "distance", jdbcType = JdbcType.REAL),
            @Result(column = "CreationTime", property = "creationTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "LastVotedTime", property = "lastVotedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "Score", property = "score", jdbcType = JdbcType.INTEGER),
            @Result(column = "SettledTime", property = "settledTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "EarnedCoins", property = "earnedCoins", jdbcType = JdbcType.DOUBLE),
            @Result(column = "Comments", property = "comments", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Evidence", property = "evidence", jdbcType = JdbcType.LONGVARBINARY)
    })
    List<RunningRecord> selectRunningRecordLatestHourByUserGroupId(@Param("userGroupId") Integer userGroupId);


    @Select({
            "SELECT",
            "  Running_Record.RuningRecordId    AS runningRecordId,",
            "  User_Info.OpenId                 AS userOpenId,",
            "  UserGroup.UserGroupId            AS userGroupId,",
            "  User_Info.UserName               AS nickName,",
            "  User_Info.Coins                  AS coins,",
            "  User_Info.Icon                   AS icon,",
            "  Running_Record.Distance          AS current,",
            "  WeekilyTarget.TargetDistance     AS target,",
            "  VoteResult.likes                 AS likes,",
            "  VoteResult.dislikes              AS dislikes,",
            "  RecordHasVoted.distancePassVoted AS distancePassVoted,",
            "  RecordWaitVoted.distanceWaitVote AS distanceWaitVote,",
            "  Running_Record.Status            AS status",
            "FROM Running_Record",
            "  LEFT JOIN UserGroup ON UserGroup.UserGroupId = Running_Record.UserGroupId",
            "  LEFT JOIN User_Info ON UserGroup.UserOpenId = User_Info.OpenId",
            "  LEFT JOIN (",
            "              SELECT *",
            "              FROM Target_Distance",
            "              WHERE CreationTime >= #{start,jdbcType=TIMESTAMP} AND CreationTime <= #{end,jdbcType=TIMESTAMP}",
            "            ) AS WeekilyTarget ON UserGroup.UserGroupId = WeekilyTarget.UserGroupId",
            "  LEFT JOIN (",
            "              SELECT",
            "                RuningRecordId,",
            "                ifnull(",
            "                    sum(CASE WHEN Score = 1",
            "                      THEN 1",
            "                        ELSE 0 END),",
            "                    0) AS likes,",
            "                ifnull(",
            "                    sum(CASE WHEN Score = -1",
            "                      THEN 1",
            "                        ELSE 0 END),",
            "                    0) AS dislikes",
            "              FROM Vote_Record",
            "              WHERE VotedTime >= #{start,jdbcType=TIMESTAMP} AND VotedTime <= #{end,jdbcType=TIMESTAMP}",
            "              GROUP BY RuningRecordId",
            "            ) AS VoteResult ON VoteResult.RuningRecordId = Running_Record.RuningRecordId",
            "  LEFT JOIN (",
            "              SELECT",
            "                UserGroupId,",
            "                sum(Distance) AS distancePassVoted",
            "              FROM Running_Record",
            "              WHERE CreationTime >= #{start,jdbcType=TIMESTAMP} AND CreationTime <= #{end,jdbcType=TIMESTAMP}",
            "                    AND Status = 3",
            "              GROUP BY UserGroupId",
            "            ) AS RecordHasVoted ON RecordHasVoted.UserGroupId = UserGroup.UserGroupId",
            "  LEFT JOIN (",
            "              SELECT",
            "                UserGroupId,",
            "                sum(Distance) AS distanceWaitVote",
            "              FROM Running_Record",
            "              WHERE CreationTime >= #{start,jdbcType=TIMESTAMP} AND CreationTime <= #{end,jdbcType=TIMESTAMP}",
            "                    AND Status =0",
            "              GROUP BY UserGroupId",
            "            ) AS RecordWaitVoted ON RecordWaitVoted.UserGroupId = UserGroup.UserGroupId",
            "WHERE Running_Record.CreationTime >= #{start,jdbcType=TIMESTAMP} AND Running_Record.CreationTime <= #{end,jdbcType=TIMESTAMP}",
            "      AND Running_Record.Status = 0"
    })
    @Results({
            @Result(column = "RunningRecordId", property = "runningRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "userOpenId", property = "userOpenId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UserGroupId", property = "userGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "NickName", property = "nickName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Coins", property = "coins", jdbcType = JdbcType.FLOAT),
            @Result(column = "Icon", property = "icon", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Current", property = "current", jdbcType = JdbcType.FLOAT),
            @Result(column = "Target", property = "target", jdbcType = JdbcType.FLOAT),
            @Result(column = "Likes", property = "likes", jdbcType = JdbcType.INTEGER),
            @Result(column = "Dislikes", property = "dislikes", jdbcType = JdbcType.INTEGER),
            @Result(column = "DistancePassVoted", property = "distanceValided", jdbcType = JdbcType.FLOAT),
            @Result(column = "DistanceWaitVote", property = "distanceWaitValided", jdbcType = JdbcType.FLOAT),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.LONGVARBINARY)
    })
    List<UserRecord> selectDailyUserRecord(@Param("start") Date start, @Param("end") Date end);


    @Select({
            "SELECT",
            "  Running_Record.RuningRecordId    AS runningRecordId,",
            "  User_Info.OpenId                 AS userOpenId,",
            "  UserGroup.UserGroupId            AS userGroupId,",
            "  User_Info.UserName               AS nickName,",
            "  User_Info.Coins                  AS coins,",
            "  User_Info.Icon                   AS icon,",
            "  Running_Record.Distance          AS current,",
            "  WeekilyTarget.TargetDistance     AS target,",
            "  VoteResult.likes                 AS likes,",
            "  VoteResult.dislikes              AS dislikes,",
            "  RecordHasVoted.distancePassVoted AS distancePassVoted,",
            "  RecordWaitVoted.distanceWaitVote AS distanceWaitVote,",
            "  Running_Record.Status            AS status",
            "FROM Running_Record",
            "  LEFT JOIN UserGroup ON UserGroup.UserGroupId = Running_Record.UserGroupId",
            "  LEFT JOIN User_Info ON UserGroup.UserOpenId = User_Info.OpenId",
            "  LEFT JOIN (",
            "              SELECT *",
            "              FROM Target_Distance",
            "              WHERE CreationTime >= #{start,jdbcType=TIMESTAMP} AND CreationTime <= #{end,jdbcType=TIMESTAMP}",
            "            ) AS WeekilyTarget ON UserGroup.UserGroupId = WeekilyTarget.UserGroupId",
            "  LEFT JOIN (",
            "              SELECT",
            "                RuningRecordId,",
            "                ifnull(",
            "                    sum(CASE WHEN Score = 1",
            "                      THEN 1",
            "                        ELSE 0 END),",
            "                    0) AS likes,",
            "                ifnull(",
            "                    sum(CASE WHEN Score = -1",
            "                      THEN 1",
            "                        ELSE 0 END),",
            "                    0) AS dislikes",
            "              FROM Vote_Record",
            "              WHERE VotedTime >= #{start,jdbcType=TIMESTAMP} AND VotedTime <= #{end,jdbcType=TIMESTAMP}",
            "              GROUP BY RuningRecordId",
            "            ) AS VoteResult ON VoteResult.RuningRecordId = Running_Record.RuningRecordId",
            "  LEFT JOIN (",
            "              SELECT",
            "                UserGroupId,",
            "                sum(Distance) AS distancePassVoted",
            "              FROM Running_Record",
            "              WHERE CreationTime >= #{start,jdbcType=TIMESTAMP} AND CreationTime <= #{end,jdbcType=TIMESTAMP}",
            "                    AND Status = 3",
            "              GROUP BY UserGroupId",
            "            ) AS RecordHasVoted ON RecordHasVoted.UserGroupId = UserGroup.UserGroupId",
            "  LEFT JOIN (",
            "              SELECT",
            "                UserGroupId,",
            "                sum(Distance) AS distanceWaitVote",
            "              FROM Running_Record",
            "              WHERE CreationTime >= #{start,jdbcType=TIMESTAMP} AND CreationTime <= #{end,jdbcType=TIMESTAMP}",
            "                    AND Status =0",
            "              GROUP BY UserGroupId",
            "            ) AS RecordWaitVoted ON RecordWaitVoted.UserGroupId = UserGroup.UserGroupId",
            "WHERE Running_Record.CreationTime >= #{start,jdbcType=TIMESTAMP} AND Running_Record.CreationTime <= #{end,jdbcType=TIMESTAMP}",
            "      AND UserOpenId=#{openid,jdbcType=VARCHAR} ORDER BY runningRecordId DESC LIMIT 1"
    })
    @Results({
            @Result(column = "RunningRecordId", property = "runningRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "userOpenId", property = "userOpenId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UserGroupId", property = "userGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "NickName", property = "nickName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Coins", property = "coins", jdbcType = JdbcType.FLOAT),
            @Result(column = "Icon", property = "icon", jdbcType = JdbcType.VARCHAR),
            @Result(column = "Current", property = "current", jdbcType = JdbcType.FLOAT),
            @Result(column = "Target", property = "target", jdbcType = JdbcType.FLOAT),
            @Result(column = "Likes", property = "likes", jdbcType = JdbcType.INTEGER),
            @Result(column = "Dislikes", property = "dislikes", jdbcType = JdbcType.INTEGER),
            @Result(column = "DistancePassVoted", property = "distanceValided", jdbcType = JdbcType.FLOAT),
            @Result(column = "DistanceWaitVote", property = "distanceWaitValided", jdbcType = JdbcType.FLOAT),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.LONGVARBINARY)
    })
    UserRecord selectDailyUserRecordWithOpenId(@Param("start") Date start, @Param("end") Date end,@Param("openid") String openid);

}