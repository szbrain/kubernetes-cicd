package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 保险箱
 */
@Getter
@Setter
public class PeSafeBox extends BaseSerialization {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 学生ID
     */
    private Integer stuId;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 图片内容
     */
    private String imageContent;
    /**
     * 类型 1-准考证
     */
    private Short type;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Short isDelete;

    /**
     * 昵称
     */
    private String nickName;

}
