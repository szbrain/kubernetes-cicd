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
public class PeLearning extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private Integer classId;
    private Integer stuId;
    private Short type;
    private Short status;
    private String content;
    private Date startTime;
    private Date endTime;
    private String resourceIds; //资源ID数组 Json格式
    private Short isPraise;//是否表扬
    private Short isComment;//是否评论
    private Short praiseType;//表扬类型
    private Short isReminded;//是否已经提醒过
    private Date updateTime;






}
