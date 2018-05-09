package com.running.coins.model.response;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseMessage<T> {
    private int code;

    private String msg;

    private T data;
}
