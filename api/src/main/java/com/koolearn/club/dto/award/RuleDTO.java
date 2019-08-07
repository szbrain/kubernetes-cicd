package com.koolearn.club.dto.award;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/4/23.
 */
@Setter
@Getter
public class RuleDTO  implements Serializable{

    private static final long serialVersionUID = 5053836827751313534L;
    private String startTime;

    private String endTime;

    private Short type;

    private Integer everyDaylimit;

}
