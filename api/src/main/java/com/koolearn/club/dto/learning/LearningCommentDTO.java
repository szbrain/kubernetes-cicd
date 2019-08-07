package com.koolearn.club.dto.learning;

import com.koolearn.club.entity.PeLearningComment;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.entity.PeTeacher;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/3/8.
 */
@Getter
@Setter
public class LearningCommentDTO extends PeLearningComment{
    private PeTeacher teacher;
}
