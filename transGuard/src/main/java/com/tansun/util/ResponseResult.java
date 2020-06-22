package com.tansun.util;

import lombok.Data;

import java.util.List;

/**
 * @Author linhb
 * @Date 2020/5/27
 **/
@Data
public class ResponseResult {
    private int status = 200;
    private String message;
    private String errMessage;
    private Object result;

    public ResponseResult() {
    }

    public ResponseResult(int status, String message, String errMessage, Object result) {
        this.status = status;
        this.message = message;
        this.errMessage = errMessage;
        this.result = result;
    }

    public static ResponseResult createInfo(List object){
        ResponseResult vo = new ResponseResult(200,"success","",object);
        return vo;
    }

    public static ResponseResult createError(Exception e){
        ResponseResult vo = new ResponseResult(201,"fail",e.getMessage(),"");
        return vo;
    }
}
