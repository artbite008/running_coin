package com.running.coins.model.request;

import lombok.Data;

/**
 * UserLoginRequest
 *
 * @author guxiang
 * @date 2018/6/4
 */
@Data
public class UserLoginRequest {
    private String userName;
    private Integer groupId;
    private String icon;
    private String jsCode;
}
