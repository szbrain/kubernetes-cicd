package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *点评
 */
@Getter
@Setter
public class PeComment extends BaseSerialization {

    private int id;
    private Integer learningId;
    private Integer pid;
    private Integer stuId;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Integer replierId;

}