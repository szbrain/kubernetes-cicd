package com.koolearn.club.impl;

import com.koolearn.club.constants.student.WithdrawRecordErrorCodeEnum;
import com.koolearn.club.dto.account.StudentAccountRespDTO;
import com.koolearn.club.dto.account.WithdrawRecordReqDTO;
import com.koolearn.club.entity.PeStudentAccount;
import com.koolearn.club.entity.PeWithdrawRecord;
import com.koolearn.club.mapper.StudentAccountMapper;
import com.koolearn.club.mapper.WithdrawRecordMapper;
import com.koolearn.club.service.IStudentAccountService;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

public class StudentAccountServiceImpl implements IStudentAccountService {

    @Resource
    private StudentAccountMapper studentAccountMapper;

    @Resource
    private WithdrawRecordMapper withdrawRecordMapper;

    /**
     * 学生余额查询
     *
     * @param stuId
     * @return
     */
    @Override
    public JSONResult<StudentAccountRespDTO> balance(Integer stuId) {
        JSONResult<StudentAccountRespDTO> jsonResult = new JSONResult<>();
        PeStudentAccount peStudentAccount = studentAccountMapper.getStudentAccount(stuId);
        if (peStudentAccount != null) {
            StudentAccountRespDTO studentAccountRespDTO = new StudentAccountRespDTO();
            BeanUtils.copyProperties(studentAccountRespDTO, peStudentAccount);
            jsonResult.success(studentAccountRespDTO);
        } else {
            StudentAccountRespDTO studentAccountRespDTO = new StudentAccountRespDTO();
            studentAccountRespDTO.setBlance(new BigDecimal(0.00));
            studentAccountRespDTO.setUid(stuId);
            jsonResult.success(studentAccountRespDTO);
        }
        return jsonResult;
    }

    /**
     * 插入提现记录
     * @param withdrawRecordReqDTO
     * @return
     */
    @Override
    @Transactional
    public JSONResult updatePeWithdrawRecord(WithdrawRecordReqDTO withdrawRecordReqDTO) {
        JSONResult jsonResult = new JSONResult();
        PeWithdrawRecord peWithdrawRecord = new PeWithdrawRecord();
        BeanUtils.copyProperties(peWithdrawRecord, withdrawRecordReqDTO);
        int result = withdrawRecordMapper.addWithdrawRecord(peWithdrawRecord);
        if (result > 0) {
            if (withdrawRecordReqDTO.getStatus()==1){
                PeStudentAccount peStudentAccount = studentAccountMapper.getStudentAccount(withdrawRecordReqDTO.getUid());
                if (peStudentAccount != null) {//增加账户信息
                    peStudentAccount.setUpdateTime(new Date());
                    studentAccountMapper.updateStudentAccount(peStudentAccount, new BigDecimal("0.00").subtract(withdrawRecordReqDTO.getAmount()));
                }
            }
            jsonResult.success(null);
        } else {
            jsonResult.fail(WithdrawRecordErrorCodeEnum.SAVE_FAIL.getCode(),
                    WithdrawRecordErrorCodeEnum.SAVE_FAIL.getMessage());
        }
        return jsonResult;
    }
}
