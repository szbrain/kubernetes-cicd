package com.koolearn.club.dto.identifying;

import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class IdentifyingCodeReqDTO implements Serializable {


    private static final long serialVersionUID = -2052361279146379120L;
    private String userName;
    private String phoneNo;
    private String remarks;
    private Short type;


}
