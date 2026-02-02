package com.hc.springboot.result;

import com.hc.springboot.enums.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的web层返回数据 统一格式 降低前后端沟通成本
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class R<T> {
    //相应的状态码
    private int code;
    //响应的信息描述 成功 失败 删除失败 修改失败 添加失败
    private String msg;
    //响应的对象 支持任意类型的对象
    private T data;

    /**
     * 为了方便返回数据 一般这里都是静态方法 包括两大类 一类是成功的 一类是失败的
     */
    public static <T> R<T> ok(T data){
        return R.<T>builder()
                .code(CodeEnums.OK.getCode())
                .msg(CodeEnums.OK.getMsg())
                .data(data)
                .build();
    }
    public static <T> R<T> ok(){
        return R.<T>builder()
                .code(CodeEnums.OK.getCode())
                .msg(CodeEnums.OK.getMsg())
                .build();
    }

    public static <T> R<T> FAIL(){
        return R.<T>builder()
                .code(CodeEnums.FAIL.getCode())
                .msg(CodeEnums.FAIL.getMsg())
                .build();
    }
    public static <T> R<T> FAIL(CodeEnums codeEnums){
        return R.<T>builder()
                .code(codeEnums.getCode())
                .msg(codeEnums.getMsg())
                .build();
    }
}

