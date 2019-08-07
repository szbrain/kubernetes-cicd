package com.koolearn.club.impl;

import com.koolearn.club.entity.PeWXMessageRecord;
import com.koolearn.club.mapper.WXMessageRecordMapper;
import com.koolearn.club.service.IWXMessageService;
import com.koolearn.club.utils.JSONResult;

import javax.annotation.Resource;

public class WXMessageServiceImpl implements IWXMessageService {

    @Resource
    WXMessageRecordMapper wxMessageRecordMapper;

    @Override
    public JSONResult add(PeWXMessageRecord peWXMessageRecord) {
        int result = wxMessageRecordMapper.insert(peWXMessageRecord);
        if (result > 0) {
            return new JSONResult().success(null);
        } else {
            return new JSONResult().fail(31000, "保存微信消息记录失败");
        }
    }
}
