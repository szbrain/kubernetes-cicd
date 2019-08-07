package com.koolearn.club.service;

import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.dto.common.SortDTO;
import com.koolearn.club.dto.square.SquareListDTO;
import com.koolearn.club.dto.square.SquareListParamDTO;
import com.koolearn.club.entity.PeSquare;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface ISquareService {
    PeSquare getById(int id);

    /**
     * 分页查询广场班级
     * @param squareListParamDTO
     * @return
     */
    PageDTO<SquareListDTO> listClassForManage(SquareListParamDTO squareListParamDTO);

    /**
     *  加入广场
     * @param classId
     * @return
     */
    int joinSquare(int classId);

    /**
     * 移除广场
     * @param classId
     * @return
     */
    int removeSquare(int classId);

    /**
     *  广场排序
     * @param sortDTOList
     * @return
     */
    int sortSquare(List<SortDTO> sortDTOList);

    /**
     *  查询广场班级列表
     * @return
     */
    List<SquareListDTO> listClassForStu();
}
