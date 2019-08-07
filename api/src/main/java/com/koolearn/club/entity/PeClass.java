package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lilong01 on 2018/2/27.
 */
@Getter
@Setter
public class PeClass extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private Integer teachId;
    private Integer contentId;
    private String name;
    private Short status;
    private String desc;
    private String coverUrl;
    private Date openTime;
    private Date enrollEndTime;
    private Integer upperLimit;
    private Short isCharge;
    private BigDecimal amount;
    private String QRCode;
    private Date createTime;
    private Date updateTime;
    private int identifyingCodeId;
    private Short isShark;
    private String sharkCourseName;
    private Short isFeedback;
    private String feedbackId;





}
