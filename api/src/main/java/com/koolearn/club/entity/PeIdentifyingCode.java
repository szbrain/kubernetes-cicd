package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class PeIdentifyingCode  extends BaseSerialization {


    private static final long serialVersionUID = -1684304293128870101L;

    private int id;
    private String userName;
    private String phoneNo;
    private String remarks;
    private Short type;
    private Short status;
    private String code;
    private Date createTime;
    private Date updateTime;
}
