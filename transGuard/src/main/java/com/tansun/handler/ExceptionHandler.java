package com.tansun.handler;

import com.tansun.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常匹配
 *
 * @author linhb
 * @ControllerAdvice + @ExceptionHandler 全局处理 Controller 层异常
 * <p>
 * 默认的异常匹配优先级：MethodArgumentTypeMismatchException / BusinessException > Exception
 * @create 2019-07-17
 */
@ControllerAdvice
public class ExceptionHandler {

//    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 其他未知异常
     *
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult handler(Exception e) {
        //其他异常
        e.printStackTrace();
//        logger.error(e.toString());
        return ResponseResult.createError(e);
    }


}
