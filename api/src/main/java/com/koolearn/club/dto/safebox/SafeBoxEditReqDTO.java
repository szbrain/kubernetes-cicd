package com.koolearn.club.dto.safebox;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

/**
 * 保险箱请求对象
 */
@Getter
@Setter
public class SafeBoxEditReqDTO extends BaseSerialization {

    private static final long serialVersionUID = 8073345811178674861L;
    /**
     * 学生SID
     */
    private Integer stuId;
    /**
     * 科目 1-英语四级 2-英语六级
     */
    private Short subject;

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

    @Override
    public String toString() {
        return "SafeBoxEditReqVo{" +
                "stuId='" + stuId + '\'' +
                ", examineeNum='" + examineeNum + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
