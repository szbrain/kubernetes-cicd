package com.koolearn.club.dto.task;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.entity.PeResource;
import com.koolearn.club.entity.PeTask;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lilong01 on 2018/3/1.
 */
@Getter
@Setter
public class PostTaskDTO extends BaseSerialization{
    private int id;
    private int classId;
    private String content;
    List<PeResource> resourceList;
}
