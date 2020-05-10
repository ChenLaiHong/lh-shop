package com.lh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CHLaih on 2018/4/30.
 */
public class ResultData<T> {
    private T data;
    private String code;
    private String msg;
    private String time;


    public ResultData(T data, String code, String msg, String time) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.time = time;
    }

    public ResultData() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
