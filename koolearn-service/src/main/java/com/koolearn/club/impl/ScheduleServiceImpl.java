package com.koolearn.club.impl;

import com.google.common.collect.Lists;
import com.koolearn.club.constants.StatTypeEnum;
import com.koolearn.club.constants.TeachMessageTypeEnum;
import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeClassStat;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.entity.PrClassStatStudent;
import com.koolearn.club.mapper.ClassMapper;
import com.koolearn.club.mapper.ClassStatMapper;
import com.koolearn.club.mapper.ClassStatStudentMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.service.IScheduleService;
import com.koolearn.club.service.ITeachMessageService;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class ScheduleServiceImpl implements IScheduleService {

    @Resource
    private ClassMapper classMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ClassStatMapper classStatMapper;
    @Resource
    private ClassStatStudentMapper classStatStudentMapper;
    @Resource
    private ITeachMessageService teachMessageService;


    @Override
    @Transactional
    public void statClass(int classId) {
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            return;
        }
        PeClassStat classStat = classStatMapper.getByClassId(classId,new DateTime().minusDays(1).toDate());//是否已经统计过
        if(null != classStat){
            return;
        }
        classStat = new PeClassStat();
        classStat.setCreateTime(new Date());
        classStat.setStatTime(new DateTime().minusDays(1).toDate());
        classStat.setClassId(classId);
        //统计班级下学生数
        int stuCount = studentMapper.countByClassId(classId);
        classStat.setCount(stuCount);
        //统计新加入学生
        //System.out.println("----23----"+new DateTime().minusDays(1).toDate());
        List<PeStudent> joinStuList = studentMapper.listAllJoinStuByClassId(classId, new DateTime().minusDays(1).toDate());
        classStat.setJoinCount(joinStuList.size());
        //统计退出学生
        List<PeStudent> quitStuList = studentMapper.listAllQuitStuByClassId(classId, new DateTime().minusDays(1).toDate());
        classStat.setQuitCount(quitStuList.size());
        //统计未完成任务学生
        List<PeStudent> undoneTaskStuList = studentMapper.listAllUndoneTaskByClassId(classId, new DateTime().minusDays(1).toDate());
        classStat.setTaskUndoneCount(undoneTaskStuList.size());
        //插入统计表
        classStatMapper.insert(classStat);
        final int classStatId = classStat.getId();
        final boolean sendNewJoinFlag = joinStuList.size() > 0 ? true : false;
        //新增新加入学生
        if (joinStuList.size() > 0) {
            List<PrClassStatStudent> classStatStudentList = Lists.newArrayList();
            for (PeStudent student : joinStuList) {
                PrClassStatStudent classStatStudent = new PrClassStatStudent();
                classStatStudent.setClassStatId(classStatId);
                classStatStudent.setStuId(student.getId());
                classStatStudent.setType(StatTypeEnum.JOIN.getCode());
                classStatStudentList.add(classStatStudent);
            }
            classStatStudentMapper.batchInsert(classStatStudentList);
        }
        //新增退出学生
        if (quitStuList.size() > 0) {
           /* List<PrClassStatStudent> classStatStudentList = Lists.newArrayList();
            for (PeStudent student : quitStuList) {
                PrClassStatStudent classStatStudent = new PrClassStatStudent();
                classStatStudent.setClassStatId(classStatId);
                classStatStudent.setStuId(student.getId());
                classStatStudent.setType(StatTypeEnum.QUIT.getCode());
                classStatStudentList.add(classStatStudent);
            }
            classStatStudentMapper.batchInsert(classStatStudentList);*/
        }
        //新增未完成任务学生
        if (undoneTaskStuList.size() > 0) {
            /*List<PrClassStatStudent> classStatStudentList = Lists.newArrayList();
            for (PeStudent student : undoneTaskStuList) {
                PrClassStatStudent classStatStudent = new PrClassStatStudent();
                classStatStudent.setClassStatId(classStatId);
                classStatStudent.setStuId(student.getId());
                classStatStudent.setType(StatTypeEnum.TASK_UNDONE.getCode());
                classStatStudentList.add(classStatStudent);
            }
            classStatStudentMapper.batchInsert(classStatStudentList);*/
        }

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //发送班级报表的消息给老师
                teachMessageService.sendMessage(TeachMessageTypeEnum.STAT_CLASS.getCode(), classStatId);
                if(sendNewJoinFlag){
                    //发送新增学员的消息给老师
                    teachMessageService.sendMessage(TeachMessageTypeEnum.NEW_JOIN.getCode(), classStatId);
                }
            }
        });
        executorService.shutdown();
    }
}
