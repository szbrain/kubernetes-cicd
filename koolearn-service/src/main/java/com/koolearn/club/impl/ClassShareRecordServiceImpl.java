package com.koolearn.club.impl;

import com.koolearn.club.entity.PeClassShareRecord;
import com.koolearn.club.mapper.ClassShareRecordMapper;
import com.koolearn.club.service.IClassShareRecordService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/15.
 */
public class ClassShareRecordServiceImpl implements IClassShareRecordService {

    @Resource
    private ClassShareRecordMapper classShareRecordMapper;

    /**
     * 学员分享课程记录列表
     *
     * @param classId
     * @return
     */
    @Override
    public List<PeClassShareRecord> getList(int offset, int pageSize, int classId) {
        return classShareRecordMapper.getList(offset,pageSize,classId);
    }

    @Override
    public int getListCount(int classId) {
        List<PeClassShareRecord> classShareRecordList=classShareRecordMapper.getListCount(classId);
        if (classShareRecordList!=null&&classShareRecordList.size()>0){
            return classShareRecordList.size();
        }else {
            return 0;
        }
    }

    /**
     * 保持学员分享课程记录
     *
     * @param stuId
     * @param classId
     * @return
     */
    @Override
    public int save(int stuId, int classId) {


        PeClassShareRecord classShareRecord = classShareRecordMapper.get(stuId, classId);
        if (classShareRecord==null){
            classShareRecord = new PeClassShareRecord();
            classShareRecord.setStuId(stuId);
            classShareRecord.setClassId(classId);
            classShareRecord.setCreateTime(new Date());
            return classShareRecordMapper.save(classShareRecord);
        }else {
            return 0;
        }
    }
}
