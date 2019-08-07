package com.koolearn.club.service;

import com.koolearn.club.dto.learning.LearningPraiseDTO;
import com.koolearn.club.entity.PeLearningPraise;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface ILearningPraiseService {

    PeLearningPraise getById(int id);

    /**
     * 根据学习ID 查询DTO
     * @param learningId
     * @return
     */
    List<LearningPraiseDTO> listDTOByLearningId(int learningId);
}
