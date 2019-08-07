package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *广场
 *
 */
@Getter
@Setter
public class PeSquare extends BaseSerialization {


    private static final long serialVersionUID = -4984277349979552606L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 班级ID
     */
    private Integer classId;
    /**
     * 排序
     */
    private Integer sn;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 加入时间
     */
    private Date joinTime;


}