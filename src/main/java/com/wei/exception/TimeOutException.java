package com.wei.exception;

public class TimeOutException extends Exception{
    public TimeOutException(){
        super();
    }

    public TimeOutException(String msg){
        super(msg);
    }
}
