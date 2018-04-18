package com.running.coins.dao;

import com.running.coins.model.VoteRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRecordMapper {
    @Delete({
        "delete from Vote_Record",
        "where VoteRecordId = #{voteRecordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer voteRecordId);

    @Insert({
        "insert into Vote_Record (VoteRecordId, VoteUserId, ",
        "GroupId, RuningRecordId, ",
        "VotedTime, UpdatedTime, ",
        "Status, Score, Comments)",
        "values (#{voteRecordId,jdbcType=INTEGER}, #{voteUserId,jdbcType=INTEGER}, ",
        "#{groupId,jdbcType=INTEGER}, #{runingRecordId,jdbcType=INTEGER}, ",
        "#{votedTime,jdbcType=TIMESTAMP}, #{updatedTime,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=INTEGER}, #{score,jdbcType=INTEGER}, #{comments,jdbcType=VARCHAR})"
    })
    int insert(VoteRecord record);

    @InsertProvider(type=VoteRecordSqlProvider.class, method="insertSelective")
    int insertSelective(VoteRecord record);

    @Select({
        "select",
        "VoteRecordId, VoteUserId, GroupId, RuningRecordId, VotedTime, UpdatedTime, ",
        "Status, Score, Comments",
        "from Vote_Record",
        "where VoteRecordId = #{voteRecordId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="VoteRecordId", property="voteRecordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="VoteUserId", property="voteUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="RuningRecordId", property="runingRecordId", jdbcType=JdbcType.INTEGER),
        @Result(column="VotedTime", property="votedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UpdatedTime", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="Status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="Score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="Comments", property="comments", jdbcType=JdbcType.VARCHAR)
    })
    VoteRecord selectByPrimaryKey(Integer voteRecordId);

    @UpdateProvider(type=VoteRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VoteRecord record);

    @Update({
        "update Vote_Record",
        "set VoteUserId = #{voteUserId,jdbcType=INTEGER},",
          "GroupId = #{groupId,jdbcType=INTEGER},",
          "RuningRecordId = #{runingRecordId,jdbcType=INTEGER},",
          "VotedTime = #{votedTime,jdbcType=TIMESTAMP},",
          "UpdatedTime = #{updatedTime,jdbcType=TIMESTAMP},",
          "Status = #{status,jdbcType=INTEGER},",
          "Score = #{score,jdbcType=INTEGER},",
          "Comments = #{comments,jdbcType=VARCHAR}",
        "where VoteRecordId = #{voteRecordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VoteRecord record);

    @Select({
            "select",
            "VoteRecordId, VoteUserId, GroupId, RuningRecordId, VotedTime, UpdatedTime, ",
            "Status, Score, Comments",
            "from Vote_Record",
            "where RuningRecordId = #{runningRecordId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="VoteRecordId", property="voteRecordId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="VoteUserId", property="voteUserId", jdbcType=JdbcType.INTEGER),
            @Result(column="GroupId", property="groupId", jdbcType=JdbcType.INTEGER),
            @Result(column="RuningRecordId", property="runingRecordId", jdbcType=JdbcType.INTEGER),
            @Result(column="VotedTime", property="votedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UpdatedTime", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="Status", property="status", jdbcType=JdbcType.INTEGER),
            @Result(column="Score", property="score", jdbcType=JdbcType.INTEGER),
            @Result(column="Comments", property="comments", jdbcType=JdbcType.VARCHAR)
    })
    List<VoteRecord> selectByVoteUserIdAndRuningRecordId(Integer runningRecordId);
}