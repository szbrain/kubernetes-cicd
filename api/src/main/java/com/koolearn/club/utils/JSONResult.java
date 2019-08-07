package com.koolearn.club.utils;

import com.koolearn.club.constants.SystemErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class JSONResult<T> implements Serializable{

  private static final long serialVersionUID = -544941649567417241L;

  public static final int SUCCESS=0;

  private int code;
  private String  msg;
  private  T  data;


    public  JSONResult<T>  success(T data){
    this.code=JSONResult.SUCCESS;
    this.msg = "请求成功";
    this.data=data;
    return this;
  }
  
  public  JSONResult<T>  fail(int code,String msg ){
    this.code=code;
    this.msg=msg;
    return this;
  }
  public  JSONResult<T>  fail(T data){
    this.code= SystemErrorCode.FAIL;
    this.msg="请求失败";
    this.data=data;
    return this;
  }

}
