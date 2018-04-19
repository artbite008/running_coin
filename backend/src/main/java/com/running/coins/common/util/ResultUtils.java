package com.running.coins.common.util;

import com.running.coins.common.enums.ResultEnum;
import com.running.coins.model.response.ResponseMessage;

public class ResultUtils {
    public static <T>ResponseMessage success(T t) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(ResultEnum.SUCCESS.getCode());
        responseMessage.setMsg(ResultEnum.SUCCESS.getMsg());
        responseMessage.setData(t);
        return responseMessage;
    }

    public static ResponseMessage success() {
        return success(null);
    }

    public static ResponseMessage error(int code,String msg) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(code);
        responseMessage.setMsg(msg);
        return responseMessage;
    }

    public static ResponseMessage error(ResultEnum resultEnum){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(resultEnum.getCode());
        responseMessage.setMsg(resultEnum.getMsg());
        return responseMessage;
    }
}