package com.koolearn.club.dto.member;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class MemberManageListReqDTO extends BaseSerialization {


    private static final long serialVersionUID = -3922252557996761219L;
    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 入班开始时间
     */
    private Date joinStartTime;

    /**
     * 入班结束时间
     */
    private Date joinEndTime;

    /**
     * Koo_phone
     */
    private String kooPhone;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 页码
     */
    private int offset;

    /**
     * 分页大小
     */
    private int pageSize;


}
