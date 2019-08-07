package com.koolearn.club.dto.classroom;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeResource;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/28.
 */
@Getter
@Setter
public class CreateClassDTO extends BaseSerialization {
    private int teachId;
    private String name;
    private String coverUrl;
    private short isCharge;
    private BigDecimal amount;
    private String desc;
    private String content;
    private List<PeResource> resourceList;
    private String code;
    private Date classStartTime;
    private Date signDeadLine;


}
