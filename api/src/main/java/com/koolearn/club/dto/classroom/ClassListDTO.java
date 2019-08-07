package com.koolearn.club.dto.classroom;

import com.koolearn.club.dto.BaseSerialization;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/2/28.
 */
@Getter
@Setter
public class ClassListDTO extends BaseSerialization {

    private int id;
    private int teachId;
    private String name;
    private String coverUrl;
    private int memberCount;
    private Short status;
    private Date createTime;

    private String teachNickname;
    private String teachAvatar;
}
