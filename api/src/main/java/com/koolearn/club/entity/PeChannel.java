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
public class PeChannel extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private int teachId;
    private Integer businessId;
    private String coverUrl;
    private short type;
    private String content;
    private int unreadCount;
    private Date createTime;
    private Date updateTime;






}
