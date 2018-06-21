package com.running.coins.common.Exception;

import com.running.coins.common.enums.ResultEnum;

public class CommonException extends RuntimeException {

    private int code;

    private String errMsg;

    public CommonException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.errMsg = resultEnum.getMsg();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}