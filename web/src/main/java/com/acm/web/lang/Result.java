package com.acm.web.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;


    public static Result success(){ return success(200,"操作成功",null); }

    public static Result fail(){
        return fail(400,"操作失败",null);
    }

    public static Result success(String msg){
        return success(200,msg,null);
    }

    public static Result fail(String msg){
        return fail(400,msg,null);
    }

    public static Result success(Object data){ return success(200,"操作成功",data); }

    public static Result fail(Object data){
        return fail(400,"操作失败",data);
    }

    public static Result success(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}
