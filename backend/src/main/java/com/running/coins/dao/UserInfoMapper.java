package com.running.coins.dao;

import com.running.coins.model.UserInfo;
import com.running.coins.model.transition.UserInfoBatchBean;
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
public interface UserInfoMapper {
    @Delete({
        "delete from User_Info",
        "where UserId = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into User_Info (UserId, UserName, ",
        "Status, Role, Coins, ",
        "TotalDistance, MetaData, ",
        "OpenId, Icon)",
        "values (#{userId,jdbcType=INTEGER}, #{userName,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{role,jdbcType=VARCHAR}, #{coins,jdbcType=DOUBLE}, ",
        "#{totalDistance,jdbcType=REAL}, #{metaData,jdbcType=VARCHAR}, ",
        "#{openId,jdbcType=VARCHAR}, #{icon,jdbcType=LONGVARBINARY})"
    })
    int insert(UserInfo record);

    @InsertProvider(type=UserInfoSqlProvider.class, method="insertSelective")
    int insertSelective(UserInfo record);

    @Select({
        "select",
        "UserId, UserName, Status, Role, Coins, TotalDistance, MetaData, OpenId, Icon",
        "from User_Info",
        "where UserId = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserName", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="Role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="Coins", property="coins", jdbcType=JdbcType.DOUBLE),
        @Result(column="TotalDistance", property="totalDistance", jdbcType=JdbcType.REAL),
        @Result(column="MetaData", property="metaData", jdbcType=JdbcType.VARCHAR),
        @Result(column="OpenId", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="Icon", property="icon", jdbcType=JdbcType.LONGVARBINARY)
    })
    UserInfo selectByPrimaryKey(Integer userId);

    @Select({
            "select",
            "UserId, UserName, Status, Role, Coins, TotalDistance, MetaData, OpenId, Icon",
            "from User_Info",
            "where OpenId = #{openId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserName", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="Role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="Coins", property="coins", jdbcType=JdbcType.DOUBLE),
            @Result(column="TotalDistance", property="totalDistance", jdbcType=JdbcType.REAL),
            @Result(column="MetaData", property="metaData", jdbcType=JdbcType.VARCHAR),
            @Result(column="OpenId", property="openId", jdbcType=JdbcType.VARCHAR),
            @Result(column="Icon", property="icon", jdbcType=JdbcType.LONGVARBINARY)
    })
    UserInfo selectByOpenId(String openId);

    @UpdateProvider(type=UserInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserInfo record);

    @UpdateProvider(type=UserInfoSqlProvider.class, method="updateByOpenIdSelective")
    int updateByopenIdSelective(UserInfo record);

    @Update({
        "update User_Info",
        "set UserName = #{userName,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=VARCHAR},",
          "Role = #{role,jdbcType=VARCHAR},",
          "Coins = #{coins,jdbcType=DOUBLE},",
          "TotalDistance = #{totalDistance,jdbcType=REAL},",
          "MetaData = #{metaData,jdbcType=VARCHAR},",
          "OpenId = #{openId,jdbcType=VARCHAR},",
          "Icon = #{icon,jdbcType=LONGVARBINARY}",
        "where UserId = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    @Update({
        "update User_Info",
        "set UserName = #{userName,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=VARCHAR},",
          "Role = #{role,jdbcType=VARCHAR},",
          "Coins = #{coins,jdbcType=DOUBLE},",
          "TotalDistance = #{totalDistance,jdbcType=REAL},",
          "MetaData = #{metaData,jdbcType=VARCHAR},",
          "OpenId = #{openId,jdbcType=VARCHAR}",
        "where UserId = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserInfo record);


    @Select({
            "SELECT",
            "sum(EarnedCoins) as TotalCoins,",
            "User_Info.OpenId as UserOpenId,",
            "sum(Distance)  as TotalDistance",
            "FROM Running_Record",
            "inner JOIN UserGroup ON UserGroup.UserGroupId = Running_Record.UserGroupId",
            "inner JOIN User_Info ON User_Info.OpenId = UserGroup.UserOpenid",
            "WHERE Running_Record.Status = 3 AND Score > 0",
            "GROUP BY User_Info.UserId"
    })
    @Results({
            @Result(column="TotalCoins", property="totalCoins", jdbcType=JdbcType.DOUBLE),
            @Result(column="UserOpenId", property="userOpenId", jdbcType=JdbcType.INTEGER),
            @Result(column="TotalDistance", property="totalDistance", jdbcType=JdbcType.FLOAT)
    })
    List<UserInfoBatchBean> selectUserTotalInfo();


    @Select({
            "select",
            "UserId, UserName, Status, Role, Coins, TotalDistance, MetaData, Icon,OpenId",
            "from User_Info"
    })
    @Results({
            @Result(column="UserId", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="UserName", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="Status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="Role", property="role", jdbcType=JdbcType.VARCHAR),
            @Result(column="Coins", property="coins", jdbcType=JdbcType.INTEGER),
            @Result(column="TotalDistance", property="totalDistance", jdbcType=JdbcType.REAL),
            @Result(column="MetaData", property="metaData", jdbcType=JdbcType.VARCHAR),
            @Result(column="Icon", property="icon", jdbcType=JdbcType.LONGVARBINARY),
            @Result(column="OpenId", property="openId", jdbcType=JdbcType.VARCHAR)
    })
    List<UserInfo> selectAllUser();

}