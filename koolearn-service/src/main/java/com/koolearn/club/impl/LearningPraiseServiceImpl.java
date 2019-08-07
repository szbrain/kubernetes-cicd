package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.dto.learning.LearningPraiseDTO;
import com.koolearn.club.entity.PeLearningPraise;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.mapper.LearningPraiseMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.service.ILearningPraiseService;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class LearningPraiseServiceImpl implements ILearningPraiseService {

    @Resource
    private LearningPraiseMapper learningPraiseMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public PeLearningPraise getById(int id) {
        return learningPraiseMapper.getById(id);
    }

    @Override
    public List<LearningPraiseDTO> listDTOByLearningId(int learningId) {
        List<LearningPraiseDTO> learningPraiseDTOList = Lists.newArrayList();
        List<PeLearningPraise> learningPraiseList = learningPraiseMapper.listByLearningId(learningId);
        for(PeLearningPraise learningPraise : learningPraiseList){
            LearningPraiseDTO learningPraiseDTO = new LearningPraiseDTO();
            BeanUtils.copyProperties(learningPraiseDTO, learningPraise);
            int stuId = learningPraise.getStuId();
            PeStudent student = studentMapper.getById(stuId);
            learningPraiseDTO.setStudent(student);
            learningPraiseDTOList.add(learningPraiseDTO);
        }
        return learningPraiseDTOList;
    }

}
