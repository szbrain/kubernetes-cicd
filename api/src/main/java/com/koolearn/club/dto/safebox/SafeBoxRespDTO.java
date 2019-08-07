package com.koolearn.club.dto.safebox;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 保险箱请求对象
 */
@Getter
@Setter
public class SafeBoxRespDTO extends BaseSerialization {

    private static final long serialVersionUID = 8073345811178674861L;

    /**
     * 科目 1-英语四级 2-英语六级
     */
    private Short subject;

    /**
     * 类型 1-准考证
     */
    private Short type;
    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 保险箱ID
     */
    private Integer safeBoxId;

    /**
     * 准考证号
     */
    private String examineeNum;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;

    private Date updateTime;

    private String nickName;

    @Override
    public String toString() {
        return "SafeBoxRespDTO{" +
                "subject=" + subject +
                ", type=" + type +
                ", safeBoxId=" + safeBoxId +
                ", examineeNum='" + examineeNum + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
