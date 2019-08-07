package com.koolearn.club.impl;

import com.koolearn.club.constants.identifying.ErrorCodeEnum;
import com.koolearn.club.dto.redpocket.RedPocketCodeDTO;
import com.koolearn.club.dto.redpocket.RedPocketCodeReqDTO;
import com.koolearn.club.dto.redpocket.RedpocketCodeListReqDTO;
import com.koolearn.club.entity.PeRedPocketCode;
import com.koolearn.club.mapper.RedPocketCodeMapper;
import com.koolearn.club.service.IRedPocketCodeService;
import com.koolearn.club.utils.ClassCreateCodeUtil;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lvyangjun on 2018/3/30.
 */
public class RedPocketCodeServiceImpl implements IRedPocketCodeService {


    @Resource
    private RedPocketCodeMapper redPocketCodeMapper;

    /**
     * 生产红包验证码
     *
     * @param redPocketCodeReqDTO
     * @return
     */
    @Override
    public JSONResult<RedPocketCodeDTO> getCode(RedPocketCodeReqDTO redPocketCodeReqDTO) {

        PeRedPocketCode peRedPocketCode = new PeRedPocketCode();
        BeanUtils.copyProperties(peRedPocketCode, redPocketCodeReqDTO);
        peRedPocketCode.setCode(ClassCreateCodeUtil.classCreateCode());
        peRedPocketCode.setCreateTime(new Date());
        peRedPocketCode.setStatus((short) 0);
        redPocketCodeMapper.insert(peRedPocketCode);
        if (peRedPocketCode != null) {
            RedPocketCodeDTO redPocketCodeDTO = new RedPocketCodeDTO();
            BeanUtils.copyProperties(redPocketCodeDTO, peRedPocketCode);
            return new JSONResult<RedPocketCodeDTO>().success(redPocketCodeDTO);
        } else {
            return new JSONResult().fail(ErrorCodeEnum.IDENTIFYING_CODE_SAVE_FAIL.getCode(),
                    ErrorCodeEnum.IDENTIFYING_CODE_SAVE_FAIL.getMessage());
        }
    }

    /**
     * 红包验证码列表
     *
     * @param redpocketCodeListReqDTO
     * @return
     */
    @Override
    public JSONPageResult<List<RedPocketCodeDTO>> list(RedpocketCodeListReqDTO redpocketCodeListReqDTO) {
        JSONPageResult<List<RedPocketCodeDTO>> listJSONPageResult = new JSONPageResult<>();
        List<PeRedPocketCode> peRedPocketCodeList = redPocketCodeMapper.list(redpocketCodeListReqDTO);
        if (peRedPocketCodeList != null && peRedPocketCodeList.size() > 0) {
            List<RedPocketCodeDTO> redPocketCodeDTOList = new ArrayList<>();
            for (PeRedPocketCode peRedPocketCode : peRedPocketCodeList) {
                RedPocketCodeDTO redPocketCodeDTO = new RedPocketCodeDTO();
                BeanUtils.copyProperties(redPocketCodeDTO, peRedPocketCode);
                redPocketCodeDTOList.add(redPocketCodeDTO);
            }
            listJSONPageResult.success(redPocketCodeDTOList);

        } else {
            listJSONPageResult.success(new ArrayList<RedPocketCodeDTO>());
        }
        listJSONPageResult.setPageSize(redpocketCodeListReqDTO.getPageSize());
        listJSONPageResult.setPageNum(redpocketCodeListReqDTO.getOffset());
        int count = redPocketCodeMapper.listCount(redpocketCodeListReqDTO);
        if (count % redpocketCodeListReqDTO.getPageSize() == 0) {
            listJSONPageResult.setPages(count / redpocketCodeListReqDTO.getPageSize());
        } else {
            listJSONPageResult.setPages((count / redpocketCodeListReqDTO.getPageSize()) + 1);
        }
        return listJSONPageResult;
    }

    /**
     * 验证红包验证码
     *
     * @param code
     * @return
     */
    @Override
    public JSONResult checkCode(String code) {

        Map<String, Object> returnMap = new HashMap<>();
        if (code != null && !code.trim().equals("")) {
            PeRedPocketCode peRedPocketCode = redPocketCodeMapper.checkCode(code);
            if (peRedPocketCode != null) {
                if (peRedPocketCode.getType() == 1) {//永久
                    returnMap.put("usable", true);
                } else {//临时
                    if (peRedPocketCode.getStatus() == 0) {
                        returnMap.put("usable", true);
                    } else {
                        return new JSONResult().fail(ErrorCodeEnum.IDENTIFYING_CODE_USEED.getCode(),
                                ErrorCodeEnum.IDENTIFYING_CODE_USEED.getMessage());
                    }
                }
            } else {
                return new JSONResult().fail(ErrorCodeEnum.IDENTIFYING_CODE_NOT_FOUND.getCode(),
                        ErrorCodeEnum.IDENTIFYING_CODE_NOT_FOUND.getMessage());
            }
        } else {
            return new JSONResult().fail(ErrorCodeEnum.IDENTIFYING_CODE_NOT_FOUND.getCode(),
                    ErrorCodeEnum.IDENTIFYING_CODE_NOT_FOUND.getMessage());
        }

        return new JSONResult().success(returnMap);
    }
}
