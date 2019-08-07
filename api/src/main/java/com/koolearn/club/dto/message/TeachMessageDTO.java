package com.koolearn.club.dto.message;

import com.koolearn.club.dto.BaseSerialization;
import com.koolearn.club.dto.learning.LearningDTO;
import com.koolearn.club.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/3/2.
 */
@Getter
@Setter
public class TeachMessageDTO extends BaseSerialization{
    private Date createTime;
    private short type;
    private String typeName;
    private PeTeacher teacher;
    private PeStudent student;
    private PeClass peClass;
    private LearningDTO learningDTO;
    private PeTask task;
    private PeClassStat classStat;
    private PeClassNotice classNotice;
    private List<PeStudent> studentList;
    private ClassStatDTO classStatDTO;

    @Getter
    @Setter
    public class ClassStatDTO extends PeClassStat{
        private PeClass peClass;
    }
}
