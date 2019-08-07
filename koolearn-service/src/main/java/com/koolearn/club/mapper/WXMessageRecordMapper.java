package com.koolearn.club.mapper;


import com.koolearn.club.entity.PeWXMessageRecord;
import com.koolearn.framework.mybatis.annotation.DAO;

@DAO
public interface WXMessageRecordMapper {

    /**
     * 创建
     *
     * @param peWXMessageRecord
     * @return
     */
    Integer insert(PeWXMessageRecord peWXMessageRecord);


}