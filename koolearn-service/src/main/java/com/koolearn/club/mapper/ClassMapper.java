package com.koolearn.club.mapper;

import com.koolearn.club.dto.classroom.ClassStatDoneStuListReqDTO;
import com.koolearn.club.dto.classroom.ClassStatDoneStuListRespDTO;
import com.koolearn.club.dto.classroom.ClassStatRemindUndoneStuReqDTO;
import com.koolearn.club.dto.classroom.manage.ClassListParamDTO;
import com.koolearn.club.entity.PeClass;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface ClassMapper {
    PeClass getById(int id);

    /**
     * 查询学生加入的班级列表
     * @param offset
     * @param pageSize
     * @param stuId
     * @return
     */
    List<PeClass> listClassForStu(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("stuId") int stuId);


    /**
     * 查询学生加入的班级数量
     * @param stuId
     * @return
     */
    int countClassForStu(int stuId);

    /**
     * 查询老师创建的班级列表
     * @param offset
     * @param pageSize
     * @param teachId
     * @return
     */
    List<PeClass> listClassForTeach(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("teachId") int teachId);

    /**
     * 查询老师创建的班级数量
     * @param teachId
     * @return
     */
    int countClassForTeach(int teachId);

    /**
     * 创建班级
     * @param peClass
     * @return
     */
    int insert(PeClass peClass);

    /**
     * 更新班级
     * @param peClass
     * @return
     */
    int update(PeClass peClass);

    /**
     *  更新状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(@Param("id") int id, @Param("status") short status);

    /**
     * 根据状态查找班级
      * @param status
     * @return
     */
    List<PeClass> allClassesByStatus(@Param("status") short status);

    /**
     * 更新老师班级二维码
     * @param url
     * @param id
     * @return
     */
    int updateQRCodeUrl(@Param("url") String url, @Param("id") int id);

    /**
     * 分页查询（manage）
     * @param classListParamDTO
     * @return
     */
    List<PeClass> listClassForManage(ClassListParamDTO classListParamDTO);

    int countClassForManage(ClassListParamDTO classListParamDTO);

    /**
     * 统计班级下一定时间段内打卡的学生(包括学生打卡次数，打卡天数)
     * @param classStatDoneStuListReqDTO
     * @return
     */
    List<ClassStatDoneStuListRespDTO> listClassStatDoneStu(ClassStatDoneStuListReqDTO classStatDoneStuListReqDTO);
    int countClassStatDoneStu(ClassStatDoneStuListReqDTO classStatDoneStuListReqDTO);

    /**
     * 统计班级下一定时间段内未打卡的学生
     * @param classStatRemindUndoneStuReqDTO
     * @return
     */
    List<Integer> listClassStatUndoneStu(ClassStatRemindUndoneStuReqDTO classStatRemindUndoneStuReqDTO);
}
