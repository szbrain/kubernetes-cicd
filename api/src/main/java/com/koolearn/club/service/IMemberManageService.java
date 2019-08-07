package com.koolearn.club.service;

import com.koolearn.club.dto.learning.LearningDTO;
import com.koolearn.club.dto.learning.LearningHistoryReqDTO;
import com.koolearn.club.dto.member.MemberManageListReqDTO;
import com.koolearn.club.dto.member.MemberManageListRespDTO;
import com.koolearn.club.utils.JSONPageResult;

import java.util.List;

public interface IMemberManageService {

    /**
     * 成员管理列表
     * @param memberManageListReqDTO
     * @return
     */
    JSONPageResult<List<MemberManageListRespDTO>> list(MemberManageListReqDTO memberManageListReqDTO);

    /**
     * 打卡记录
     * @param learningHistoryReqDTO
     * @return
     */
    JSONPageResult<List<LearningDTO>> studentLearingHistory(LearningHistoryReqDTO learningHistoryReqDTO);


}
