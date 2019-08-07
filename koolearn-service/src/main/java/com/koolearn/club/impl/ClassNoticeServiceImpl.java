package com.koolearn.club.impl;

import com.koolearn.club.entity.PeClassNotice;
import com.koolearn.club.mapper.ClassNoticeMapper;
import com.koolearn.club.service.IClassNoticeService;

import javax.annotation.Resource;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class ClassNoticeServiceImpl implements IClassNoticeService {

    @Resource
    private ClassNoticeMapper classNoticeMapper;

    @Override
    public PeClassNotice getById(int id) {
        return classNoticeMapper.getById(id);
    }
}
