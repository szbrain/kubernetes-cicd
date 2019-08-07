package com.koolearn.club.dto.live;


import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 直播红包记录
 */
@Getter
@Setter
public class LiveRedPocketRecordDTO implements Serializable {

    private static final long serialVersionUID = -5436197967275086438L;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 直播红包ID
     */
    private Integer liveRedPocketId;
    /**
     * 学生对象
     */
    private PeStudent student;

    /**
     * 中奖金额
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    private Date createTime;

}
