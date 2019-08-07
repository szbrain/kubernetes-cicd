package com.koolearn.club.dto.identifying;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class IdentifyingCodeDTO implements Serializable {


    private static final long serialVersionUID = 2807484996678609253L;
    private String userName;
    private String phoneNo;
    private String remarks;
    private Short status;
    private Short type;
    private String code;
    private Date createTime;


}
