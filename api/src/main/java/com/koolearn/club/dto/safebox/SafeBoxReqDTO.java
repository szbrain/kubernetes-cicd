package com.koolearn.club.dto.safebox;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

/**
 * 保险箱请求对象
 */
@Getter
@Setter
public class SafeBoxReqDTO extends BaseSerialization {

    private static final long serialVersionUID = -6946594839575453485L;
    /**
     * 学生ID
     */
    private Integer stuId;


}
