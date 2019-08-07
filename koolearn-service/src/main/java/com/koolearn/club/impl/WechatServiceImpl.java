package com.koolearn.club.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.koolearn.club.constants.FormIdTypeEnum;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.WechatTemplateTypeEnum;
import com.koolearn.club.dto.wechat.BaseTemplateVo;
import com.koolearn.club.dto.wechat.RemindTemplateVo;
import com.koolearn.club.dto.wechat.WechatMessageVo;
import com.koolearn.club.entity.PeFormId;
import com.koolearn.club.entity.PeStudent;
import com.koolearn.club.entity.PeTeacher;
import com.koolearn.club.exception.ClubException;
import com.koolearn.club.service.IFormIdService;
import com.koolearn.club.service.IStudentService;
import com.koolearn.club.service.ITeacherService;
import com.koolearn.club.service.IWechatService;
import com.koolearn.club.utils.HttpUtil;
import com.koolearn.framework.common.utils.PropertiesConfigUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lvyangjun on 2018/4/10.
 */
public class WechatServiceImpl implements IWechatService {

    //学生端accessToken
    private static final Map<String, String> stuAccessTokenMap = new HashMap<>();
    //老师端accessToken
    private static final Map<String, String> teachAccessTokenMap = new HashMap<>();

    @Resource
    private IStudentService studentService;

    @Resource
    private ITeacherService teacherService;

    @Resource
    private IFormIdService formIdService;
    //发送通知消息
    private static final String API_SEND_WXOPEN_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
    private static final String WXOPEN_MESSAGE_PAGE = "pages/notice/notice?classID=CLASS_ID";
    //获取ACCESS_TOKEN URL
    private static final String API_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";


    @Override
    public boolean sendWXOpenMessageForStu(WechatMessageVo wechatMessageVo) {
        Map map = Maps.newConcurrentMap();
        String path = API_SEND_WXOPEN_MESSAGE.replace("ACCESS_TOKEN", getAccessTokenForStu());
        String templateId = PropertiesConfigUtils.getProperty("wechat_stu_template_id");
        String page = WXOPEN_MESSAGE_PAGE.replace("CLASS_ID", String.valueOf(wechatMessageVo.getClassId()));
        map.put("template_id", templateId);
        map.put("page", page);
        Map data = Maps.newConcurrentMap();
        Map keyword1 = Maps.newConcurrentMap();
        keyword1.put("value", wechatMessageVo.getCaseNo());//案号
        keyword1.put("color", "#2D92F0");
        Map keyword2 = Maps.newConcurrentMap();
        keyword2.put("value", wechatMessageVo.getSender());//发送人
        keyword2.put("color", "#000000");
        Map keyword3 = Maps.newConcurrentMap();
        keyword3.put("value", wechatMessageVo.getDetail());//详情
        keyword3.put("color", "#000000");
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        map.put("data", data);
        // map.put("emphasis_keyword", "keyword1.DATA");
        String openId = "";
        String formId = null;
        if (wechatMessageVo.getUserType() == FormIdTypeEnum.STU.getCode()) {
            PeStudent student = studentService.getById(wechatMessageVo.getUserId());
            openId = student.getOpenId();
            map.put("touser", openId);
        }
        int recode = 41028;
        while (recode == 41028 || recode == 41029) { //form_id不正确，重新寻找新的form_id,form_id已经被使用
            formId = getFormId(openId, FormIdTypeEnum.STU.getCode());
            if (formId == null) {
                break;
            }
            map.put("form_id", formId);
            JSONObject re = HttpUtil.httpsRequest(path, "POST", JSON.toJSONString(map));
            recode = re.getIntValue("errcode");
            if (recode == 41028 || recode == 41029) {
                formIdService.updateIsUse(openId, formId);
            }
        }
        if (recode == 0) {
            //发送成功，将使用过的form_id置为已使用
            formIdService.updateIsUse(openId, formId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean sendWXOpenMessageForTeach(WechatMessageVo wechatMessageVo) {
        Map map = Maps.newConcurrentMap();
        String path = API_SEND_WXOPEN_MESSAGE.replace("ACCESS_TOKEN", getAccessTokenForTeach());
        String templateId = PropertiesConfigUtils.getProperty("wechat_template_id");
        map.put("template_id", templateId);
        map.put("page", "pages/msg/msg");
        Map data = Maps.newConcurrentMap();
        Map keyword1 = Maps.newConcurrentMap();
        keyword1.put("value", wechatMessageVo.getCaseNo());//案号
        keyword1.put("color", "#2D92F0");
        Map keyword2 = Maps.newConcurrentMap();
        keyword2.put("value", wechatMessageVo.getSender());//发送人
        keyword2.put("color", "#000000");
        Map keyword3 = Maps.newConcurrentMap();
        keyword3.put("value", wechatMessageVo.getDetail());//详情
        keyword3.put("color", "#000000");
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        map.put("data", data);
        // map.put("emphasis_keyword", "keyword1.DATA");
        String openId = "";
        String formId = null;
        if (wechatMessageVo.getUserType() == FormIdTypeEnum.TEACH.getCode()) {
            PeTeacher peTeacher = teacherService.getById(wechatMessageVo.getUserId());
            openId = peTeacher.getOpenId();
            map.put("touser", openId);
        }
        int recode = 41028;
        while (recode == 41028 || recode == 41029) { //form_id不正确，重新寻找新的form_id,form_id已经被使用
            formId = getFormId(openId, FormIdTypeEnum.TEACH.getCode());
            if (formId == null) {
                break;
            }
            map.put("form_id", formId);
            JSONObject re = HttpUtil.httpsRequest(path, "POST", JSON.toJSONString(map));
            recode = re.getIntValue("errcode");
            if (recode == 41028 || recode == 41029) {
                formIdService.updateIsUse(openId, formId);
            }
        }
        if (recode == 0) {
            //发送成功，将使用过的form_id置为已使用
            formIdService.updateIsUse(openId, formId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean sendWXOpenMessageForStu(BaseTemplateVo baseTemplateVo) {
        Map map = Maps.newConcurrentMap();
        String path = API_SEND_WXOPEN_MESSAGE.replace("ACCESS_TOKEN", getAccessTokenForStu());
        map.put("template_id", baseTemplateVo.getTemplateId());
        map.put("page", baseTemplateVo.getPage());

        if (baseTemplateVo.getTemplateType() == WechatTemplateTypeEnum.REMIND.getCode()) {//任务结束提醒
            RemindTemplateVo remindTemplateVo = (RemindTemplateVo) baseTemplateVo;
            Map data = remindTemplateVo.getDataMap();
            map.put("data", data);
        }
        String openId = "";
        String formId = null;
        if (baseTemplateVo.getUserType() == FormIdTypeEnum.STU.getCode()) {
            PeStudent student = studentService.getById(baseTemplateVo.getUserId());
            openId = student.getOpenId();
            map.put("touser", openId);
        }
        int recode = 41028;
        while (recode == 41028 || recode == 41029) { //form_id不正确，重新寻找新的form_id,form_id已经被使用
            formId = getFormId(openId, FormIdTypeEnum.STU.getCode());
            if (formId == null) {
                break;
            }
            map.put("form_id", formId);
            JSONObject re = HttpUtil.httpsRequest(path, "POST", JSON.toJSONString(map));
            recode = re.getIntValue("errcode");
            if (recode == 41028 || recode == 41029) {
                formIdService.updateIsUse(openId, formId);
            }
        }
        if (recode == 0) {
            //发送成功，将使用过的form_id置为已使用
            formIdService.updateIsUse(openId, formId);
            return true;
        } else {
            return false;
        }
    }


    /**
     * 根据openId 查询formId
     *
     * @param openId
     * @return
     */
    private String getFormId(String openId, short type) {
        String formId = null;
        List<PeFormId> formIdList = formIdService.listByOpenId(openId, type);
        if (null != formIdList && formIdList.size() > 0) {
            formId = formIdList.get(0).getFormId();
        }
        return formId;
    }


    /**
     * 学生端的accessToken
     *
     * @return
     */
    public String getAccessTokenForTeach() {
        String appid =PropertiesConfigUtils.getProperty("wechat_appid");
        String secret =PropertiesConfigUtils.getProperty("wechat_secret");
        if (teachAccessTokenMap != null && teachAccessTokenMap.get("access_token") == null) {//第一次获取
            teachAccessTokenMap.put("access_token", getAccessTokenFromServer(appid, secret));
            teachAccessTokenMap.put("create_time", new Date().getTime() + "");
            return teachAccessTokenMap.get("access_token");
        } else {//已持有
            if (new Date().getTime() > Long.parseLong(teachAccessTokenMap.get("create_time")) + 90 * 1000) {//过期更新
                String accessToken = getAccessTokenFromServer(appid, secret);
                if (!teachAccessTokenMap.get("access_token").equals(accessToken)) {
                    teachAccessTokenMap.put("access_token", accessToken);
                    teachAccessTokenMap.put("create_time", new Date().getTime() + "");
                }
            }
            return teachAccessTokenMap.get("access_token");
        }
    }

    /**
     * 学生端的accessToken
     *
     * @return
     */
    public String getAccessTokenForStu() {
        String appid = PropertiesConfigUtils.getProperty("wechat_stu_appid");
        String secret = PropertiesConfigUtils.getProperty("wechat_stu_secret");
        if (stuAccessTokenMap != null && stuAccessTokenMap.get("access_token") == null) {//第一次获取
            stuAccessTokenMap.put("access_token", getAccessTokenFromServer(appid, secret));
            stuAccessTokenMap.put("create_time", new Date().getTime() + "");
            return stuAccessTokenMap.get("access_token");
        } else {//已持有
            if (new Date().getTime() > Long.parseLong(stuAccessTokenMap.get("create_time")) + 90 * 1000) {//过期更新
                String accessToken = getAccessTokenFromServer(appid, secret);
                if (!stuAccessTokenMap.get("access_token").equals(accessToken)) {
                    stuAccessTokenMap.put("access_token", accessToken);
                    stuAccessTokenMap.put("create_time", new Date().getTime() + "");
                }
            }
            return stuAccessTokenMap.get("access_token");
        }
    }

    /**
     * 重服务端获取数据
     *
     * @param appid
     * @param secret
     * @return
     */
    public String getAccessTokenFromServer(String appid, String secret) {
        String path = API_ACCESS_TOKEN.replace("APPID", appid);
        path = path.replace("SECRET", secret);
        JSONObject re = HttpUtil.httpsRequest(path, "GET", "");
        int recode = re.getIntValue("errcode");
        if (recode == 0) {
            String accessToken = re.getString("access_token");
            return accessToken;
        } else {
            throw new ClubException(SystemErrorCode.GET_ACCESS_TOKEN_ERROR);
        }
    }
}
