package com.acm.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.acm.web.enums.ResponseEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo<T> implements Serializable {

    private Integer status;

    private String msg;

    private T data;

    private ResponseVo(Integer status,String msg){
        this.status = status;
        this.msg = msg;
    }

    private ResponseVo(Integer status,T Data){
        this.status = status;
        this.data = Data;
    }
    /**
     * 手动写信息
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseVo<T> successByMsg(String msg){
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(),msg);
    }

    /**
     * 默认返回枚举类中的描述信息
     * @param <T>
     * @return
     */
    public static <T> ResponseVo<T> success(){
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getDesc());
    }

    public static <T> ResponseVo<T> success(T data){
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(),data);
    }

    /**
     * 传入一个枚举类 根据枚举类来设置信息
     * @param responseEnum
     * @param <T>
     * @return
     */
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum){
        return new ResponseVo<>(responseEnum.getCode(),responseEnum.getDesc());
    }

    /**
     * 自定义信息
     * @param responseEnum
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum,String msg){
        return new ResponseVo<>(responseEnum.getCode(),msg);
    }

    /**
     * 简化代码
     * @param responseEnum
     * @param bindingResult
     * @param <T>
     * @return
     */
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, BindingResult bindingResult){
        return new ResponseVo<>(responseEnum.getCode(), Objects.requireNonNull(bindingResult.getFieldError()).getField() + " " + bindingResult.getFieldError().getDefaultMessage());
    }
}
