package com.koolearn.club.impl;

import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.TeachSourceEnum;
import com.koolearn.club.entity.PeTeacher;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.TeacherMapper;
import com.koolearn.club.service.IChannelService;
import com.koolearn.club.service.ITeacherService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class TeacherServiceImpl implements ITeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private IChannelService channelService;

    @Override
    public PeTeacher getById(int id) {
        PeTeacher teacher = teacherMapper.getById(id);
        if(null == teacher){
            throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_FOUND);
        }
        return teacher;
    }

    @Override
    public PeTeacher getBySid(String sid) {
        PeTeacher teacher = teacherMapper.getBySid(sid);
        if(null == teacher){
            throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_FOUND);
        }
        return teacher;
    }

    @Override
    @Transactional
    public int editTeacher(PeTeacher teacher) {
        PeTeacher teacherDb = teacherMapper.getById(teacher.getId());
        if(null == teacherDb){
            throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_FOUND);
        }
        if(null != teacher.getNickname()){
            teacherDb.setNickname(teacher.getNickname());
        }
        if(null != teacher.getIdentity()){
            teacherDb.setIdentity(teacher.getIdentity());
        }
        if(null != teacher.getAvatar()){
            teacherDb.setAvatar(teacher.getAvatar());
        }
        if(null != teacher.getDesc()){
            teacherDb.setDesc(teacher.getDesc());
        }
        return teacherMapper.update(teacherDb);
    }

    @Override
    @Transactional
    public int loginWechat(PeTeacher teacher) {
        PeTeacher dbTeacher = teacherMapper.getByOpenId(teacher.getOpenId());
        if(null != dbTeacher){ //已经登录过
            return teacherMapper.updateSid(teacher.getSid(),dbTeacher.getId());
        }
        teacher.setCreateTime(new Date());
        teacher.setSource(TeachSourceEnum.WECHAT.getCode());
        int count = teacherMapper.insert(teacher);
        /*final int teachId = teacher.getId();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
            //创建新增学员频道
            channelService.createChannel(ChannelTypeEnum.ADD_STU.getCode(),teachId);
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
             //创建班级学习报表
             channelService.createChannel(ChannelTypeEnum.LEARN_STAT.getCode(),teachId);
            }
        });
        executorService.shutdown();*/
        return count;
    }

    @Override
    public PeTeacher getByOpenId(String openId) {
        return teacherMapper.getByOpenId(openId);
    }

    @Override
    public int replaceTeacher(String newOpenId, String oldOpenId) {
        int count = 0;
        PeTeacher newTeacher = teacherMapper.getByOpenId(newOpenId);
        PeTeacher oldTeacher = teacherMapper.getByOpenId(oldOpenId);
        if(null != newTeacher && null != oldTeacher){
            count += teacherMapper.deleteById(newTeacher.getId());
            count += teacherMapper.updateOpenId(newOpenId, oldTeacher.getId());
        }
        return count;
    }
}
