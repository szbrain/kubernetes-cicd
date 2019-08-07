package com.koolearn.club.dto.message;

import com.koolearn.club.entity.PeChannel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/3/2.
 */
@Getter
@Setter
public class ChannelDTO extends PeChannel{
    private String className;
    private int classId;

}
