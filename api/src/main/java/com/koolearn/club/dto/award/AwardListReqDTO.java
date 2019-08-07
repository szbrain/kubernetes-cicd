package com.koolearn.club.dto.award;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class AwardListReqDTO implements Serializable {


    private static final long serialVersionUID = -3582008205165939700L;

    /**
     * 教师ID
     */
    private int teachId;


    private int offset;

    private int pageSize;


}
