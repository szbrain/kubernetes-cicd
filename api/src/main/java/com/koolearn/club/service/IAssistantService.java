package com.koolearn.club.service;

import com.koolearn.club.dto.assistant.*;
import com.koolearn.club.entity.PeAssistant;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface IAssistantService {
    PeAssistant getById(int id);

    /**
     * 查询助教列表
     * @param assistantListReqDTO
     * @return
     */
    List<AssistantListRespDTO> listByClassId(AssistantListReqDTO assistantListReqDTO);

    /**
     *  新增助教
     * @param assistantReqDTO
     * @return
     */
    int addAssistant(AssistantReqDTO assistantReqDTO);

    /**
     *  编辑助教
     * @param assistantEditReqDTO
     * @return
     */
    int editAssistant(AssistantEditReqDTO assistantEditReqDTO);

    /**
     *  助教授权
     * @param assistantAuthorizeReqDTO
     * @return
     */
    int authorizeAssistant(AssistantAuthorizeReqDTO assistantAuthorizeReqDTO);

}
