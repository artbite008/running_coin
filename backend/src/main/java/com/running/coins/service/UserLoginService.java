package com.running.coins.service;

import com.alibaba.fastjson.JSON;
import com.running.coins.common.util.ResultUtils;
import com.running.coins.dao.TargetDistanceMapper;
import com.running.coins.dao.TempUserInfoForOpenIdMapper;
import com.running.coins.dao.UserGroupMapper;
import com.running.coins.dao.UserInfoMapper;
import com.running.coins.model.*;
import com.running.coins.model.request.UserJoinRequest;
import com.running.coins.model.request.UserLoginRequest;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.model.response.WeChatOpenIdResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * UserLoginService
 *
 * @author guxiang
 * @date 2018/5/10
 */
@Service
@Slf4j
public class UserLoginService {


    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String appSecret;

    @Autowired
    private TempUserInfoForOpenIdMapper tempUserInfoForOpenIdMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private TargetDistanceMapper targetDistanceMapper;


    public ResponseMessage userLoginService(String code, int olduserId, String sessionOpenId) {
        log.info("arrive userLoginServiceV1 " + sessionOpenId);
        if (sessionOpenId != "0000" && sessionOpenId.length() >= 28) {
            return ResultUtils.success(sessionOpenId);
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appId, appSecret, code);
        String result = restTemplate.getForObject(url, String.class);
        log.info(url);
        log.info(result);
        WeChatOpenIdResponse weChatOpenIdResponse = JSON.parseObject(result, WeChatOpenIdResponse.class);
        log.info(weChatOpenIdResponse.toString());

        String openId = weChatOpenIdResponse.getOpenid();
        TempUserInfoForOpenId tempUserInfoForOpenId = new TempUserInfoForOpenId();
        tempUserInfoForOpenId.setOldUserId(olduserId);
        tempUserInfoForOpenId.setOpenId(openId);
        tempUserInfoForOpenId.setSessionKey(weChatOpenIdResponse.getSession_key());

        /**查询openid 如果没有存在openid 那么就存入*/
        TempUserInfoForOpenIdExample tempUserInfoForOpenIdExample = new TempUserInfoForOpenIdExample();
        TempUserInfoForOpenIdExample.Criteria criteria = tempUserInfoForOpenIdExample.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<TempUserInfoForOpenId> tempUserInfoForOpenIds = tempUserInfoForOpenIdMapper.selectByExample(tempUserInfoForOpenIdExample);

        if (tempUserInfoForOpenIds.size() == 0) {
            tempUserInfoForOpenIdMapper.insert(tempUserInfoForOpenId);
        }
        UserInfo userInfo = userInfoMapper.selectByOpenId(openId);
        return ResultUtils.success(userInfo);
    }

    public ResponseMessage userLoginServiceV2(UserLoginRequest userLoginRequest) {
        log.info("arrive userLoginServiceV2 " + userLoginRequest.toString());
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appId, appSecret, userLoginRequest.getJsCode());
        String result = restTemplate.getForObject(url, String.class);
        WeChatOpenIdResponse weChatOpenIdResponse = JSON.parseObject(result, WeChatOpenIdResponse.class);
        log.info(weChatOpenIdResponse.toString());
        String openId = weChatOpenIdResponse.getOpenid();
        UserInfo userInfo1 = new UserInfo();

        if (userLoginRequest.getUserName() != null || userLoginRequest.getUserName() != "")
            userInfo1.setUserName(userLoginRequest.getUserName());
        if (userLoginRequest.getIcon() != null || userLoginRequest.getIcon() != "")
            userInfo1.setIcon(userLoginRequest.getIcon());

        userInfo1.setOpenId(openId);

        System.out.println(openId);
        UserInfo userInfoDb = userInfoMapper.selectByOpenId(openId);


        /**查询openid 如果没有存在openid 那么就存入*/
        if (userInfoDb == null) {
            userInfo1.setCoins((double) 0);
            userInfo1.setTotalDistance((float) 0);
            userInfo1.setStatus("active");
            userInfo1.setRole("member");
            userInfoMapper.insert(userInfo1);

            UserGroup userGroup = new UserGroup();
            userGroup.setGroupId(userLoginRequest.getGroupId());
            userGroup.setUserOpenid(openId);
            userGroupMapper.insert(userGroup);


            // setup a default target
            TargetDistance targetDistance = new TargetDistance();
            targetDistance.setTargetDistance(0f);

            //insert the default target, distance 0
            userGroup = userGroupMapper.selectByGroupIdAndUserOpenId(userGroup.getGroupId(), userGroup.getUserOpenid());
            targetDistance.setUserGroupId(userGroup.getUserGroupId());
            targetDistanceMapper.insert(targetDistance);
        } else {
            userInfoMapper.updateByopenIdSelective(userInfo1);
        }

        return ResultUtils.success(openId);
    }
}
