package com.koolearn.club.web.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.koolearn.club.dto.award.AwardEditReqDTO;
import com.koolearn.club.dto.award.AwardListReqDTO;
import com.koolearn.club.dto.award.AwardListRespDTO;
import com.koolearn.club.dto.award.AwardReqDTO;
import com.koolearn.club.service.IDrawAwardService;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.club.web.webapp.aop.Ignore;
import com.koolearn.club.web.webapp.aop.Logging;
import com.koolearn.club.web.webapp.constants.SysErrorCodeEnum;
import com.koolearn.club.web.webapp.util.ValidUtil;
import com.koolearn.club.web.webapp.vo.award.AwardEditReqVo;
import com.koolearn.club.web.webapp.vo.award.AwardListReqVo;
import com.koolearn.club.web.webapp.vo.award.AwardListRespVo;
import com.koolearn.club.web.webapp.vo.award.AwardReqVo;
import com.koolearn.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Api(value = "award", description = "奖品管理")
@RequestMapping(value = "/v1/award")
@RestController
public class AwardController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(AwardController.class);

    @Reference(application = "${dubbo.application.name}", registry = "${dubbo.registry.address}")
    private IDrawAwardService drawAwardService;

    @ApiOperation(value = "新增奖品", httpMethod = "POST", notes = "新增奖品")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @Logging
    public JSONResult addAward(@ApiParam("新建活动对象") @Valid @RequestBody AwardReqVo awardReqVo,
                               @Ignore BindingResult result, @Ignore HttpServletRequest request) {
        log.info("新建奖品" + awardReqVo.toString());
        if (result.hasErrors()) {
            return ValidUtil.validateParam(result);
        }
        AwardReqDTO awardReqDTO = new AwardReqDTO();
        awardReqVo.setTeachId(getTeachId(request));
        BeanUtils.copyProperties(awardReqDTO, awardReqVo);
        JSONResult JSONResult = drawAwardService.addAward(awardReqDTO);
        if (JSONResult.getCode() == JSONResult.SUCCESS) {
            return new JSONResult().success(null);
        } else {
            return new JSONResult().fail(SysErrorCodeEnum.ERR_REST_FAIL.getCode(), SysErrorCodeEnum.ERR_REST_FAIL.getMessage());
        }
    }


    @ApiOperation(value = "奖品列表", httpMethod = "POST", notes = "奖品列表")
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @Logging
    public JSONPageResult<List<AwardListRespVo>> activityList(@ApiParam("新建活动对象") @Valid @RequestBody AwardListReqVo awardListReqVo,
                                                              @Ignore BindingResult result,  @Ignore HttpServletRequest request) {
        log.info("奖品列表" + awardListReqVo.toString());
        if (result.hasErrors()) {
            return ValidUtil.validatePageParam(result);
        }
        JSONPageResult<List<AwardListRespVo>> listJSONPageResult = new JSONPageResult<>();
        AwardListReqDTO awardListReqDTO = new AwardListReqDTO();
        awardListReqVo.setTeachId(getTeachId(request));
        BeanUtils.copyProperties(awardListReqDTO, awardListReqVo);
        awardListReqDTO.setOffset(awardListReqDTO.getOffset() * awardListReqDTO.getPageSize());
        JSONPageResult<List<AwardListRespDTO>> jsonPageResult = drawAwardService.getAwardListByTeach(awardListReqDTO);
        if (jsonPageResult.getCode() == JSONPageResult.SUCCESS) {
            List<AwardListRespVo> awardListRespVoList = new ArrayList<>();
            for (AwardListRespDTO awardListRespDTO : jsonPageResult.getData()) {
                AwardListRespVo awardListRespVo = new AwardListRespVo();
                BeanUtils.copyProperties(awardListRespVo, awardListRespDTO);
                awardListRespVoList.add(awardListRespVo);
            }
            listJSONPageResult.success(awardListRespVoList);
            listJSONPageResult.setPages(jsonPageResult.getPages());
            listJSONPageResult.setPageNum(jsonPageResult.getPageNum());
            listJSONPageResult.setPageSize(jsonPageResult.getPageSize());
            listJSONPageResult.setTotal(jsonPageResult.getTotal());
        } else {
            listJSONPageResult.fail(jsonPageResult.getCode(), jsonPageResult.getMsg());
        }
        return listJSONPageResult;
    }



    @ApiOperation(value = "下拉列表奖品列表", httpMethod = "POST", notes = "下拉列表奖品列表")
    @PostMapping(value = "/simple_list", produces = MediaType.APPLICATION_JSON_VALUE)
    @Logging
    public JSONResult<List<AwardListRespVo>> simpleActivityList( @Ignore HttpServletRequest request) {
        JSONResult<List<AwardListRespVo>> listJSONResult = new JSONResult<>();
        JSONResult<List<AwardListRespDTO>> jsonResult = drawAwardService.simpleActivityList(getTeachId(request));
        if (jsonResult.getCode() == JSONPageResult.SUCCESS) {
            List<AwardListRespVo> awardListRespVoList = new ArrayList<>();
            for (AwardListRespDTO awardListRespDTO : jsonResult.getData()) {
                AwardListRespVo awardListRespVo = new AwardListRespVo();
                BeanUtils.copyProperties(awardListRespVo, awardListRespDTO);
                awardListRespVoList.add(awardListRespVo);
            }
            listJSONResult.success(awardListRespVoList);
        } else {
            listJSONResult.fail(jsonResult.getCode(), jsonResult.getMsg());
        }
        return listJSONResult;
    }

    @ApiOperation(value = "编辑奖品", httpMethod = "POST", notes = "编辑奖品")
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @Logging
    public JSONResult updateAward(@ApiParam("编辑奖品请求对象") @Valid @RequestBody AwardEditReqVo awardEditReqVo,
                                  @Ignore BindingResult result) {
        log.info("编辑奖品" + awardEditReqVo.toString());
        if (result.hasErrors()) {
            return ValidUtil.validateParam(result);
        }
        AwardEditReqDTO awardEditReqDTO = new AwardEditReqDTO();
        BeanUtils.copyProperties(awardEditReqDTO, awardEditReqVo);
        JSONResult JSONResult = drawAwardService.editAward(awardEditReqDTO);
        if (JSONResult.getCode() == JSONResult.SUCCESS) {
            return new JSONResult().success(JSONResult.getData());
        } else {
            return new JSONResult().fail(SysErrorCodeEnum.ERR_REST_FAIL.getCode(), SysErrorCodeEnum.ERR_REST_FAIL.getMessage());
        }
    }


}
