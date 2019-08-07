package com.koolearn.club.dto.common;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageDTO<T> extends BaseSerialization {

    private static final long serialVersionUID = 9164880061228146523L;

    private List<T> list;

    private int count;

    private int totalPage;

    private Long timeline;

    public int getNextPageNo(int pageNo, int pageSize){
        totalPage = count / pageSize;
        if(totalPage - pageNo == 1){
            if(count % pageSize > 0){
                return ++pageNo;
            }
        }
        if(totalPage - pageNo > 1){
            return  ++pageNo;
        }
        return pageNo;
    }
}