package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.dto.refuelposter.RefuelPosterDTO;
import com.koolearn.club.dto.refuelposter.RefuelPosterKeywordsDTO;
import com.koolearn.club.entity.PeRefuelPoster;
import com.koolearn.club.entity.PeRefuelPosterKeywords;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.RefuelPosterKeywordsMapper;
import com.koolearn.club.mapper.RefuelPosterMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.service.IRefuelPosterService;
import com.koolearn.club.utils.RandomUtil;
import com.koolearn.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class RefuelPosterServiceImpl implements IRefuelPosterService {

    @Resource
    private RefuelPosterMapper refuelPosterMapper;
    @Resource
    private RefuelPosterKeywordsMapper refuelPosterKeywordsMapper;
    @Resource
    private StudentMapper studentMapper;

    @Override
    public PeRefuelPoster getById(int id) {
        return refuelPosterMapper.getById(id);
    }

    @Override
    public List<RefuelPosterKeywordsDTO> listKeywords(String keywordIds) {
        List<RefuelPosterKeywordsDTO> refuelPosterKeywordsDTOList = Lists.newArrayList();
        List keywordIdList = Lists.newArrayList();
        if(StringUtils.isNoneBlank(keywordIds)){
            keywordIdList = Arrays.asList(keywordIds.split(","));
        }
        List<PeRefuelPosterKeywords>  refuelPosterKeywordsList = refuelPosterKeywordsMapper.listKeywords(keywordIdList);
        if(refuelPosterKeywordsList.size() > 3){
            Set<Integer> indexSet = RandomUtil.generateMultiNum(refuelPosterKeywordsList.size(), 3);
            for(Integer index : indexSet){
                RefuelPosterKeywordsDTO refuelPosterKeywordsDTO = new RefuelPosterKeywordsDTO();
                BeanUtils.copyProperties(refuelPosterKeywordsDTO, refuelPosterKeywordsList.get(index));
                refuelPosterKeywordsDTOList.add(refuelPosterKeywordsDTO);
            }
        }else{
            for(PeRefuelPosterKeywords refuelPosterKeywords : refuelPosterKeywordsList){
                RefuelPosterKeywordsDTO refuelPosterKeywordsDTO = new RefuelPosterKeywordsDTO();
                BeanUtils.copyProperties(refuelPosterKeywordsDTO, refuelPosterKeywords);
                refuelPosterKeywordsDTOList.add(refuelPosterKeywordsDTO);
            }
        }
        return refuelPosterKeywordsDTOList;
    }

    @Override
    public Integer generate(int studentId, String epithet, String collegeName, Integer keywordId) {
        PeRefuelPosterKeywords refuelPosterKeywords = refuelPosterKeywordsMapper.getById(keywordId);
        if(null == refuelPosterKeywords){
            throw new ClubServiceException(SystemErrorCode.BIZ_REFUEL_POSTER_KEYWORDS_NOT_FOUND);
        }
        PeRefuelPoster refuelPoster = new PeRefuelPoster();
        refuelPoster.setKeyword(refuelPosterKeywords.getContent());
        refuelPoster.setCreateTime(new Date());
        refuelPoster.setCollegeName(collegeName);
        refuelPoster.setEpithet(epithet);
        refuelPoster.setStuId(studentId);
        refuelPosterMapper.insert(refuelPoster);
        return refuelPoster.getId();
    }

    @Override
    public RefuelPosterDTO getByStuId(int stuId) {
        RefuelPosterDTO refuelPosterDTO = new RefuelPosterDTO();
        PeStudent student = studentMapper.getById(stuId);
        if(null == student){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        PeRefuelPoster refuelPoster = refuelPosterMapper.getByStuId(stuId);
        if(null == refuelPoster){
            throw new ClubServiceException(SystemErrorCode.BIZ_REFUEL_POSTER_NOT_FOUND);
        }
        BeanUtils.copyProperties(refuelPosterDTO, refuelPoster);
        return refuelPosterDTO;
    }


}
