package com.koolearn.club.service;

import com.koolearn.club.dto.wechat.BaseTemplateVo;
import com.koolearn.club.dto.wechat.WechatMessageVo;

/**
 * Created by lvyangjun on 2018/4/10.
 */
public interface IWechatService {

    public boolean sendWXOpenMessageForStu(WechatMessageVo wechatMessageVo);

    public boolean sendWXOpenMessageForTeach(WechatMessageVo wechatMessageVo);

    public boolean sendWXOpenMessageForStu(BaseTemplateVo baseTemplateVo);

}
