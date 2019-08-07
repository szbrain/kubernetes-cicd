package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.entity.PeStudentSetting;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface StudentSettingMapper {
    PeStudent getById(int id);

    List<PeStudentSetting> getListHaveRemindTime();
    /**
     * 根据学生ID，班级ID查询
     * @param stuId
     * @param classId
     * @return
     */
    PeStudentSetting getByStuIdClassId(@Param("stuId") int stuId, @Param("classId") int classId);

    /**
     * 更新
     * @param studentSetting
     * @return
     */
    int update(PeStudentSetting studentSetting);

    /**
     * 新增
     * @param studentSetting
     * @return
     */
    int insert(PeStudentSetting studentSetting);
}
