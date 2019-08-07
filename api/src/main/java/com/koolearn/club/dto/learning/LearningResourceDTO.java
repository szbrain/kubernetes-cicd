package com.koolearn.club.dto.learning;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeResource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/11.
 */
@Getter
@Setter

public class LearningResourceDTO extends BaseSerialization {

    private int stuId;
    private int classId;
    private short type;
    private String content;
    private List<PeResource> resourceList;

}



