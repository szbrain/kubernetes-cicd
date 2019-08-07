package com.koolearn.club.dto.message;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.dto.learning.LearningDTO;
import com.koolearn.club.dto.task.TaskDTO;
import com.koolearn.club.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lilong01 on 2018/3/2.
 */
@Getter
@Setter
public class StuMessageDTO extends BaseSerialization{
    private Date createTime;
    private short type;
    private PeTeacher teacher;
    private PeStudent student;
    private TaskDTO taskDTO;
    private PeClassNotice classNotice;
    private LearningDTO learningDTO;
    private PeLearningComment learningComment;

}
