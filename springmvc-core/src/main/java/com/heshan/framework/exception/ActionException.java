package com.heshan.framework.exception;


public class ActionException extends  BaseException{

    private static final long serialVersionUID = 3402316066131362480L;



    public ActionException() {
    }

    public ActionException(int code, String msg) {
        super(code, msg);
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(Throwable cause) {
        super(cause);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }

}
