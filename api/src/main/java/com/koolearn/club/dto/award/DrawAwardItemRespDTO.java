package com.koolearn.club.dto.award;

import com.koolearn.club.entity.PeDrawAwardItem;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/4/18.
 */
@Getter
@Setter
public class DrawAwardItemRespDTO extends PeDrawAwardItem {

    /**
     * 奖品信息
     */
    private AwardRespDTO awardRespDTO;

}
