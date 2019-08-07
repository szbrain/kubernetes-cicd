package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lilong01 on 2018/2/27.
 */
@Getter
@Setter
public class PeLiveRedPocketRule extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private Integer taskId;
    private Short status;
    private BigDecimal totalAmount;
    private Integer totalCount;
    private BigDecimal standardAmount;
    private BigDecimal floatingAmount;
    private Date startTime;
    private Date endTime;
    private String copywriting;
    private Date createTime;


}
