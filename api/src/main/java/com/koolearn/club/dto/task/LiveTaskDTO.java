package com.koolearn.club.dto.task;

import com.koolearn.club.entity.PeLive;
import com.koolearn.club.entity.PeResource;
import com.koolearn.club.entity.PeTask;
import com.koolearn.club.entity.PeTeacher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lilong01 on 2018/3/1.
 */
@Getter
@Setter
public class LiveTaskDTO extends PeLive{
    private PeTeacher teacher;
    private PeTask task;
}
