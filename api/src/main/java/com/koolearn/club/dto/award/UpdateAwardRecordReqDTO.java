package com.koolearn.club.dto.award;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

/**
 *抽奖请求对象
 */
@Getter
@Setter
public class UpdateAwardRecordReqDTO extends BaseSerialization{

    /**
     * 学生SID
     */
    private String sid;

    /**
     * 班级ID
     */
    private Integer drawRecordId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 邮政编码
     */
    private String postalCode;

}
