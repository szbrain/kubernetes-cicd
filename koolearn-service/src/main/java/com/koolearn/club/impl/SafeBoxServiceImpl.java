package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.koolearn.club.constants.SafeBoxTypeEnum;
import com.koolearn.club.constants.safebox.SafeBoxErrorCodeEnum;
import com.koolearn.club.dto.safebox.*;
import com.koolearn.club.entity.PeSafeBox;
import com.koolearn.club.mapper.SafeBoxMapper;
import com.koolearn.club.service.ISafeBoxService;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SafeBoxServiceImpl implements ISafeBoxService {

    @Resource
    SafeBoxMapper safeBoxMapper;

    /**
     * 保存保险箱
     *
     * @param safeBoxReqDTO
     * @return
     */
    @Override
    public JSONResult<SafeBoxRespDTO> get(SafeBoxReqDTO safeBoxReqDTO) {

        JSONResult<SafeBoxRespDTO> jsonResult = new JSONResult();
        PeSafeBox peSafeBox = safeBoxMapper.getByStuId(safeBoxReqDTO.getStuId());
        if (peSafeBox != null) {
            SafeBoxRespDTO safeBoxRespDTO = new SafeBoxRespDTO();
            ImageContentDTO imageContentDTO = JSON.parseObject(peSafeBox.getImageContent(), ImageContentDTO.class);
            BeanUtils.copyProperties(safeBoxRespDTO, imageContentDTO);
            safeBoxRespDTO.setSafeBoxId(peSafeBox.getId());
            safeBoxRespDTO.setType(peSafeBox.getType());
            safeBoxRespDTO.setImageUrl(peSafeBox.getImageUrl());
            jsonResult.success(safeBoxRespDTO);
        } else {
            jsonResult.fail(SafeBoxErrorCodeEnum.SAFE_BOX_NOT_FOUND.getCode(),
                    SafeBoxErrorCodeEnum.SAFE_BOX_NOT_FOUND.getMessage());
        }
        return jsonResult;
    }

    /**
     * 编辑保险箱
     *
     * @param safeBoxEditReqDTO
     * @return
     */
    @Override
    public JSONResult edit(SafeBoxEditReqDTO safeBoxEditReqDTO) {
        JSONResult jsonResult = new JSONResult();

        PeSafeBox peSafeBox = safeBoxMapper.getByStuId(safeBoxEditReqDTO.getStuId());
        if (peSafeBox != null) {
            ImageContentDTO imageContentDTO = new ImageContentDTO();
            BeanUtils.copyProperties(imageContentDTO, safeBoxEditReqDTO);
            peSafeBox.setImageContent(JSON.toJSONString(imageContentDTO));
            peSafeBox.setUpdateTime(new Date());
            Integer result = safeBoxMapper.edit(peSafeBox);
            if (result > 0) {
                jsonResult.success(null);
            } else {
                jsonResult.fail(SafeBoxErrorCodeEnum.SAFE_BOX_SAVE_FAIL.getCode(),
                        SafeBoxErrorCodeEnum.SAFE_BOX_SAVE_FAIL.getMessage());
            }
        } else {
            peSafeBox = new PeSafeBox();
            ImageContentDTO imageContentDTO = new ImageContentDTO();
            BeanUtils.copyProperties(imageContentDTO, safeBoxEditReqDTO);
            peSafeBox.setImageContent(JSON.toJSONString(imageContentDTO));
            peSafeBox.setStuId(safeBoxEditReqDTO.getStuId());
            peSafeBox.setCreateTime(new Date());
            peSafeBox.setType(SafeBoxTypeEnum.EXAMINEE.getCode());
            peSafeBox.setImageUrl(" ");
            Integer result = safeBoxMapper.add(peSafeBox);
            if (result > 0) {
                jsonResult.success(null);
            } else {
                jsonResult.fail(SafeBoxErrorCodeEnum.SAFE_BOX_SAVE_FAIL.getCode(),
                        SafeBoxErrorCodeEnum.SAFE_BOX_SAVE_FAIL.getMessage());
            }
        }
        return jsonResult;
    }


    /**
     * 查分保险箱列表
     *
     * @param safeBoxListReqDTO
     * @return
     */
    @Override
    public JSONPageResult<List<SafeBoxRespDTO>> getList(SafeBoxListReqDTO safeBoxListReqDTO) {

        JSONPageResult<List<SafeBoxRespDTO>> listJSONPageResult = new JSONPageResult<>();
        List<PeSafeBox> peSafeBoxList = safeBoxMapper.getList(safeBoxListReqDTO);
        if (peSafeBoxList != null && peSafeBoxList.size() > 0) {
            List<SafeBoxRespDTO> safeBoxRespDTOList = new ArrayList<>();
            for (PeSafeBox peSafeBox : peSafeBoxList) {
                SafeBoxRespDTO safeBoxRespDTO = new SafeBoxRespDTO();
                ImageContentDTO imageContentDTO = JSON.parseObject(peSafeBox.getImageContent(), ImageContentDTO.class);
                BeanUtils.copyProperties(safeBoxRespDTO, imageContentDTO);
                safeBoxRespDTO.setSafeBoxId(peSafeBox.getId());
                safeBoxRespDTO.setImageUrl(peSafeBox.getImageUrl());
                safeBoxRespDTO.setType(peSafeBox.getType());
                safeBoxRespDTO.setUpdateTime(peSafeBox.getUpdateTime());
                safeBoxRespDTO.setNickName(peSafeBox.getNickName());
                safeBoxRespDTOList.add(safeBoxRespDTO);
            }
            listJSONPageResult.success(safeBoxRespDTOList);
        } else {
            listJSONPageResult.success(new ArrayList<SafeBoxRespDTO>());
        }
        listJSONPageResult.setPageSize(safeBoxListReqDTO.getPageSize());
        listJSONPageResult.setPageNum(safeBoxListReqDTO.getOffset());
        int count = safeBoxMapper.getListCount(safeBoxListReqDTO);
        if (count % safeBoxListReqDTO.getPageSize() == 0) {
            listJSONPageResult.setPages(count / safeBoxListReqDTO.getPageSize());
        } else {
            listJSONPageResult.setPages((count / safeBoxListReqDTO.getPageSize()) + 1);
        }
        listJSONPageResult.setTotal(count);
        return listJSONPageResult;
    }

    @Override
    public List<SafeBoxRespDTO> listNoPage(SafeBoxListReqDTO safeBoxListReqDTO) {
        List<PeSafeBox> peSafeBoxList = safeBoxMapper.listNoPage(safeBoxListReqDTO);
        List<SafeBoxRespDTO> safeBoxRespDTOList = new ArrayList<>();
        if (peSafeBoxList != null && peSafeBoxList.size() > 0) {
            for (PeSafeBox peSafeBox : peSafeBoxList) {
                SafeBoxRespDTO safeBoxRespDTO = new SafeBoxRespDTO();
                ImageContentDTO imageContentDTO = JSON.parseObject(peSafeBox.getImageContent(), ImageContentDTO.class);
                BeanUtils.copyProperties(safeBoxRespDTO, imageContentDTO);
                safeBoxRespDTO.setSafeBoxId(peSafeBox.getId());
                safeBoxRespDTO.setImageUrl(peSafeBox.getImageUrl());
                safeBoxRespDTO.setType(peSafeBox.getType());
                safeBoxRespDTO.setUpdateTime(peSafeBox.getUpdateTime());
                safeBoxRespDTO.setNickName(peSafeBox.getNickName());
                safeBoxRespDTOList.add(safeBoxRespDTO);
            }

        }
        return safeBoxRespDTOList;
    }

    @Override
    public int deleteByStuId(int stuId) {
        return safeBoxMapper.deleteByStuId(stuId);
    }

    @Override
    public int saveOrUpdate(int stuId, String imageUrl, String imageContent) {
        PeSafeBox safeBox = safeBoxMapper.getByStuId(stuId);
        if (null == safeBox) {
            safeBox = new PeSafeBox();
            safeBox.setStuId(stuId);
            safeBox.setCreateTime(new Date());
            safeBox.setImageContent(imageContent);
            safeBox.setImageUrl(imageUrl);
            safeBox.setType(SafeBoxTypeEnum.EXAMINEE.getCode());
            return safeBoxMapper.add(safeBox);
        } else {
            safeBox.setImageUrl(imageUrl);
            safeBox.setImageContent(imageContent);
            return safeBoxMapper.edit(safeBox);
        }
    }
}
