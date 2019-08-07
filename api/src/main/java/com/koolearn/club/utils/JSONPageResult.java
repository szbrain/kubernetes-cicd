package com.koolearn.club.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class JSONPageResult<T> implements Serializable {

    public static final int SUCCESS = 0;
    private static final long serialVersionUID = 1340587409790821761L;
    private int code;
    private String msg;
    private T data;
    //第几页
    private int pageNum;
    //每页数据条数
    private int pageSize;
    //总页数
    private int pages;
    //总条数
    private int total;

    public JSONPageResult(){
    }


    public JSONPageResult(int pageSize, int total, int pageNum){
        this.pageSize = pageSize;
        this.total = total;
        this.pageNum = pageNum;
        this.pages = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
    }

    public static int getSuccess() {
        return SUCCESS;
    }

    public JSONPageResult<T> success(T data) {
        this.code = JSONPageResult.SUCCESS;
        this.msg = "请求成功";
        this.data = data;
        return this;
    }

    public JSONPageResult<T> fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "JSONPageResult [code=" + code + ", msg=" + msg + ", data=" + data + ", pageNum=" + pageNum
                + ", pageSize=" + pageSize + ", pages=" + pages + "]";
    }

}