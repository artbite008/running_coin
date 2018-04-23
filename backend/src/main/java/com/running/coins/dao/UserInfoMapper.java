package com.running.coins.dao;

import com.running.coins.model.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    @Delete({
        "delete from User_Info",
        "where UserId = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("userId") Integer userId);

    @Insert({
        "insert into User_Info (UserId,",
        "UserName, Status, ",
        "Role, Coins, TotalDistance, ",
        "MetaData, Icon)",
        "values (#{userId,jdbcType=INTEGER},",
        "#{userName,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{role,jdbcType=VARCHAR}, #{coins,jdbcType=INTEGER}, #{totalDistance,jdbcType=REAL}, ",
        "#{metaData,jdbcType=VARCHAR}, #{icon,jdbcType=LONGVARBINARY})"
    })
    int insert(UserInfo record);

    @InsertProvider(type=UserInfoSqlProvider.class, method="insertSelective")
    int insertSelective(UserInfo record);

    @Select({
        "select",
        "UserId, UserName, Status, Role, Coins, TotalDistance, MetaData, Icon",
        "from User_Info",
        "where UserId = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserName", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="Role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="Coins", property="coins", jdbcType=JdbcType.INTEGER),
        @Result(column="TotalDistance", property="totalDistance", jdbcType=JdbcType.REAL),
        @Result(column="MetaData", property="metaData", jdbcType=JdbcType.VARCHAR),
        @Result(column="Icon", property="icon", jdbcType=JdbcType.LONGVARBINARY)
    })
    UserInfo selectByPrimaryKey(@Param("userId") Integer userId);

    @UpdateProvider(type=UserInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserInfo record);

    @Update({
        "update User_Info",
        "set UserName = #{userName,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=VARCHAR},",
          "Role = #{role,jdbcType=VARCHAR},",
          "Coins = #{coins,jdbcType=INTEGER},",
          "TotalDistance = #{totalDistance,jdbcType=REAL},",
          "MetaData = #{metaData,jdbcType=VARCHAR},",
          "Icon = #{icon,jdbcType=LONGVARBINARY}",
        "where UserId = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    @Update({
        "update User_Info",
        "set UserName = #{userName,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=VARCHAR},",
          "Role = #{role,jdbcType=VARCHAR},",
          "Coins = #{coins,jdbcType=INTEGER},",
          "TotalDistance = #{totalDistance,jdbcType=REAL},",
          "MetaData = #{metaData,jdbcType=VARCHAR}",
        "where UserId = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserInfo record);
}