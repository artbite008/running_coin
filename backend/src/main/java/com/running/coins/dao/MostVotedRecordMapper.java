package com.running.coins.dao;

import com.running.coins.model.MostVotedRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MostVotedRecordMapper {
    @Delete({
        "delete from MostVoted_Record",
        "where MostVotedId = #{mostVotedId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer mostVotedId);

    @Insert({
        "insert into MostVoted_Record (MostVotedId, UserGroupId, ",
        "VotedCount, VotedDate, ",
        "CoinGiveStatus, GroupId)",
        "values (#{mostVotedId,jdbcType=INTEGER}, #{userGroupId,jdbcType=INTEGER}, ",
        "#{votedCount,jdbcType=INTEGER}, #{votedDate,jdbcType=DATE}, ",
        "#{coinGiveStatus,jdbcType=INTEGER}, #{groupId,jdbcType=INTEGER})"
    })
    int insert(MostVotedRecord record);

    @InsertProvider(type=MostVotedRecordSqlProvider.class, method="insertSelective")
    int insertSelective(MostVotedRecord record);

    @Select({
        "select",
        "MostVotedId, UserGroupId, VotedCount, VotedDate, CoinGiveStatus, GroupId",
        "from MostVoted_Record",
        "where MostVotedId = #{mostVotedId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="MostVotedId", property="mostVotedId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="VotedCount", property="votedCount", jdbcType=JdbcType.INTEGER),
        @Result(column="VotedDate", property="votedDate", jdbcType=JdbcType.DATE),
        @Result(column="CoinGiveStatus", property="coinGiveStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    MostVotedRecord selectByPrimaryKey(Integer mostVotedId);

    @UpdateProvider(type=MostVotedRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MostVotedRecord record);

    @Update({
        "update MostVoted_Record",
        "set UserGroupId = #{userGroupId,jdbcType=INTEGER},",
          "VotedCount = #{votedCount,jdbcType=INTEGER},",
          "VotedDate = #{votedDate,jdbcType=DATE},",
          "CoinGiveStatus = #{coinGiveStatus,jdbcType=INTEGER},",
          "GroupId = #{groupId,jdbcType=INTEGER}",
        "where MostVotedId = #{mostVotedId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MostVotedRecord record);

    @Select({
            "select",
            "MostVotedId, UserGroupId, VotedCount, VotedDate, CoinGiveStatus, GroupId",
            "from MostVoted_Record",
            "where VotedDate =#{votedDate,jdbcType=DATE}",
            "and UserGroupId =#{userGroupId,jdbcType=INTEGER} "
    })
    @Results({
            @Result(column="MostVotedId", property="mostVotedId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
            @Result(column="VotedCount", property="votedCount", jdbcType=JdbcType.INTEGER),
            @Result(column="VotedDate", property="votedDate", jdbcType=JdbcType.DATE),
            @Result(column="CoinGiveStatus", property="coinGiveStatus", jdbcType=JdbcType.INTEGER),
            @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    MostVotedRecord selectByVotedDateAndUserGroupId(@Param("votedDate")Date votedDate, @Param("userGroupId")Integer UserGroupId);

    @Select({
            "SELECT *",
            "FROM MostVoted_Record",
            "WHERE VotedDate < #{endDate,jdbcType=TIMESTAMP} AND VotedDate >= #{startDate,jdbcType=TIMESTAMP}",
            "      AND VotedCount != 0 AND VotedCount =",
            "          (",
            "            SELECT max(VotedCount)",
            "            FROM MostVoted_Record",
            "            WHERE UserGroupId NOT IN",
            "                  (",
            "                    SELECT MostVotedUserGroupId",
            "                    FROM DailyMostVoted_Record",
            "                    WHERE awardDate < #{endDate,jdbcType=TIMESTAMP} AND awardDate >= #{startDate,jdbcType=TIMESTAMP}",
            "                  )",
            "            and VotedDate < #{endDate,jdbcType=TIMESTAMP} AND VotedDate >= #{startDate,jdbcType=TIMESTAMP}",
            "          )"
    })
    @Results({
            @Result(column="MostVotedId", property="mostVotedId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserGroupId", property="userGroupId", jdbcType=JdbcType.INTEGER),
            @Result(column="VotedCount", property="votedCount", jdbcType=JdbcType.INTEGER),
            @Result(column="VotedDate", property="votedDate", jdbcType=JdbcType.DATE),
            @Result(column="CoinGiveStatus", property="coinGiveStatus", jdbcType=JdbcType.INTEGER),
            @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    List<MostVotedRecord> selectThePersonShouldBeAward(@Param("startDate")Date startDate, @Param("endDate") Date endDate);
}