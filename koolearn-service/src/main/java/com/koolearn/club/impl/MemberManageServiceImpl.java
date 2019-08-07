package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.dto.learning.LearningCommentDTO;
import com.koolearn.club.dto.learning.LearningDTO;
import com.koolearn.club.dto.learning.LearningHistoryReqDTO;
import com.koolearn.club.dto.learning.LearningPraiseDTO;
import com.koolearn.club.dto.member.MemberManageListReqDTO;
import com.koolearn.club.dto.member.MemberManageListRespDTO;
import com.koolearn.club.entity.PeLearning;
import com.koolearn.club.entity.PeLearningPraise;
import com.koolearn.club.entity.PeResource;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.mapper.LearningMapper;
import com.koolearn.club.mapper.LearningPraiseMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.service.ILearningCommentService;
import com.koolearn.club.service.ILearningPraiseService;
import com.koolearn.club.service.IMemberManageService;
import com.koolearn.club.service.IResourceService;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 成员管理
 */
public class MemberManageServiceImpl implements IMemberManageService {


    @Resource
    StudentMapper studentMapper;

    @Resource
    LearningMapper learningMapper;

    @Resource
    IResourceService resourceService;

    @Resource
    ILearningCommentService learningCommentService;

    @Resource
    ILearningPraiseService learningPraiseService;

    @Resource
    LearningPraiseMapper learningPraiseMapper;

    /**
     * 成员管理列表
     *
     * @param memberManageListReqDTO
     * @return
     */
    @Override
    public JSONPageResult<List<MemberManageListRespDTO>> list(MemberManageListReqDTO memberManageListReqDTO) {

        JSONPageResult<List<MemberManageListRespDTO>> listJSONPageResult = new JSONPageResult<>();
        memberManageListReqDTO.setOffset(memberManageListReqDTO.getOffset() * memberManageListReqDTO.getPageSize());
        List<MemberManageListRespDTO> studentList = studentMapper.memberListByClassId(memberManageListReqDTO);
        if (studentList != null && studentList.size() > 0) {
            for (MemberManageListRespDTO memberManageListRespDTO : studentList) {
                int count = learningMapper.countHistoryForStuOfV2(memberManageListRespDTO.getId(), memberManageListReqDTO.getClassId());
                memberManageListRespDTO.setLearningCount(count);
            }
            listJSONPageResult.success(studentList);
        } else {
            listJSONPageResult.success(new ArrayList<MemberManageListRespDTO>());
        }
        Integer count = studentMapper.countMemberListByClassId(memberManageListReqDTO);
        listJSONPageResult.setPageSize(memberManageListReqDTO.getPageSize());
        listJSONPageResult.setPageNum(memberManageListReqDTO.getOffset());
        if (count % memberManageListReqDTO.getPageSize() == 0) {
            listJSONPageResult.setPages(count / memberManageListReqDTO.getPageSize());
        } else {
            listJSONPageResult.setPages((count / memberManageListReqDTO.getPageSize()) + 1);
        }
        listJSONPageResult.setTotal(count);
        return listJSONPageResult;
    }


    @Override
    public JSONPageResult<List<LearningDTO>> studentLearingHistory(LearningHistoryReqDTO learningHistoryReqDTO) {

        JSONPageResult<List<LearningDTO>> listJSONPageResult = new JSONPageResult<>();

        List<LearningDTO> learningDTOList = Lists.newArrayList();
        learningHistoryReqDTO.setOffset(learningHistoryReqDTO.getOffset() * learningHistoryReqDTO.getPageSize());
        List<PeLearning> learningList = learningMapper.studentLearingHistory(learningHistoryReqDTO);

        if (learningList != null && learningList.size() > 0) {
            for (PeLearning learning : learningList) {
                LearningDTO learningDTO = new LearningDTO();
                BeanUtils.copyProperties(learningDTO, learning);
                //学生
                int stuId = learningDTO.getStuId();
                PeStudent student = studentMapper.getById(stuId);
                learningDTO.setStudent(student);
                //资源
                String resourceIdsJson = learning.getResourceIds();
                if (StringUtils.isNoneBlank(resourceIdsJson)) {
                    List<PeResource> resourceList = resourceService.listByIdsJson(resourceIdsJson);
                    learningDTO.setResourceList(resourceList);
                    //点评
                    if (learning.getIsComment() == 1) {
                        List<LearningCommentDTO> learningCommentDTOList = learningCommentService.ListDTOByLearningId(learning.getId());
                        learningDTO.setLearningCommentList(learningCommentDTOList);
                    }
                    learningDTOList.add(learningDTO);
                }
                //学生点赞
                List<LearningPraiseDTO> learningPraiseDTOList = learningPraiseService.listDTOByLearningId(learning.getId());
                if (null != learningPraiseDTOList && learningPraiseDTOList.size() > 0) {
                    learningDTO.setLearningPraiseDTOList(learningPraiseDTOList);
                }
                PeLearningPraise learningPraise = learningPraiseMapper.getByStuIdLearningId(stuId, learning.getId());
                if (null != learningPraise) {
                    learningDTO.setHasLike(true);
                }
            }
            listJSONPageResult.success(learningDTOList);

        } else {
            listJSONPageResult.success(learningDTOList);
        }
        int count = learningMapper.studentLearingHistoryCount(learningHistoryReqDTO);
        listJSONPageResult.setPageSize(learningHistoryReqDTO.getPageSize());
        listJSONPageResult.setPageNum(learningHistoryReqDTO.getOffset());
        if (count % learningHistoryReqDTO.getPageSize() == 0) {
            listJSONPageResult.setPages(count / learningHistoryReqDTO.getPageSize());
        } else {
            listJSONPageResult.setPages((count / learningHistoryReqDTO.getPageSize()) + 1);
        }
        listJSONPageResult.setTotal(count);
        return listJSONPageResult;
    }
}
