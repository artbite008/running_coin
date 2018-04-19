package com.running.coins.common.Exception;

import com.running.coins.common.util.ResultUtils;
import com.running.coins.common.enums.ResultEnum;
import com.running.coins.model.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseMessage handle(Exception e) {
        if (e instanceof CommonException) {
            CommonException myException = (CommonException) e;
            return ResultUtils.error(myException.getCode(), myException.getMessage());
        } else {
            logger.error("[System Error] {}", e);
            return ResultUtils.error(ResultEnum.SYSTEM_ERROR);
        }
    }
}
