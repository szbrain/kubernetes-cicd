package com.koolearn.club.dto.learning;

import com.koolearn.club.entity.PeLearning;
import com.koolearn.club.entity.PeResource;
import com.koolearn.club.entity.PeStudent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lilong01 on 2018/3/2.
 */
@Getter
@Setter
public class LearningDTO extends PeLearning {
    List<PeResource> resourceList;
    PeStudent student;
    List<LearningCommentDTO> learningCommentList;
    List<LearningPraiseDTO> learningPraiseDTOList;
    boolean hasLike = false;
}
