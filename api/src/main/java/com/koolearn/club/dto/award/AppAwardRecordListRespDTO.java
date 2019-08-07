package com.koolearn.club.dto.award;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
public class AppAwardRecordListRespDTO extends BaseSerialization {

    /**
     * 主键ID
     */
    private int id;

    /**
     * 时间开始
     */
    private Date createTime;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 奖项名称
     */
    private String awardItemName;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     *  物流地址
     */
    private String address;

    /**
     * 备注
     */
    private String remarks;


    /**
     * 班级ID
     */
    private Integer classId;
    /**
     * 班级名称
     */
    private String className;


}
