package com.hc.user.exception;

import com.hc.user.common.BaseResponse;
import com.hc.user.common.ErrorCode;
import com.hc.user.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author 风寻
 * @RestControllerAdvice 用于处理全局异常 主要作用是将异常信息返回给前端
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     * @param e 自定义异常对象
     * @return 返回异常信息
     */
    @ExceptionHandler
    public BaseResponse businessExceptionHandler(BusinessException e){
    log.error("businessException : " + e.getMessage(),e);
    return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription());
    }

    /**
     * 处理运行时异常
     * @param e 运行时异常对象
     * @return 返回异常信息
     */
    @ExceptionHandler
    public BaseResponse runtimeExceptionHandler(RuntimeException e){
        log.error("runtimeException",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }
}
