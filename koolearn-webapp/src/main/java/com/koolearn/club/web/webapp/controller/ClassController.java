package com.koolearn.club.web.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.koolearn.club.dto.classroom.manage.ClassListDTO;
import com.koolearn.club.dto.classroom.manage.ClassListParamDTO;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.service.IClassService;
import com.koolearn.club.utils.JSONPageResult;

import com.koolearn.club.web.webapp.util.ValidUtil;
import com.koolearn.club.web.webapp.vo.classroom.ClassListReqVo;
import com.koolearn.club.web.webapp.vo.classroom.ClassListRespVo;
import com.koolearn.util.BeanUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Api(value = "class", description = "班级管理")
@RequestMapping(value = "/v1/class", method = RequestMethod.POST)
@Controller
public class ClassController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(ClassController.class);

    @Reference(application = "${dubbo.application.name}", registry = "${dubbo.registry.address}")
    private IClassService classService;

    @ApiOperation(value = "查询班级列表", httpMethod = "POST", notes = "查询班级列表")
    @ResponseBody
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Logging
    public JSONPageResult<ClassListRespVo> list(@ApiParam("班级列表查询对象")  @Valid @RequestBody ClassListReqVo classListReqVo,
                                                @Ignore BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) {
            return ValidUtil.validatePageParam(result);
        }
        ClassListParamDTO classListParamDTO = new ClassListParamDTO();
        classListReqVo.setTeachId(getTeachId(request));
        BeanUtils.copyProperties(classListParamDTO, classListReqVo);
        PageDTO<ClassListDTO> pageDTO = classService.listClassForManage(classListParamDTO);
        JSONPageResult jsonPageResult = new JSONPageResult(classListReqVo.getPageSize(), pageDTO.getCount(), classListReqVo.getOffset());
        List<ClassListRespVo> classListRespVoList = Lists.newArrayList();
        List<ClassListDTO> listDTOList = pageDTO.getList();
        for(ClassListDTO classListDTO : listDTOList){
            ClassListRespVo classListRespVo = ClassListRespVo.getInstance(classListDTO);
            classListRespVoList.add(classListRespVo);
        }
        return jsonPageResult.success(classListRespVoList);
    }

}
