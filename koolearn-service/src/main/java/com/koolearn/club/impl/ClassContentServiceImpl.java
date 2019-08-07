package com.koolearn.club.impl;

import com.koolearn.club.entity.PeClassContent;
import com.koolearn.club.mapper.ClassContentMapper;
import com.koolearn.club.service.IClassContentService;

import javax.annotation.Resource;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class ClassContentServiceImpl implements IClassContentService {

    @Resource
    private ClassContentMapper classContentMapper;

    @Override
    public PeClassContent getById(int id) {
        return classContentMapper.getById(id);
    }
}
