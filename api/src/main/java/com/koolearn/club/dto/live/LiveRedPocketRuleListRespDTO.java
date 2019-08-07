package com.koolearn.club.dto.live;

import com.koolearn.club.entity.PeLiveRedPocketRule;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by lilong01 on 2018/5/22.
 */
@Setter
@Getter
public class LiveRedPocketRuleListRespDTO extends PeLiveRedPocketRule{

    private Integer issuedCount;
    private BigDecimal issuedAmount;

}
