package com.koolearn.club.impl;

import com.koolearn.club.constants.StuQuitTypeEnum;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.TeachSourceEnum;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.entity.PeStudentSetting;
import com.koolearn.club.entity.PrClassStudent;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.ClassStudentMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.mapper.StudentSettingMapper;
import com.koolearn.club.service.IStudentService;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class StudentServiceImpl implements IStudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ClassStudentMapper classStudentMapper;

    @Resource
    private StudentSettingMapper studentSettingMapper;

    @Override
    public PeStudent getById(int id) {
        return studentMapper.getById(id);
    }

    @Override
    public PageDTO<PeStudent> listByClassId(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<PeStudent> pageDTO = new PageDTO<>();
        //学生列表
        List<PeStudent> studentList = studentMapper.listByClassId(offset, pageSize, classId);
        pageDTO.setList(studentList);
        pageDTO.setCount(studentMapper.countByClassId(classId));
        return pageDTO;
    }

    @Override
    public List<PeStudent> listAllByClassId(int classId) {
        return  studentMapper.listAllByClassId(classId);
    }

    @Override
    public PageDTO<PeStudent> listUndoneTaskByClassId(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<PeStudent> pageDTO = new PageDTO<>();
        //学生列表
        List<PeStudent> studentList = studentMapper.listUndoneTaskByClassId(offset, pageSize, classId);
        pageDTO.setList(studentList);
        pageDTO.setCount(studentMapper.countUndoneTaskByClassId(classId));
        return pageDTO;
    }

    @Override
    public List<PeStudent> listAllUndoneTaskByClassId(int classId) {
        List<PeStudent> studentList = studentMapper.listAllUndoneTaskByClassId(classId,new DateTime().minusDays(1).toDate());
        return studentList;
    }

    @Override
    public PageDTO<PeStudent> listQuitStuByClassId(int offset, int pageSize, int classId) {
        //初始化返回对象
        PageDTO<PeStudent> pageDTO = new PageDTO<>();
        //学生列表
        List<PeStudent> studentList = studentMapper.listQuitStuByClassId(offset, pageSize, classId);
        pageDTO.setList(studentList);
        pageDTO.setCount(studentMapper.countQuitStuByClassId(classId));
        return pageDTO;
    }

    @Override
    @Transactional
    public int removeStudent(int stuId, int classId, boolean rejoin) {
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(classId,stuId);
        if(null == classStudent){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_UNJOINED);
        }
        if(null != classStudent.getIsQuit() && 1 == classStudent.getIsQuit()){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_REMOVED);
        }
        classStudent.setIsQuit((short)1);
        classStudent.setQuitTime(new Date());
        classStudent.setQuitType(StuQuitTypeEnum.REMOVE.getCode());
        if(rejoin){
            classStudent.setIsRejoin((short)1);
        }else{
            classStudent.setIsRejoin((short)0);
        }
        return classStudentMapper.update(classStudent);
    }

    @Override
    @Transactional
    public int insertStudent(PeStudent student) {
        PeStudent peStudent = studentMapper.getByOpenId(student.getOpenId());
        int count=0;
        if(null != peStudent){
            count=studentMapper.updateSid(student.getSid(),student.getId());
        }else {
            student.setCreateTime(new Date());
            student.setSource(TeachSourceEnum.WECHAT.getCode());
            count=studentMapper.insert(student);
        }

        return count;

    }

    @Override
    public int editStudent(PeStudent student) {
            PeStudent student1 = studentMapper.getById(student.getId());
            if(null == student1){
                throw new ClubServiceException(SystemErrorCode.BIZ_TEACH_NOT_FOUND);
            }
            if(null != student.getNickname()){
                student1.setNickname(student.getNickname());
            }
            if(null != student.getAvatar()){
                student1.setAvatar(student.getAvatar());
            }
            if(null != student.getDesc()){
                student1.setDesc(student.getDesc());
            }
            if (student.getSex()!=null){
                student1.setSex(student.getSex());
            }
            return studentMapper.update(student1);

    }

    @Override
    public PeStudent getBySid(String sid) {
        PeStudent student = studentMapper.getBySid(sid);
        if(null == student){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        return student;
    }
    @Override
    public PeStudent getByOpenId(String openId) {
        PeStudent student = studentMapper.getByOpenId(openId);
        if(null == student){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        return student;
    }

    @Override
    public boolean isInClass(Integer stuId, Integer classId) {
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(classId,stuId);
        if(null != classStudent){
            if(null != classStudent.getIsQuit() && 0 == classStudent.getIsQuit()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int setRemind(int stuId, int classId, int duration) {
        int count = 0;
        PeStudentSetting studentSetting = studentSettingMapper.getByStuIdClassId(stuId,classId);
        if(null != studentSetting){
            studentSetting.setRemindTime(duration);
            count += studentSettingMapper.update(studentSetting);
        }else{
            studentSetting = new PeStudentSetting();
            studentSetting.setRemindTime(duration);
            studentSetting.setClassId(classId);
            studentSetting.setStuId(stuId);
            studentSetting.setCreateTime(new Date());
            count += studentSettingMapper.insert(studentSetting);
        }
        return count;
    }

    @Override
    public int getRemindTime(int stuId, int classId) {
        PeStudentSetting studentSetting = studentSettingMapper.getByStuIdClassId(stuId,classId);
        if(null == studentSetting){
            return 0;
        }
        return studentSetting.getRemindTime();
    }
}
