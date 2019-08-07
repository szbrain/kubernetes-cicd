package com.koolearn.club.impl;

import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.dto.share.CardParamDTO;
import com.koolearn.club.entity.PeClass;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.entity.PrClassStudent;
import com.koolearn.club.exception.ClubException;
import com.koolearn.club.exception.ClubServiceException;
import com.koolearn.club.mapper.ClassMapper;
import com.koolearn.club.mapper.ClassStudentMapper;
import com.koolearn.club.mapper.LearningMapper;
import com.koolearn.club.mapper.StudentMapper;
import com.koolearn.club.service.IShareService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lilong01 on 2018/2/27.
 */
public class ShareServiceImpl implements IShareService {

    @Resource
    private ClassStudentMapper classStudentMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ClassMapper classMapper;
    @Resource
    private LearningMapper learningMapper;
    @Override
    public boolean qrCodeUrlExist(int stuId, int classId) {
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(classId,stuId);
        if(null == classStudent){
            throw new ClubException(SystemErrorCode.BIZ_STU_CLASS_UNJOINED);
        }else{
            if(null != classStudent.getIsQuit() && 1 == classStudent.getIsQuit()){
                throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_REMOVED);
            }
            if(StringUtils.isNoneBlank(classStudent.getQRCode())){
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public int saveOrUpdateQRCodeUrl(int stuId, int classId, String url) {
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(classId,stuId);
        if(null == classStudent){
            throw new ClubException(SystemErrorCode.BIZ_STU_CLASS_UNJOINED);
        }
        if (classStudent.getIsQuit()!=null&&classStudent.getIsQuit()==1)
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_REMOVED);

        return classStudentMapper.updateQRCodeUrl(url, classStudent.getId());
    }

    @Override
    public CardParamDTO getCardParams(int stuId, int classId) {
        PeStudent student = studentMapper.getById(stuId);
        if(null == student) {
            throw new ClubException(SystemErrorCode.BIZ_STU_NOT_FOUND);
        }
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        CardParamDTO cardParamDTO = new CardParamDTO();
        cardParamDTO.setAvatarUrl(student.getAvatar());
        cardParamDTO.setNickName(student.getNickname());
        cardParamDTO.setClassName(peClass.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        cardParamDTO.setCheckTime(format.format(new Date()));
        //班级人数
        int classMembers = studentMapper.countByClassId(classId);
        cardParamDTO.setClassMembers(classMembers);
        //统计学生打卡天数
        int checkDays = 0;
        List<String> dayList = learningMapper.listForStuLearning(stuId, classId);
        if(null != dayList && dayList.size() > 0){
            checkDays = dayList.size();
        }
        cardParamDTO.setCheckDays(checkDays);
        //二维码的URL
        PrClassStudent classStudent = classStudentMapper.getByClassIdStuId(classId,stuId);
        if(null == classStudent ){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_UNJOINED);
        }
        if(null != classStudent.getIsQuit() && 1 == classStudent.getIsQuit()){
            throw new ClubServiceException(SystemErrorCode.BIZ_STU_CLASS_REMOVED);
        }
        cardParamDTO.setQRCodeUrl(classStudent.getQRCode());
        return cardParamDTO;
    }

    @Override
    public CardParamDTO getCardParamsForTeach(int classId) {
        CardParamDTO cardParamDTO = new CardParamDTO();
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass) {
            throw new ClubException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        cardParamDTO.setClassName(peClass.getName());
        cardParamDTO.setQRCodeUrl(peClass.getQRCode());
        return cardParamDTO;
    }

    @Override
    public boolean qrCodeUrlExistForTeach(int classId) {
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }else{
            if(StringUtils.isNoneBlank(peClass.getQRCode())){
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public int saveOrUpdateQRCodeUrlForTeach(int classId, String url) {
        PeClass peClass = classMapper.getById(classId);
        if(null == peClass){
            throw new ClubException(SystemErrorCode.BIZ_CLASS_NOT_FOUND);
        }
        return classMapper.updateQRCodeUrl(url, peClass.getId());
    }
}
