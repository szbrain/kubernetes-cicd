package com.koolearn.club.dto.member;

import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class MemberManageListRespDTO extends PeStudent {


    private static final long serialVersionUID = -7748976994533464934L;

    /**
     * 入班时间
     */
    private Date joinTime;

    /**
     * 打卡次数
     */
    private Integer learningCount;


}
