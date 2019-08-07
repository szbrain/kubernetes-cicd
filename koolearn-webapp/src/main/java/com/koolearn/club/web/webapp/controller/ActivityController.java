package com.koolearn.club.web.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.koolearn.club.constants.ActivityStatusEnum;
import com.koolearn.club.constants.ActivityTemplateEnum;
import com.koolearn.club.constants.ActivityTriggerRuleEnum;
import com.koolearn.club.constants.ActivityTypeEnum;
import com.koolearn.club.dto.activity.*;
import com.koolearn.club.dto.award.DrawAwardItemReqDTO;
import com.koolearn.club.dto.award.DrawAwardItemRespDTO;
import com.koolearn.club.dto.award.RuleDTO;
import com.koolearn.club.service.IActivityService;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.club.web.webapp.aop.Ignore;
import com.koolearn.club.web.webapp.aop.Logging;
import com.koolearn.club.web.webapp.constants.SysErrorCodeEnum;
import com.koolearn.club.web.webapp.util.ValidUtil;
import com.koolearn.club.web.webapp.vo.activity.*;
import com.koolearn.club.web.webapp.vo.award.DeleteAwardItemReqVo;
import com.koolearn.club.web.webapp.vo.award.DrawAwardItemReqVo;
import com.koolearn.club.web.webapp.vo.award.RuleVo;
import com.koolearn.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Api(value = "activity", description = "活动管理")
@RequestMapping(value = "/v1/activity", method = RequestMethod.POST)
@RestController
public class ActivityController  {

    private static final Logger log = LoggerFactory.getLogger(ActivityController.class);

    @Reference(application = "${dubbo.application.name}", registry = "${dubbo.registry.address}")
    private IActivityService activityService;

    /*@ApiOperation(value = "新建活动", httpMethod = "POST", notes = "新建活动")
    @ResponseBody
    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @Logging
    public JSONResult addActivity(@ApiParam("新建活动对象") @Valid @RequestBody ActivityReqVo activityReqVo,
                                  BindingResult result) {
        log.info("新建活动" + activityReqVo.toString());
        if (result.hasErrors()) {
            return ValidUtil.validateParam(result);
        }
        ActivityReqDTO activityReqDTO = new ActivityReqDTO();
        BeanUtils.copyProperties(activityReqDTO, activityReqVo);
        RuleDTO ruleDTO = new RuleDTO();
        BeanUtils.copyProperties(ruleDTO, activityReqVo.getRuleVo());
        activityReqDTO.setRuleDTO(ruleDTO);
        List<DrawAwardItemReqVo> drawAwardItemReqVoList = activityReqVo.getDrawAwardItemReqVoList();
        List<DrawAwardItemReqDTO> drawAwardItemReqDTOList = new ArrayList<>();
        if (drawAwardItemReqVoList.size() > 0) {
            for (DrawAwardItemReqVo drawAwardItemReqVo : drawAwardItemReqVoList) {
                DrawAwardItemReqDTO drawAwardItemReqDTO = new DrawAwardItemReqDTO();
                BeanUtils.copyProperties(drawAwardItemReqDTO, drawAwardItemReqVo);
                drawAwardItemReqDTOList.add(drawAwardItemReqDTO);
            }
        }
        activityReqDTO.setDrawAwardItemReqDTOList(drawAwardItemReqDTOList);
        JSONResult JSONResult = activityService.addActivity(activityReqDTO);
        if (JSONResult.getCode() == JSONResult.SUCCESS) {
            return new JSONResult().success(null);
        } else {
            return new JSONResult().fail(SysErrorCodeEnum.ERR_REST_FAIL.getCode(), SysErrorCodeEnum.ERR_REST_FAIL.getMessage());
        }
    }*/


    @ApiOperation(value = "开始活动", httpMethod = "POST", notes = "开始活动")
    @ResponseBody
    @RequestMapping(value = "/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult startActivity(@ApiParam("活动ID") @RequestParam(required = true) final Integer activityId) {
        log.info("开始活动" + activityId);
        int count = activityService.startActivity(activityId);
        if(count > 0){
            return new JSONResult().success(null);
        }else{
            return new JSONResult().fail(SysErrorCodeEnum.ERR_REST_FAIL.getCode(), SysErrorCodeEnum.ERR_REST_FAIL.getMessage());
        }
    }

    @ApiOperation(value = "结束活动", httpMethod = "POST", notes = "结束活动")
    @ResponseBody
    @RequestMapping(value = "/finish", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult finishActivity(@ApiParam("活动ID") @RequestParam(required = true) final Integer activityId) {
        log.info("结束活动" + activityId);
        int count = activityService.finishActivity(activityId);
        if(count > 0){
            return new JSONResult().success(null);
        }else{
            return new JSONResult().fail(SysErrorCodeEnum.ERR_REST_FAIL.getCode(), SysErrorCodeEnum.ERR_REST_FAIL.getMessage());
        }
    }


    @ApiOperation(value = "活动列表", httpMethod = "POST", notes = "活动列表")
    @ResponseBody
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONPageResult<List<ActivityListRespVo>> activityList(@ApiParam("新建活动对象") @Valid @RequestBody ActivityListReqVo activityListReqVo,
                                                                 BindingResult result) {
        log.info("新建活动" + activityListReqVo.toString());
        if (result.hasErrors()) {
            return ValidUtil.validatePageParam(result);
        }
        JSONPageResult<List<ActivityListRespVo>> listJSONPageResult = new JSONPageResult<>();
        ActivityListReqDTO activityListReqDTO = new ActivityListReqDTO();
        BeanUtils.copyProperties(activityListReqDTO, activityListReqVo);
        activityListReqDTO.setOffset(activityListReqDTO.getOffset() * activityListReqDTO.getPageSize());
        JSONPageResult<List<ActivityRespDTO>> jsonPageResult = activityService.activityList(activityListReqDTO);
        if (jsonPageResult.getCode() == JSONPageResult.SUCCESS) {
            List<ActivityListRespVo> activityRespVoList = new ArrayList<>();
            for (ActivityRespDTO activityRespDTO : jsonPageResult.getData()) {
                ActivityListRespVo activityRespVo = new ActivityListRespVo();
                BeanUtils.copyProperties(activityRespVo, activityRespDTO);
                activityRespVo.setStatus(ActivityStatusEnum.getNameByCode(activityRespDTO.getStatus()));
                activityRespVo.setType(ActivityTypeEnum.getNameByCode(activityRespDTO.getType()));
                activityRespVoList.add(activityRespVo);
            }
            listJSONPageResult.success(activityRespVoList);
            listJSONPageResult.setPages(jsonPageResult.getPages());
            listJSONPageResult.setPageNum(jsonPageResult.getPageNum());
            listJSONPageResult.setPageSize(jsonPageResult.getPageSize());
            listJSONPageResult.setTotal(jsonPageResult.getTotal());
        } else {
            listJSONPageResult.fail(jsonPageResult.getCode(), jsonPageResult.getMsg());
        }
        return listJSONPageResult;
    }

    @ApiOperation(value = "活动详情", httpMethod = "POST", notes = "活动详情")
    @ResponseBody
    @RequestMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    @Logging
    public JSONResult<ActivityDetailRespVo> activityDetail(@ApiParam("活动详情对象") @Valid @RequestBody ActivityDetailReqVo activityDetailReqVo,
                                                           @Ignore BindingResult result) {
        log.info("新建活动" + activityDetailReqVo.toString());
        if (result.hasErrors()) {
            return ValidUtil.validateParam(result);
        }
        JSONResult<ActivityDetailRespVo> jsonResult = new JSONResult<>();
        ActivityDetailReqDTO activityDetailReqDTO = new ActivityDetailReqDTO();
        BeanUtils.copyProperties(activityDetailReqDTO, activityDetailReqVo);
        JSONResult<ActivityDetailRespDTO> jsonResult1 = activityService.activityDetail(activityDetailReqDTO);
        if (jsonResult1.getCode() == JSONPageResult.SUCCESS) {
            ActivityDetailRespVo activityDetailRespVo = new ActivityDetailRespVo();
            ActivityDetailRespDTO activityDetailRespDTO = jsonResult1.getData();
            BeanUtils.copyProperties(activityDetailRespVo, activityDetailRespDTO);
            activityDetailRespVo.setStatus(ActivityStatusEnum.getNameByCode(activityDetailRespDTO.getStatus()));
            activityDetailRespVo.setType(ActivityTypeEnum.getNameByCode(activityDetailRespDTO.getType()));
            activityDetailRespVo.setTriggerRule(ActivityTriggerRuleEnum.getNameByCode(activityDetailRespDTO.getTriggerRule()));
            activityDetailRespVo.setActivityTemplate(ActivityTemplateEnum.getNameByCode(new Short(activityDetailRespDTO.getActivityTemplate())));
            RuleVo ruleVo = new RuleVo();
            RuleDTO ruleDTO = activityDetailRespDTO.getRuleDTO();
            BeanUtils.copyProperties(ruleVo, ruleDTO);
            activityDetailRespVo.setRuleVO(ruleVo);
            List<DrawAwardItemReqVo> drawAwardItemReqVoList = new ArrayList<>();
            List<DrawAwardItemRespDTO> drawAwardItemRespDTOList = activityDetailRespDTO.getDrawAwardItemRespDTOList();
            if (drawAwardItemRespDTOList.size() > 0) {
                for (DrawAwardItemRespDTO drawAwardItemRespDTO : drawAwardItemRespDTOList) {
                    DrawAwardItemReqVo drawAwardItemReqVo = new DrawAwardItemReqVo();
                    BeanUtils.copyProperties(drawAwardItemReqVo, drawAwardItemRespDTO);
                    drawAwardItemReqVo.setAwardName(drawAwardItemRespDTO.getAwardRespDTO().getAwardName());
                    drawAwardItemReqVoList.add(drawAwardItemReqVo);
                }
            }
            activityDetailRespVo.setAwardItemReqVoList(drawAwardItemReqVoList);
            jsonResult.success(activityDetailRespVo);
        } else {
            jsonResult.fail(jsonResult1.getCode(), jsonResult1.getMsg());
        }
        return jsonResult;
    }


    @ApiOperation(value = "更新活动", httpMethod = "POST", notes = "更新活动")
    @ResponseBody
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult editActivity(@ApiParam("新建活动对象") @Valid @RequestBody ActivityEditReqVo activityEditReqVo,
                                   BindingResult result) {
        log.info("新建活动" + activityEditReqVo.toString());
        if (result.hasErrors()) {
            return ValidUtil.validateParam(result);
        }
        ActivityEditReqDTO activityEditReqDTO = new ActivityEditReqDTO();
        BeanUtils.copyProperties(activityEditReqDTO, activityEditReqVo);
        if (activityEditReqVo.getRuleVo() != null) {
            RuleDTO ruleDTO = new RuleDTO();
            BeanUtils.copyProperties(ruleDTO, activityEditReqVo.getRuleVo());
            activityEditReqDTO.setRuleDTO(ruleDTO);
        }
        if (activityEditReqVo.getDrawAwardItemReqVoList() != null && activityEditReqVo.getDrawAwardItemReqVoList().size() > 0) {
            List<DrawAwardItemReqVo> drawAwardItemReqVoList = activityEditReqVo.getDrawAwardItemReqVoList();
            List<DrawAwardItemReqDTO> drawAwardItemReqDTOList = new ArrayList<>();
            if (drawAwardItemReqVoList.size() > 0) {
                for (DrawAwardItemReqVo drawAwardItemReqVo : drawAwardItemReqVoList) {
                    DrawAwardItemReqDTO drawAwardItemReqDTO = new DrawAwardItemReqDTO();
                    BeanUtils.copyProperties(drawAwardItemReqDTO, drawAwardItemReqVo);
                    drawAwardItemReqDTOList.add(drawAwardItemReqDTO);
                }
            }
            activityEditReqDTO.setDrawAwardItemReqDTOList(drawAwardItemReqDTOList);
        }
        JSONResult JSONResult = activityService.editActivity(activityEditReqDTO);
        if (JSONResult.getCode() == JSONResult.SUCCESS) {
            return new JSONResult().success(null);
        } else {
            return new JSONResult().fail(SysErrorCodeEnum.ERR_REST_FAIL.getCode(), SysErrorCodeEnum.ERR_REST_FAIL.getMessage());
        }
    }

    @ApiOperation(value = "删除活动奖项", httpMethod = "POST", notes = "删除活动奖项")
    @ResponseBody
    @RequestMapping(value = "/delete_award_item", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult deleteAwardItem(@ApiParam("删除活动奖项对象") @Valid @RequestBody DeleteAwardItemReqVo deleteAwardItemReqVo,
                                      BindingResult result) {
        log.info("新建活动" + deleteAwardItemReqVo.toString());
        if (result.hasErrors()) {
            return ValidUtil.validateParam(result);
        }
        JSONResult jsonResult = activityService.deleteAwardItem(deleteAwardItemReqVo.getId());
        if (jsonResult.getCode() == JSONResult.SUCCESS) {
            return new JSONResult().success(null);
        } else {
            return new JSONResult().fail(jsonResult.getCode(), jsonResult.getMsg());
        }
    }


}
