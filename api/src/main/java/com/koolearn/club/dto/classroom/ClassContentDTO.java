package com.koolearn.club.dto.classroom;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeResource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lilong01 on 2018/3/1.
 */
@Getter
@Setter
public class ClassContentDTO extends BaseSerialization{

    private String content;
    private List<PeResource> resourceList;

}
