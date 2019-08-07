package com.koolearn.club.mapper;


import com.koolearn.club.entity.PeStudentAccount;
import com.koolearn.framework.mybatis.annotation.DAO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@DAO
public interface StudentAccountMapper {

    /**
     * 插入学生数据
     *
     * @param peStudentAccount
     * @return
     */
    int addStudentAccount(PeStudentAccount peStudentAccount);


    /**
     * 查询学生账户信息
     *
     * @param stuId
     * @return
     */
    PeStudentAccount getStudentAccount(@Param("stuId") Integer stuId);


    /**
     * 更新学生账户
     *
     * @param peStudentAccount
     * @param redPocketAmount
     * @return
     */
    int updateStudentAccount(@Param("peStudentAccount") PeStudentAccount peStudentAccount,
                             @Param("redPocketAmount") BigDecimal redPocketAmount);

}
