package com.koolearn.club.impl;

import com.google.common.collect.Maps;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.TeachTokenStatusEnum;
import com.koolearn.club.entity.PeTeachToken;
import com.koolearn.club.exception.ClubException;
import com.koolearn.club.mapper.TeachTokenMapper;
import com.koolearn.club.service.ITeachTokenService;
import com.koolearn.club.utils.RandomUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class TeachTokenServiceImpl implements ITeachTokenService {

    @Resource
    private TeachTokenMapper teachTokenMapper;
    @Override
    public PeTeachToken getById(int id) {
        return teachTokenMapper.getById(id);
    }

    @Override
    public String insertCode() {
        String code = RandomUtil.generateLowerString(10);
        while(teachTokenMapper.getByCode(code) != null){
            code = RandomUtil.generateLowerString(10);
        }
        PeTeachToken teachToken = new PeTeachToken();
        teachToken.setCode(code);
        teachToken.setStatus(TeachTokenStatusEnum.CODE.getCode());
        teachToken.setCreateTime(new Date());
        teachTokenMapper.insert(teachToken);
        return code;
    }

    @Override
    public Map checkLogin(String code) {
        PeTeachToken teachToken = teachTokenMapper.getByCode(code);
        if(null == teachToken){
            throw new ClubException(SystemErrorCode.CODE_ERROR);
        }
        Map retMap = Maps.newHashMap();
        retMap.put("status", teachToken.getStatus());
        if(teachToken.getStatus() == TeachTokenStatusEnum.LOGIN.getCode()){ //已经登录
            retMap.put("teachId", teachToken.getTeachId());
        }
        return retMap;
    }

    @Override
    public int login(String code, int teachId) {
        PeTeachToken teachToken = teachTokenMapper.getByCode(code);
        if(null == teachToken){
            throw new ClubException(SystemErrorCode.CODE_ERROR);
        }
        teachToken.setTeachId(teachId);
        teachToken.setStatus(TeachTokenStatusEnum.TOKEN.getCode());
        teachTokenMapper.update(teachToken);
        return teachToken.getId();
    }

    @Override
    public Integer confirmLogin(int id) {
        PeTeachToken teachToken = teachTokenMapper.getById(id);
        if(null == teachToken){
            throw new ClubException(SystemErrorCode.TOKEN_NOT_FOUND);
        }
        if(teachToken.getStatus() != TeachTokenStatusEnum.TOKEN.getCode()){
            throw new ClubException(SystemErrorCode.TOKEN_NOT_FOUND);
        }
        teachTokenMapper.updateStatus(id, TeachTokenStatusEnum.LOGIN.getCode());
        return teachToken.getTeachId();
    }

}
