package com.koolearn.club.dto.wechat;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/3/14.
 */
@Getter
@Setter
public class WechatMessageVo extends BaseSerialization {
    private int userId; //用户ID（学生ID或老师ID）
    private short userType; //老师或学生（1老师2学生）
    private String caseNo; //案号
    private String sender;//发送人
    private String detail;//详情
    private int classId;//班级ID
}
