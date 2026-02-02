package com.hc.user.common;

/**
 * 返回结果工具类
 */
public class ResultUtils {
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0,data,"ok");
    }
    /**
     * 根据错误码对象生成错误响应
     * @param errorCode 错误码对象，包含错误码、错误信息和描述
     * @return 包含错误信息的BaseResponse对象，data字段为null
     */
    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }
    public static BaseResponse error(ErrorCode errorCode,String description){
        return new BaseResponse<>(errorCode.getCode(),null,errorCode.getMessage(),description);
    }
    public static BaseResponse error(ErrorCode errorCode,String message,String description){
        return new BaseResponse<>(errorCode.getCode(),null,message,description);
    }
    public static BaseResponse error(int code,String message,String description){
        return new BaseResponse<>(code,null,message,description);
    }
}
