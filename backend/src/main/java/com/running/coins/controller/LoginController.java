package com.running.coins.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.running.coins.common.util.ResultUtils;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.model.response.WeChatOpenIdResponse;
import com.running.coins.service.UserLoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * LoginController
 *
 * @author guxiang
 * @date 2018/5/9
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/onLogin")
    @ApiOperation(value = "user  onLogin by code", notes = "submit records")
    @ApiImplicitParam(name = "onLogin", value = "", required = true, dataType = "onLogin")
    public ResponseMessage onLogin(@RequestParam("code")String code, String olduserId){


        ResponseMessage responseMessage =userLoginService.userLoginService(code,olduserId);

        return  responseMessage;
    }


}
