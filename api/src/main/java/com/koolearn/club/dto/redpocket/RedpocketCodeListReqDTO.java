package com.koolearn.club.dto.redpocket;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class RedpocketCodeListReqDTO implements Serializable {

    private static final long serialVersionUID = -3115344558035786792L;
    private String userName;
    private String phoneNo;
    private int offset;
    private int pageSize;

}
