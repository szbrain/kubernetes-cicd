package com.koolearn.club.dto.live;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 检查打开红包
 */
@Getter
@Setter
public class CheckOpenRespDTO implements Serializable {

    private static final long serialVersionUID = -59588283915913784L;
    /**
     * 是否可以抽奖
     */
    private boolean checkOpen;

    /**
     * 直播红包ID
     */
    private Integer liveRedPocketId;

    /**
     * 红包文案
     */
    private String copywriting;


}
