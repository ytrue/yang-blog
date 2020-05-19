package com.yang.blog.util;


import lombok.Data;

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

//    public static <T> ResponseData<T> verificationResults(T data) {
//        ResponseData<T> resp = new ResponseData<T>(data);
//        //操作成功
//        resp.setCode(2);
//        resp.setMsg("error");
//        return resp;
//    }
}

