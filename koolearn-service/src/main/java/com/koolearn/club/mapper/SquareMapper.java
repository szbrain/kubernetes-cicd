package com.koolearn.club.mapper;

import com.koolearn.club.dto.square.SquareListParamDTO;
import com.koolearn.club.entity.PeSquare;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface SquareMapper {
    PeSquare getById(int id);


    /**
     * 新建广场
     * @param square
     * @return
     */
    int insert(PeSquare square);


    /**
     * 分页查询
     * @param squareListParamDTO
     * @return
     */
    List<PeSquare> listSquareForManage(SquareListParamDTO squareListParamDTO);

    int countSquareForManage(SquareListParamDTO squareListParamDTO);

    /**
     *  查询最大序号
     * @return
     */
    Integer getMaxSn();

    /**
     *  通过班级查询广场
     * @param classId
     * @return
     */
    PeSquare getByClassId(int classId);

    /**
     *  删除
     * @param id
     * @param maxSn
     * @return
     */
    int delete(@Param("id") Integer id, @Param("maxSn") Integer maxSn);

    /**
     *  重排SN值
     * @param sn
     * @param maxSn
     * @return
     */
    int refreshSn(@Param("sn") Integer sn, @Param("maxSn") Integer maxSn);

    /**
     *  更新
     * @param square
     * @return
     */
    int update(PeSquare square);

    /**
     *  查询广场班级列表
     * @return
     */
    List<PeSquare> listClassForStu();
}
