package com.koolearn.club.dto.assistant;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Setter
@Getter
public class AssistantAuthorizeReqDTO implements Serializable {

    private static final long serialVersionUID = -3582008205165939700L;

    /**
     * 教师ID
     */
    private Integer teachId;

    /**
     * 助教ID
     */
    private Integer assistId;


}
