package com.koolearn.club.mapper;

import com.koolearn.club.entity.PeTeacher;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lilong01 on 2018/2/27.
 */
@DAO
public interface TeacherMapper {
    PeTeacher getById(int id);

    /**
     * 更新老师对象
     * @param teacher
     * @return
     */
    int update(PeTeacher teacher);

    /**
     *  新增老师对象
     * @param teacher
     * @return
     */
    int insert(PeTeacher teacher);

    /**
     * 根据sid查询老师
     * @param sid
     * @return
     */
    PeTeacher getBySid(String sid);

    /**
     * 根据openId查询老师
     * @param openId
     * @return
     */
    PeTeacher getByOpenId(String openId);

    /**
     *  更新sid
     * @param sid
     * @param id
     * @return
     */
    int updateSid(@Param("sid") String sid, @Param("id") int id);

    /**
     * 更新openId
     * @param openId
     * @param id
     * @return
     */
    int updateOpenId(@Param("openId") String openId, @Param("id") int id);

    /**
     *  通过id删除
     * @param id
     * @return
     */
    int deleteById(int id);
}
