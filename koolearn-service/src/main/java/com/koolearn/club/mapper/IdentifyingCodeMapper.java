package com.koolearn.club.mapper;

import com.koolearn.club.dto.identifying.IdentifyingCodeListReqDTO;
import com.koolearn.club.entity.PeIdentifyingCode;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@DAO
public interface IdentifyingCodeMapper {


    /**
     * 新增记录
     * @param identifyingCode
     */
    int insert(@Param("identifyingCode") PeIdentifyingCode identifyingCode);

    /**
     *
     * @param identifyingCodeListReqDTO
     * @return
     */
    List<PeIdentifyingCode> list(@Param("identifyingCode") IdentifyingCodeListReqDTO identifyingCodeListReqDTO);


    /**
     *
     * @param identifyingCodeListReqDTO
     * @return
     */
    int listCount(@Param("identifyingCode") IdentifyingCodeListReqDTO identifyingCodeListReqDTO);

    /**
     *
     * @param code
     * @return
     */
    PeIdentifyingCode checkCode(@Param("code") String code);

    /**
     * 修改记录
     * @param identifyingCode
     */
    int updateStatus(@Param("identifyingCode") PeIdentifyingCode identifyingCode);


    /**
     * 通过ID 获取
     * @param identifyingCodeId
     * @return
     */
    PeIdentifyingCode getById(int identifyingCodeId);
}
