package com.koolearn.club.web.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.koolearn.club.constants.teach.TeachErrorCodeEnum;
import com.koolearn.club.entity.PeTeacher;
import com.koolearn.club.service.ITeacherService;
import com.koolearn.club.utils.JSONResult;

import com.koolearn.club.web.webapp.vo.teach.TeacherVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Api(value = "class", description = "用户信息")
@RequestMapping(value = "/v1/user", method = RequestMethod.POST)
@Controller
public class UserInfoController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Reference(application = "${dubbo.application.name}", registry = "${dubbo.registry.address}")
    private ITeacherService teacherService;

    @ApiOperation(value = "老师信息", httpMethod = "POST", notes = "老师信息")
    @ResponseBody
    @RequestMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Logging
    public JSONResult<TeacherVo> info(HttpServletRequest request) {

        PeTeacher teacher = teacherService.getById(getTeachId(request));
        if (teacher != null) {
            TeacherVo teacherVo = new TeacherVo();
            teacherVo.setAvatar(teacher.getAvatar());
            teacherVo.setNickname(teacher.getNickname());
            return new JSONResult<TeacherVo>().success(teacherVo);
        } else {
            return new JSONResult().fail(TeachErrorCodeEnum.TEACH_NOT_FOUND.getCode(), TeachErrorCodeEnum.TEACH_NOT_FOUND.getMessage());
        }
    }
}
