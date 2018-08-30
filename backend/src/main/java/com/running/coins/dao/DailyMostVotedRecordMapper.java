package com.running.coins.dao;

import com.running.coins.model.DailyMostVotedRecord;
import com.running.coins.model.MostVotedRecord;
import com.running.coins.model.WeeklyAwardedReportVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyMostVotedRecordMapper {
    @Delete({
        "delete from DailyMostVoted_Record",
        "where DailyMostRecordId = #{dailyMostRecordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer dailyMostRecordId);

    @Delete({
            "delete from DailyMostVoted_Record",
            "where  awardDate = #{awardDate,jdbcType=DATE}"
    })
    int deleteByDate(@Param("awardDate")Date awardDate);

    @Insert({
        "insert into DailyMostVoted_Record (DailyMostRecordId, MostVotedUserGroupId, ",
        "EarnCoin, WeeklyStatus, ",
        "awardDate)",
        "values (#{dailyMostRecordId,jdbcType=INTEGER}, #{mostVotedUserGroupId,jdbcType=INTEGER}, ",
        "#{earnCoin,jdbcType=INTEGER}, #{weeklyStatus,jdbcType=INTEGER}, ",
        "#{awardDate,jdbcType=DATE})"
    })
    int insert(DailyMostVotedRecord record);

    @InsertProvider(type=DailyMostVotedRecordSqlProvider.class, method="insertSelective")
    int insertSelective(DailyMostVotedRecord record);

    @Select({
        "select",
        "DailyMostRecordId, MostVotedUserGroupId, EarnCoin, WeeklyStatus, awardDate",
        "from DailyMostVoted_Record",
        "where DailyMostRecordId = #{dailyMostRecordId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="DailyMostRecordId", property="dailyMostRecordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MostVotedUserGroupId", property="mostVotedUserGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="EarnCoin", property="earnCoin", jdbcType=JdbcType.INTEGER),
        @Result(column="WeeklyStatus", property="weeklyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="awardDate", property="awardDate", jdbcType=JdbcType.DATE)
    })
    DailyMostVotedRecord selectByPrimaryKey(Integer dailyMostRecordId);

    @UpdateProvider(type=DailyMostVotedRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DailyMostVotedRecord record);

    @Update({
        "update DailyMostVoted_Record",
        "set MostVotedUserGroupId = #{mostVotedUserGroupId,jdbcType=INTEGER},",
          "EarnCoin = #{earnCoin,jdbcType=INTEGER},",
          "WeeklyStatus = #{weeklyStatus,jdbcType=INTEGER},",
          "awardDate = #{awardDate,jdbcType=DATE}",
        "where DailyMostRecordId = #{dailyMostRecordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DailyMostVotedRecord record);


    @Select({
            "select",
            "DailyMostRecordId, MostVotedUserGroupId, EarnCoin, WeeklyStatus, awardDate",
            "from DailyMostVoted_Record",
            "where awardDate = #{awardDate,jdbcType=DATE}",
            "and  MostVotedUserGroupId = #{mostVotedUserGroupId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="DailyMostRecordId", property="dailyMostRecordId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="MostVotedUserGroupId", property="mostVotedUserGroupId", jdbcType=JdbcType.INTEGER),
            @Result(column="EarnCoin", property="earnCoin", jdbcType=JdbcType.INTEGER),
            @Result(column="WeeklyStatus", property="weeklyStatus", jdbcType=JdbcType.INTEGER),
            @Result(column="awardDate", property="awardDate", jdbcType=JdbcType.DATE)
    })
    DailyMostVotedRecord selectByawardDateAndMostVotedUserGroupId(@Param("awardDate")Date awardDate, @Param("mostVotedUserGroupId")Integer UserGroupId);



    @Select({
            "select EarnCoin,awardDate,UserName from DailyMostVoted_Record",
            "  LEFT JOIN UserGroup on UserGroup.UserGroupId = MostVotedUserGroupId",
            "  LEFT JOIN User_Info on User_Info.OpenId = UserGroup.UserOpenid",
            "WHERE awardDate >=#{weeklyBeginDate,jdbcType=INTEGER}  and awardDate <=#{weeklyEndDate,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="EarnCoin", property="earnCoin", jdbcType=JdbcType.INTEGER),
            @Result(column="UserName", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="awardDate", property="awardDate", jdbcType=JdbcType.DATE)
    })
    List<WeeklyAwardedReportVo> selectWeeklyAwardedRecord(@Param("weeklyBeginDate")Date weeklyBeginDate, @Param("weeklyEndDate")Date weeklyEndDate);
}