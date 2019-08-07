package com.koolearn.club.impl;

import com.koolearn.club.constants.activity.RedPocketErrorCodeEnum;
import com.koolearn.club.dto.activity.RedPocketRecordListReqDTO;
import com.koolearn.club.dto.award.RedPocketRecordDTO;
import com.koolearn.club.entity.PeRedPocketRecord;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.mapper.RedPocketRecordMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.service.IRedPocketRecordService;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class RedPocketRecordServiceImpl implements IRedPocketRecordService {

    @Resource
    private RedPocketRecordMapper redPocketRecordMapper;
    @Resource
    private StudentMapper studentMapper;

    /**
     * 红包中奖名单
     *
     * @param redPocketRecordListReqDTO
     * @return
     */
    @Override
    public JSONPageResult<List<RedPocketRecordDTO>> redPocketRecordList(RedPocketRecordListReqDTO redPocketRecordListReqDTO) {

        JSONPageResult<List<RedPocketRecordDTO>> listJSONPageResult = new JSONPageResult<>();
        List<PeRedPocketRecord> peRedPocketRecordList = redPocketRecordMapper.redPocketRecordList(redPocketRecordListReqDTO);
        if (peRedPocketRecordList != null && peRedPocketRecordList.size() > 0) {
            List<RedPocketRecordDTO> redPocketRecordDTOList = new ArrayList<>();
            for (PeRedPocketRecord peRedPocketRecord : peRedPocketRecordList) {
                RedPocketRecordDTO redPocketRecordDTO = new RedPocketRecordDTO();
                BeanUtils.copyProperties(redPocketRecordDTO, peRedPocketRecord);
                PeStudent student = studentMapper.getById(peRedPocketRecord.getUid());
                if (null != student) {
                    redPocketRecordDTO.setStudent(student);
                }
                redPocketRecordDTOList.add(redPocketRecordDTO);
            }
            listJSONPageResult.success(redPocketRecordDTOList);
        } else {
            listJSONPageResult.success(new ArrayList<RedPocketRecordDTO>());
        }
        listJSONPageResult.setPageSize(redPocketRecordListReqDTO.getPageSize());
        listJSONPageResult.setPageNum(redPocketRecordListReqDTO.getOffset());
        int count = redPocketRecordMapper.redPocketRecordListCount(redPocketRecordListReqDTO);
        if (count % redPocketRecordListReqDTO.getPageSize() == 0) {
            listJSONPageResult.setPages(count / redPocketRecordListReqDTO.getPageSize());
        } else {
            listJSONPageResult.setPages((count / redPocketRecordListReqDTO.getPageSize()) + 1);
        }
        listJSONPageResult.setTotal(count);
        return listJSONPageResult;
    }


    /**
     * 导出红包中奖列表
     *
     * @param redPocketRecordListReqDTO
     * @return
     */
    @Override
    public JSONResult<List<RedPocketRecordDTO>> export(RedPocketRecordListReqDTO redPocketRecordListReqDTO) {

        JSONResult<List<RedPocketRecordDTO>> jsonResult = new JSONResult<>();
        List<PeRedPocketRecord> peRedPocketRecordList = redPocketRecordMapper.export(redPocketRecordListReqDTO);
        if (peRedPocketRecordList != null && peRedPocketRecordList.size() > 0) {
            List<RedPocketRecordDTO> redPocketRecordDTOList = new ArrayList<>();
            for (PeRedPocketRecord peRedPocketRecord : peRedPocketRecordList) {
                RedPocketRecordDTO redPocketRecordDTO = new RedPocketRecordDTO();
                BeanUtils.copyProperties(redPocketRecordDTO, peRedPocketRecord);
                PeStudent student = studentMapper.getById(peRedPocketRecord.getUid());
                if (null != student) {
                    redPocketRecordDTO.setStudent(student);
                }
                redPocketRecordDTOList.add(redPocketRecordDTO);
            }
            jsonResult.success(redPocketRecordDTOList);
        } else {
            jsonResult.fail(RedPocketErrorCodeEnum.RED_POCKET_RECORD_NOT_FOUND.getCode(),
                    RedPocketErrorCodeEnum.RED_POCKET_RECORD_NOT_FOUND.getMessage());
        }
        return jsonResult;
    }
}
