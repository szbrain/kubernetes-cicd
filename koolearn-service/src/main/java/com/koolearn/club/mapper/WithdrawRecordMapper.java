package com.koolearn.club.mapper;


import com.koolearn.club.entity.PeWithdrawRecord;
import com.koolearn.framework.mybatis.annotation.DAO;

/**
 * 学生提现记录Mapper
 */
@DAO
public interface WithdrawRecordMapper {

    /**
     * 插入学生提现记录
     *
     * @param peStudentAccount
     * @return
     */
    int addWithdrawRecord(PeWithdrawRecord peStudentAccount);


}
