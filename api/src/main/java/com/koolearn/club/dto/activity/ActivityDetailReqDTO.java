package com.koolearn.club.dto.activity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class ActivityDetailReqDTO implements Serializable {


    private static final long serialVersionUID = -7633933200326556366L;

    /**
     * 活动ID
     */
    private int activityId;

}
