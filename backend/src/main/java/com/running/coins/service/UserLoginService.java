package com.running.coins.service;

import com.alibaba.fastjson.JSON;
import com.running.coins.common.util.ResultUtils;
import com.running.coins.dao.TempUserInfoForOpenIdMapper;
import com.running.coins.model.TempUserInfoForOpenId;
import com.running.coins.model.TempUserInfoForOpenIdExample;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.model.response.WeChatOpenIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * UserLoginService
 *
 * @author guxiang
 * @date 2018/5/10
 */
@Service
public class UserLoginService {


    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String appSecret;

    @Autowired
    private TempUserInfoForOpenIdMapper tempUserInfoForOpenIdMapper;


    public ResponseMessage userLoginService(String code, int olduserId, String sessionOpenId) {

        if (sessionOpenId != "0000" && sessionOpenId.length() >= 28) {
            return ResultUtils.success(sessionOpenId);
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appId, appSecret, code);
        String result = restTemplate.getForObject(url, String.class);
        WeChatOpenIdResponse weChatOpenIdResponse = JSON.parseObject(result, WeChatOpenIdResponse.class);
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

        return ResultUtils.success(openId);
    }
}
