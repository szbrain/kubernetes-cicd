package com.koolearn.club.service;

import com.koolearn.club.dto.account.WithdrawRecordReqDTO;
import com.koolearn.club.dto.account.StudentAccountRespDTO;
import com.koolearn.club.utils.JSONResult;

/**
 * Created by lvyangjun on 2018/05/17.
 */

public interface IStudentAccountService {

    /**
     * 学生账户余额查询
     * @param stuId
     * @return
     */
    JSONResult<StudentAccountRespDTO> balance(Integer stuId);

    /**
     * 记录学生提现记录
     * @param withdrawRecordReqDTO
     * @return
     */
    JSONResult updatePeWithdrawRecord(WithdrawRecordReqDTO withdrawRecordReqDTO);



}
