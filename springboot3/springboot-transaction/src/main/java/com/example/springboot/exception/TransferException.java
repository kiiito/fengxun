package com.example.springboot.exception;

/**
 * 自定义异常 转账异常
 */
public class TransferException extends RuntimeException{
    public TransferException() {
    }

    public TransferException(String message) {
        super(message);
    }
}
