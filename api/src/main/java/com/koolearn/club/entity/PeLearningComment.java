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
public class PeLearningComment extends BaseSerialization{

    private static final long serialVersionUID = 9164880061228146523L;

    private int id;
    private Integer teachId;
    private Integer learningId;
    private Short type;
    private String content;
    private Short isReply;
    private Short replyType;
    private Date createTime;
    private int duration;


}
