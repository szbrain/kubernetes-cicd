package com.koolearn.club.dto.classroom;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeClassContent;
import com.koolearn.club.entity.PeResource;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/28.
 */
@Getter
@Setter
public class ClassDetailDTO extends BaseSerialization {

    private int id;
    private int teachId;
    private String coverUrl;
    private String name;
    private int upperLimit;
    private Short status;
    private Date createTime;
    private Date openTime;
    private Date enrollEndTime;
    private String desc;

    private int memberCount;

    private String teachNickname;
    private String teachAvatar;
    private String teachDesc;
    private String teachIdentity;

    ClassContentDTO classContentDTO;

    private Short isShark;
    private String sharkCourseName;

    private Short isFeedback;
    private String feedbackId;
}
