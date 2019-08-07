package com.koolearn.club.impl;

import com.koolearn.club.constants.identifying.ErrorCodeEnum;
import com.koolearn.club.dto.identifying.IdentifyingCodeDTO;
import com.koolearn.club.dto.identifying.IdentifyingCodeListReqDTO;
import com.koolearn.club.dto.identifying.IdentifyingCodeReqDTO;
import com.koolearn.club.entity.PeIdentifyingCode;
import com.koolearn.club.mapper.IdentifyingCodeMapper;
import com.koolearn.club.service.IIdentifyingCodeService;
import com.koolearn.club.utils.ClassCreateCodeUtil;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lvyangjun on 2018/3/30.
 */
public class IdentifyingCodeServiceImpl implements IIdentifyingCodeService {


    @Resource
    private IdentifyingCodeMapper identifyingCodeMapper;

    @Override
    public JSONResult<IdentifyingCodeDTO> getCode(IdentifyingCodeReqDTO identifyingCodeReqDTO) {
        /*IdentifyingCodeListReqDTO identifyingCodeListReqDTO=new IdentifyingCodeListReqDTO();
        identifyingCodeListReqDTO.setUserName(identifyingCodeListReqDTO.getUserName());
        identifyingCodeListReqDTO.setPhoneNo(identifyingCodeListReqDTO.getPhoneNo());
        List<PeIdentifyingCode> peIdentifyingCodeList=identifyingCodeMapper.list(identifyingCodeListReqDTO);*/

        PeIdentifyingCode peIdentifyingCode = new PeIdentifyingCode();
        BeanUtils.copyProperties(peIdentifyingCode, identifyingCodeReqDTO);
        peIdentifyingCode.setCode(ClassCreateCodeUtil.classCreateCode());
        peIdentifyingCode.setCreateTime(new Date());
        peIdentifyingCode.setStatus((short) 0);
        identifyingCodeMapper.insert(peIdentifyingCode);
        if (peIdentifyingCode != null) {
            IdentifyingCodeDTO identifyingCodeDTO = new IdentifyingCodeDTO();
            BeanUtils.copyProperties(identifyingCodeDTO, peIdentifyingCode);
            return new JSONResult<IdentifyingCodeDTO>().success(identifyingCodeDTO);
        } else {
            return new JSONResult().fail(ErrorCodeEnum.IDENTIFYING_CODE_SAVE_FAIL.getCode(),
                    ErrorCodeEnum.IDENTIFYING_CODE_SAVE_FAIL.getMessage());
        }
    }


    @Override
    public JSONPageResult<List<IdentifyingCodeDTO>> list(IdentifyingCodeListReqDTO identifyingCodeListReqDTO) {
        JSONPageResult<List<IdentifyingCodeDTO>> listJSONPageResult = new JSONPageResult<List<IdentifyingCodeDTO>>();
        List<PeIdentifyingCode> peIdentifyingCodeList = identifyingCodeMapper.list(identifyingCodeListReqDTO);
        if (peIdentifyingCodeList != null && peIdentifyingCodeList.size() > 0) {
            List<IdentifyingCodeDTO> identifyingCodeDTOList = new ArrayList<>();
            for (PeIdentifyingCode peIdentifyingCode : peIdentifyingCodeList) {
                IdentifyingCodeDTO identifyingCodeDTO = new IdentifyingCodeDTO();
                BeanUtils.copyProperties(identifyingCodeDTO, peIdentifyingCode);
                identifyingCodeDTOList.add(identifyingCodeDTO);
            }
            listJSONPageResult.success(identifyingCodeDTOList);

        } else {
            listJSONPageResult.success(new ArrayList<IdentifyingCodeDTO>());
        }
        listJSONPageResult.setPageSize(identifyingCodeListReqDTO.getPageSize());
        listJSONPageResult.setPageNum(identifyingCodeListReqDTO.getOffset());
        int count = identifyingCodeMapper.listCount(identifyingCodeListReqDTO);
        if (count % identifyingCodeListReqDTO.getPageSize() == 0) {
            listJSONPageResult.setPages(count / identifyingCodeListReqDTO.getPageSize());
        } else {
            listJSONPageResult.setPages((count / identifyingCodeListReqDTO.getPageSize()) + 1);
        }

        return listJSONPageResult;
    }

    @Override
    public JSONResult checkCode(String code) {

        Map<String, Object> returnMap = new HashMap<>();
        if (code != null && !code.trim().equals("")) {
            PeIdentifyingCode peIdentifyingCode = identifyingCodeMapper.checkCode(code);
            if (peIdentifyingCode != null) {
                if (peIdentifyingCode.getType() == 1) {//永久
                    returnMap.put("usable", true);
                } else {//临时
                    if (peIdentifyingCode.getStatus() == 0) {
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
