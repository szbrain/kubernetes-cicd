package com.koolearn.club.mapper;

import com.koolearn.club.dto.redpocket.RedpocketCodeListReqDTO;
import com.koolearn.club.entity.PeRedPocketCode;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@DAO
public interface RedPocketCodeMapper {


    /**
     * 新增记录
     *
     * @param peRedPocketCode
     */
    int insert(@Param("peRedPocketCode") PeRedPocketCode peRedPocketCode);

    /**
     * 红包验证码列表
     *
     * @param redpocketCodeListReqDTO
     * @return
     */
    List<PeRedPocketCode> list(@Param("redpocketCode") RedpocketCodeListReqDTO redpocketCodeListReqDTO);


    /**
     * 红包验证码总数
     *
     * @param redpocketCodeListReqDTO
     * @return
     */
    int listCount(@Param("redpocketCode") RedpocketCodeListReqDTO redpocketCodeListReqDTO);

    /**
     * 检查红包验证码
     *
     * @param code
     * @return
     */
    PeRedPocketCode checkCode(@Param("code") String code);

    /**
     * 修改记录
     *
     * @param peRedPocketCode
     */
    int updateStatus(@Param("redPocketCode") PeRedPocketCode peRedPocketCode);


    /**
     * 通过ID 获取
     *
     * @param peRedPocketCodeId
     * @return
     */
    PeRedPocketCode getById(@Param("peRedPocketCodeId") int peRedPocketCodeId);

}
