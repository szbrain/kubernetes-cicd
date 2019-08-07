package com.koolearn.club.service;

import com.koolearn.club.entity.PeTeacher;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface ITeacherService {

    PeTeacher getById(int id);

    /**
     * 根据sid查询老师
     * @param sid
     * @return
     */
    PeTeacher getBySid(String sid);

    /**
     * 编辑老师
     * @param teacher
     * @return
     */
    int editTeacher(PeTeacher teacher);

    /**
     * 老师登录微信
     * @param teacher
     * @return
     */
    int loginWechat(PeTeacher teacher);

    /**
     * 根据openId 查询老师
     *
     * @param openId
     * @return
     */
    PeTeacher getByOpenId(String openId);

    /**
     * 替换老师
     * @param newOpenId
     * @param oldOpenId
     * @return
     */
    int replaceTeacher(String newOpenId, String oldOpenId);
}
