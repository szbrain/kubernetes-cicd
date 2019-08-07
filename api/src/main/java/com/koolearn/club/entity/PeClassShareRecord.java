package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 课程分享统计
 * Created by lvyangjun on 2018/03/14.
 */
@Getter
@Setter
public class PeClassShareRecord extends BaseSerialization{


    private static final long serialVersionUID = 5032118995803975557L;
    private int id;
    private Integer classId;
    private Integer stuId;
    private Date createTime;

}
