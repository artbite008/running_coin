package com.running.coins.dao;

import com.running.coins.model.DailyMostVotedRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface DailyMostVotedRecordMapper {
    @Delete({
        "delete from DailyMostVoted_Record",
        "where DailyMostRecordId = #{dailyMostRecordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer dailyMostRecordId);

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
}