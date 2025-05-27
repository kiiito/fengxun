package com.hc.exceptions;

/**
 * 转账异常
 */
public class TransferException extends Exception{
    public TransferException (){}
    public TransferException(String msg){
        super(msg);
    }
}
