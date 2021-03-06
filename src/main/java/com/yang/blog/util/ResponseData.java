package com.yang.blog.util;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：yangyi
 * @date：2020/4/16 20:43
 * @description：
 */
@Data
public class ResponseData<T> {

    private Integer code;

    private String msg;

    private T data;


    public ResponseData() {
    }

    public ResponseData(T data) {
        this.data = data;
    }

    public ResponseData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 列表返回数据
     *
     * @param total
     * @param rows
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> list(T total, T rows) {
        HashMap<String, T> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", rows);
        return map;
    }

    public static <T> ResponseData<T> success() {
        ResponseData<T> resp = new ResponseData<T>(null);
        //操作成功
        resp.setCode(0);
        resp.setMsg("success");
        return resp;
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作成功
        resp.setCode(0);
        resp.setMsg("success");
        return resp;
    }

    public static <T> ResponseData<T> success(String msg, T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作成功
        resp.setCode(0);
        resp.setMsg(msg);
        return resp;
    }

    public static <T> ResponseData<T> success(Integer code, String msg, T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作成功
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }


    public static <T> ResponseData<T> fail(String msg) {
        ResponseData<T> resp = new ResponseData<T>();
        //操作失败
        resp.setCode(1);
        resp.setMsg(msg);
        return resp;
    }


    public static <T> ResponseData<T> fail(Integer code, String msg) {
        ResponseData<T> resp = new ResponseData<T>();
        //操作失败
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public static <T> ResponseData<T> fail(Integer code, String msg, T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作失败
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }


    public ResponseData<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public ResponseData<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

}

