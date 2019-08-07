package com.koolearn.club.service;

import com.koolearn.club.entity.PeTeachToken;

import java.util.Map;

/**
 * Created by lilong01 on 2018/2/27.
 */
public interface ITeachTokenService {
    PeTeachToken getById(int id);

    /**
     * 插入code
     * @return
     */
    String insertCode();

    /**
     * 检查是否登录
     * @param code
     * @return
     */
    Map checkLogin(String code);

    /**
     *  扫码登录
     * @param code
     * @param teachId
     * @return
     */
    int login(String code, int teachId);

    /**
     *  确认登录
     * @param id
     * @return
     */
    Integer confirmLogin(int id);
}
