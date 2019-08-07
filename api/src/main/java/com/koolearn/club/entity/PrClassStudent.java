package com.koolearn.club.entity;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/2/27.
 */
@Getter
@Setter
public class PrClassStudent extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private Integer classId;
    private Integer stuId;
    private Date joinTime;
    private Short isQuit;
    private Short quitType;
    private Short isRejoin;
    private Date quitTime;
    private String QRCode;
}
