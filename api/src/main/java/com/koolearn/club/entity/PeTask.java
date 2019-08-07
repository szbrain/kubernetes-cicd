package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/2/27.
 */
@Getter
@Setter
public class PeTask extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private Integer classId;
    private Short status;
    private Short type;
    private String content;
    private String resourceIds; //资源ID数组（json格式）
    private Date createTime;
    private Date updateTime;






}
