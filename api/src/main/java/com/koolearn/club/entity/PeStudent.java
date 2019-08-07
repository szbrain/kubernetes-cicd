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
public class PeStudent extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private String sid;
    private String nickname;
    private String phone;
    private Short sex;
    private String avatar;
    private String desc;
    private String openId;
    private Short source;
    private String email;
    private String sharkPhone;
    private Date createTime;
    private Date updateTime;

}
