package com.running.coins.controller;


import com.running.coins.model.request.UserLoginRequest;
import com.running.coins.model.response.ResponseMessage;
import com.running.coins.service.UserLoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.rmi.runtime.Log;

/**
 * LoginController
 *
 * @author guxiang
 * @date 2018/5/9
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {


    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/onLogin")
    @ApiOperation(value = "user  onLogin by code", notes = "submit records")
    @ApiImplicitParam(name = "onLogin", value = "", required = true, dataType = "onLogin")
    public ResponseMessage onLogin(@RequestParam("code") String code,
                                   @RequestParam(value = "olduserId", required = false,defaultValue = "0000") int olduserId,
                                   @RequestParam(value = "sessionOpenId", required = false ,defaultValue = "0000") String sessionOpenId) {

        ResponseMessage responseMessage = userLoginService.userLoginService(code, olduserId,sessionOpenId);
        return responseMessage;
    }


    @ApiOperation(value = "user  onLogin by code", notes = "submit records")
    @ApiImplicitParam(name = "onLogin", value = "", required = true, dataType = "onLogin")
    @PostMapping("/onLoginV2")
    public ResponseMessage onLoginV2(@RequestBody UserLoginRequest userLoginRequest) {

        ResponseMessage responseMessage = userLoginService.userLoginServiceV2(userLoginRequest);
        return responseMessage;
    }


}
