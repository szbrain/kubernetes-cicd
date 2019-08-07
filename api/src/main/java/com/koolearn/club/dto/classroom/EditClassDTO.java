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
public class EditClassDTO extends BaseSerialization {
    private int id;
    private Short isCharge;
    private BigDecimal amount;
    private String coverUrl;
    private String name;
    private Date openTime;
    private Date enrollEndTime;
    private Integer upperLimit;
    private String desc;
    private String content;
    private List<PeResource> resourceList;
    private Short isShark;
    private String sharkCourseName;
    private Short isFeedback;
    private String feedbackId;
}
