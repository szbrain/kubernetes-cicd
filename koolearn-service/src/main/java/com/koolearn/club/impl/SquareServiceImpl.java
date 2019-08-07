package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.common.SortDTO;
import com.koolearn.club.dto.square.SquareListDTO;
import com.koolearn.club.dto.square.SquareListParamDTO;
import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeSquare;
import com.koolearn.club.entity.PeTeacher;
import com.koolearn.club.exception.ClubException;
import com.koolearn.club.mapper.ClassMapper;
import com.koolearn.club.mapper.SquareMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.mapper.TeacherMapper;
import com.koolearn.club.service.ISquareService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class SquareServiceImpl implements ISquareService {

    private static final Integer MAX_SN = 99999;

    @Resource
    private SquareMapper squareMapper;
    @Resource
    private ClassMapper classMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private StudentMapper studentMapper;

    @Override
    public PeSquare getById(int id) {
        return squareMapper.getById(id);
    }

    @Override
    public PageDTO<SquareListDTO> listClassForManage(SquareListParamDTO squareListParamDTO) {
        PageDTO<SquareListDTO> pageDTO = new PageDTO<>();
        List<SquareListDTO> squareListDTOList = Lists.newArrayList();
        List<PeSquare> squareList = squareMapper.listSquareForManage(squareListParamDTO);
        for(PeSquare square : squareList){
            SquareListDTO squareListDTO = new SquareListDTO();
            squareListDTO.setSn(square.getSn());
            squareListDTO.setJoinTime(square.getJoinTime());
            PeClass peClass = classMapper.getById(square.getClassId());
            if(null != peClass){
                squareListDTO.setClassId(peClass.getId());
                squareListDTO.setClassName(peClass.getName());
                squareListDTO.setClassNum(studentMapper.countByClassId(peClass.getId()));
                PeTeacher teacher = teacherMapper.getById(peClass.getTeachId());
                if(null != teacher){
                    squareListDTO.setTeacher(teacher.getNickname());
                }
            }
            squareListDTOList.add(squareListDTO);
        }
        pageDTO.setList(squareListDTOList);
        pageDTO.setCount(squareMapper.countSquareForManage(squareListParamDTO));
        return pageDTO;
    }

    @Override
    @Transactional
    public int joinSquare(int classId) {
        PeClass peClass= classMapper.getById(classId);
        if(peClass == null){
            throw new ClubException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        PeSquare square = squareMapper.getByClassId(classId);
        if(square != null){
            throw new ClubException(SystemErrorCode.BIZ_CLASS_JOINED_SQUARE);
        }
        square = new PeSquare();
        Integer maxSn = squareMapper.getMaxSn();
        if(maxSn == null){
            square.setSn(1);
        }else{
            if(maxSn >= MAX_SN){
                throw new ClubException(SystemErrorCode.BIZ_CLASS_JOINED_SQUARE);
            }
            square.setSn(++ maxSn);
        }
        square.setJoinTime(new Date());
        square.setClassId(classId);
        return squareMapper.insert(square);
    }

    @Override
    @Transactional
    public int removeSquare(int classId) {
        PeClass peClass= classMapper.getById(classId);
        if(peClass == null){
            throw new ClubException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        PeSquare square = squareMapper.getByClassId(classId);
        if(square == null){
            throw new ClubException(SystemErrorCode.BIZ_CLASS_UNJOINED_SQUARE);
        }
        int count = squareMapper.delete(square.getId(),MAX_SN);
        //重排SN值
        count += squareMapper.refreshSn(square.getSn(),MAX_SN);
        return count;
    }

    @Override
    @Transactional
    public int sortSquare(List<SortDTO> sortDTOList) {
        int count = 0;
        for(SortDTO sortDTO : sortDTOList){
            int classId = sortDTO.getBusinessId();
            PeSquare square = squareMapper.getByClassId(classId);
            if(square == null){
                throw new ClubException(SystemErrorCode.BIZ_CLASS_UNJOINED_SQUARE);
            }
            square.setSn(sortDTO.getSn());
            count += squareMapper.update(square);
        }
        return count;
    }

    @Override
    public List<SquareListDTO> listClassForStu() {
        List<SquareListDTO> squareListDTOList = Lists.newArrayList();
        List<PeSquare> squareList = squareMapper.listClassForStu();
        for(PeSquare square : squareList){
            SquareListDTO squareListDTO = new SquareListDTO();
            squareListDTO.setSn(square.getSn());
            squareListDTO.setJoinTime(square.getJoinTime());
            PeClass peClass = classMapper.getById(square.getClassId());
            if(null != peClass){
                squareListDTO.setClassId(peClass.getId());
                squareListDTO.setClassName(peClass.getName());
                squareListDTO.setClassNum(studentMapper.countByClassId(peClass.getId()));
                squareListDTO.setClassCover(peClass.getCoverUrl());
                PeTeacher teacher = teacherMapper.getById(peClass.getTeachId());
                if(null != teacher){
                    squareListDTO.setTeacher(teacher.getNickname());
                }
            }
            squareListDTOList.add(squareListDTO);
        }
        return squareListDTOList;
    }
}
