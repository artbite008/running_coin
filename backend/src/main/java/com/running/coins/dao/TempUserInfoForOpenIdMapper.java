package com.running.coins.dao;

import com.running.coins.model.TempUserInfoForOpenId;
import com.running.coins.model.TempUserInfoForOpenIdExample;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserInfoForOpenIdMapper {
    @DeleteProvider(type=TempUserInfoForOpenIdSqlProvider.class, method="deleteByExample")
    int deleteByExample(TempUserInfoForOpenIdExample example);

    @Insert({
        "insert into TempUserInfoForOpenId (OldUserId, OpenId, ",
        "SessionKey)",
        "values (#{oldUserId,jdbcType=INTEGER}, #{openId,jdbcType=VARCHAR}, ",
        "#{sessionKey,jdbcType=VARCHAR})"
    })
    int insert(TempUserInfoForOpenId record);

    @InsertProvider(type=TempUserInfoForOpenIdSqlProvider.class, method="insertSelective")
    int insertSelective(TempUserInfoForOpenId record);

    @SelectProvider(type=TempUserInfoForOpenIdSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="OldUserId", property="oldUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="OpenId", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="SessionKey", property="sessionKey", jdbcType=JdbcType.VARCHAR)
    })
    List<TempUserInfoForOpenId> selectByExample(TempUserInfoForOpenIdExample example);

    @UpdateProvider(type=TempUserInfoForOpenIdSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TempUserInfoForOpenId record, @Param("example") TempUserInfoForOpenIdExample example);

    @UpdateProvider(type=TempUserInfoForOpenIdSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TempUserInfoForOpenId record, @Param("example") TempUserInfoForOpenIdExample example);
}