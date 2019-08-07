package com.koolearn.club.mapper;

import com.koolearn.club.entity.PrClassStatStudent;
import com.koolearn.framework.mybatis.annotation.DAO;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ClassStatStudentMapper {

    int insert(PrClassStatStudent classStatStudent);

    void batchInsert(List<PrClassStatStudent> classStatStudentList);
}
