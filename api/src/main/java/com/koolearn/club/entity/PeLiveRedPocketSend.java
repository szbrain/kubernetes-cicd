package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 直播红包发送记录
 */
@Getter
@Setter
public class PeLiveRedPocketSend extends BaseSerialization {

    private static final long serialVersionUID = 119604291354381688L;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 直播红包ID
     */
    private Integer liveRedPocketId;

    /**
     * userID
     */
    private Integer uid;


    /**
     * 创建时间
     */
    private Date createTime;

}
