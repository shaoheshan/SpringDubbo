package com.heshan.framework.common.entity;

import java.io.Serializable;

/**
 * 数据对象 key - value
 *
 * @param <C>
 * @param <M>
 */
public class Entry<C, M> implements Serializable {
    private static final long serialVersionUID = 4054845297443258156L;

    public static final String SUCCESS = "200";
    public static final String ERROR = "400";
    public C code;
    public M msg;
    public Object ext;

    public Entry() {
    }

    public Entry(C code, M msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Entry<C, M> put(C code, M msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(String.valueOf(this.code));
    }

    public boolean isSuccess(String code) {
        return SUCCESS.equals(code);
    }

    public C getCode() {
        return code;
    }

    public void setCode(C code) {
        this.code = code;
    }

    public M getMsg() {
        return msg;
    }

    public void setMsg(M msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "\",\"ext\":\"" + ext + "\"}";
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

}
