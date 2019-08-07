package com.koolearn.club.dto.safebox;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

/**
 * 保险列表求对象
 */
@Setter
@Getter
public class SafeBoxListReqDTO extends BaseSerialization {


    private static final long serialVersionUID = 4207666730681245028L;
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
     * 昵称
     */
    private String nickName;

    /**
     * 学生ID
     */
    private Integer stuId;
    /**
     * 手机号
     */
    private String phone;

    private int offset;

    private int pageSize;

    public String getExamineeNum() {
        if (examineeNum!=null){
            return "\"examineeNum\":\"" + examineeNum;
        }else {
            return examineeNum;
        }

    }

    public String getSubject() {
        if (subject!=null){
            return "\"subject\":" + subject;
        }else {
            return null;
        }

    }

    public String getName() {
        if (name!=null){
            return "\"name\":\"" + name;
        }else {
            return null;
        }

    }

    public String getPhone() {
        if (phone!=null){
            return "\"phone\":\"" + phone;
        }else {
            return null;
        }

    }


}
