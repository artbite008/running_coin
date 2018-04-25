package com.running.coins.dao;

import com.running.coins.model.VoteRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRecordMapper {
    @Delete({
            "delete from Vote_Record",
            "where VoteRecordId = #{voteRecordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("voteRecordId") Integer voteRecordId);

    @Insert({
            "insert into Vote_Record (VoteRecordId, ",
            "VoteUserGroupId, RuningRecordId, ",
            "VotedTime, UpdatedTime, ",
            "Status, Score, Comments)",
            "values (#{VoteRecordId,jdbcType=INTEGER}, #{voteUserGroupId,jdbcType=INTEGER}, ",
            "#{runingRecordId,jdbcType=INTEGER}, ",
            "#{votedTime,jdbcType=TIMESTAMP}, #{updatedTime,jdbcType=TIMESTAMP}, ",
            "#{status,jdbcType=INTEGER}, #{score,jdbcType=INTEGER}, #{comments,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "VoteRecordId", keyColumn = "VoteRecordId")
    int insert(VoteRecord record);

    @InsertProvider(type = VoteRecordSqlProvider.class, method = "insertSelective")
    int insertSelective(VoteRecord record);

    @Select({
            "select",
            "VoteRecordId, VoteUserGroupId, RuningRecordId, VotedTime, UpdatedTime, ",
            "Status, Score, Comments",
            "from Vote_Record",
            "where VoteRecordId = #{voteRecordId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "VoteRecordId", property = "voteRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "VoteUserGroupId", property = "voteUserGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "RuningRecordId", property = "runingRecordId", jdbcType = JdbcType.INTEGER),
            @Result(column = "VotedTime", property = "votedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "UpdatedTime", property = "updatedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "Score", property = "score", jdbcType = JdbcType.INTEGER),
            @Result(column = "Comments", property = "comments", jdbcType = JdbcType.VARCHAR)
    })
    VoteRecord selectByPrimaryKey(@Param("voteRecordId") Integer voteRecordId);

    @UpdateProvider(type = VoteRecordSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VoteRecord record);

    @Update({
            "update Vote_Record",
            "set VoteUserGroupId = #{voteUserGroupId,jdbcType=INTEGER},",
            "RuningRecordId = #{runingRecordId,jdbcType=INTEGER},",
            "VotedTime = #{votedTime,jdbcType=TIMESTAMP},",
            "UpdatedTime = #{updatedTime,jdbcType=TIMESTAMP},",
            "Status = #{status,jdbcType=INTEGER},",
            "Score = #{score,jdbcType=INTEGER},",
            "Comments = #{comments,jdbcType=VARCHAR}",
            "where VoteRecordId = #{VoteRecordId,jdbcType=INTEGER}"
    })
    @Options(useGeneratedKeys = true, keyProperty = "VoteRecordId", keyColumn = "VoteRecordId")
    int updateByPrimaryKey(VoteRecord record);

    @Select({
            "select",
            "VoteRecordId, VoteUserGroupId, RuningRecordId, VotedTime, UpdatedTime, ",
            "Status, Score, Comments",
            "from Vote_Record",
            "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}",
            "and VoteUserGroupId = #{voteUserGroupId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "VoteRecordId", property = "voteRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "VoteUserGroupId", property = "voteUserGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "RuningRecordId", property = "runingRecordId", jdbcType = JdbcType.INTEGER),
            @Result(column = "VotedTime", property = "votedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "UpdatedTime", property = "updatedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "Score", property = "score", jdbcType = JdbcType.INTEGER),
            @Result(column = "Comments", property = "comments", jdbcType = JdbcType.VARCHAR)
    })
    VoteRecord selectByVoteUserIdAndRuningRecordId(@Param("runingRecordId") Integer runingRecordId, @Param("voteUserGroupId") Integer voteUserGroupId);

    @Select({
            "select",
            "VoteRecordId, VoteUserGroupId, RuningRecordId, VotedTime, UpdatedTime, ",
            "Status, Score, Comments",
            "from Vote_Record",
            "where RuningRecordId = #{runingRecordId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "VoteRecordId", property = "voteRecordId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "VoteUserGroupId", property = "voteUserGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "RuningRecordId", property = "runingRecordId", jdbcType = JdbcType.INTEGER),
            @Result(column = "VotedTime", property = "votedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "UpdatedTime", property = "updatedTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "Status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "Score", property = "score", jdbcType = JdbcType.INTEGER),
            @Result(column = "Comments", property = "comments", jdbcType = JdbcType.VARCHAR)
    })
    List<VoteRecord> selectByRuningRecordId(@Param("runingRecordId") Integer runingRecordId);


}