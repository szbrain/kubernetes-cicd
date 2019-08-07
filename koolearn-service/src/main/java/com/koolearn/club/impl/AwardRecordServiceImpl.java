package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.dto.award.AwardRecordListDTO;
import com.koolearn.club.dto.award.AwardRecordListParamDTO;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.entity.PeDrawRecord;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.mapper.DrawRecordMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.service.IAwardRecordService;
import com.koolearn.util.BeanUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lvyangjun on 2018/4/18.
 */
public class AwardRecordServiceImpl implements IAwardRecordService {


    @Resource
    DrawRecordMapper drawRecordMapper;

    @Resource
    private StudentMapper studentMapper;

    /**
     * 福利红包--中奖名单
     * @param awardRecordListParamDTO
     * @return
     */
    @Override
    public PageDTO<AwardRecordListDTO> listForWebapp(AwardRecordListParamDTO awardRecordListParamDTO) {
        PageDTO<AwardRecordListDTO> pageDTO = new PageDTO<>();
        List<AwardRecordListDTO> awardRecordListDTOList = Lists.newArrayList();
        List<PeDrawRecord> drawRecordList = drawRecordMapper.listForWebapp(awardRecordListParamDTO);
        if (drawRecordList!=null&&drawRecordList.size()>0){
            for (PeDrawRecord drawRecord : drawRecordList){
                AwardRecordListDTO awardRecordListDTO = new AwardRecordListDTO();
                BeanUtils.copyProperties(awardRecordListDTO,drawRecord);
                PeStudent student = studentMapper.getById(drawRecord.getUid());
                if(null != student){
                    awardRecordListDTO.setNickname(student.getNickname());
                }
                awardRecordListDTOList.add(awardRecordListDTO);
            }
        }
        pageDTO.setList(awardRecordListDTOList);
        pageDTO.setCount(drawRecordMapper.countForWebapp(awardRecordListParamDTO));
        return pageDTO;
    }


    /**
     * 福利红包导出--中奖名单
     * @param awardRecordListParamDTO
     * @return
     */
    @Override
    public List<AwardRecordListDTO> listAllForWebapp(AwardRecordListParamDTO awardRecordListParamDTO) {
        List<PeDrawRecord> drawRecordList = drawRecordMapper.listAllForWebapp(awardRecordListParamDTO);
        List<AwardRecordListDTO> awardRecordListDTOList = Lists.newArrayList();
        if (drawRecordList!=null&&drawRecordList.size()>0){
            for (PeDrawRecord drawRecord : drawRecordList){
                AwardRecordListDTO awardRecordListDTO = new AwardRecordListDTO();
                BeanUtils.copyProperties(awardRecordListDTO,drawRecord);
                PeStudent student = studentMapper.getById(drawRecord.getUid());
                if(null != student){
                    awardRecordListDTO.setNickname(student.getNickname());
                }
                awardRecordListDTOList.add(awardRecordListDTO);
            }
        }
        return awardRecordListDTOList;
    }
}
