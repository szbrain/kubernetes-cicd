package com.koolearn.club.dto.learning;

import com.koolearn.club.entity.PeLearningPraise;
import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lilong01 on 2018/3/8.
 */
@Getter
@Setter
public class LearningPraiseDTO extends PeLearningPraise{
    private PeStudent student;
}
