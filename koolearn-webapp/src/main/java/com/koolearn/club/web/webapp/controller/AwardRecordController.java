package com.koolearn.club.web.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.award.AwardRecordErrorCodeEnum;
import com.koolearn.club.dto.award.AwardRecordListDTO;
import com.koolearn.club.dto.award.AwardRecordListParamDTO;
import com.koolearn.club.dto.common.PageDTO;
import com.koolearn.club.exception.ClubException;
import com.koolearn.club.service.IAwardRecordService;
import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.club.web.webapp.poi.ExportUtils;
import com.koolearn.club.web.webapp.util.ValidUtil;
import com.koolearn.club.web.webapp.vo.awardrecord.AwardRecordListReqVo;
import com.koolearn.club.web.webapp.vo.awardrecord.AwardRecordListRespVo;
import com.koolearn.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */
@Api(value = "awardRecord", description = "中奖管理")
@RequestMapping(value = "/v1/award-record")
@Controller
public class AwardRecordController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(AwardRecordController.class);

    @Reference(application = "${dubbo.application.name}", registry = "${dubbo.registry.address}")
    private IAwardRecordService awardRecordService;



    @ApiOperation(value = "中奖名单列表", httpMethod = "POST", notes = "中奖名单列表")
    @ResponseBody
    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public JSONPageResult<List<AwardRecordListRespVo>> awardRecordList(@ApiParam("中奖名单列表查询对象") @Valid @RequestBody AwardRecordListReqVo awardRecordListReqVo,
                                                                       @Ignore BindingResult result) {

        if (result.hasErrors()) {
            return ValidUtil.validatePageParam(result);
        }
        AwardRecordListParamDTO awardRecordListParamDTO = new AwardRecordListParamDTO();
        BeanUtils.copyProperties(awardRecordListParamDTO, awardRecordListReqVo);
        awardRecordListParamDTO.setOffset(awardRecordListReqVo.getOffset() * awardRecordListReqVo.getPageSize());
        awardRecordListParamDTO.setPageSize(awardRecordListReqVo.getPageSize());
        PageDTO<AwardRecordListDTO> pageDTO = awardRecordService.listForWebapp(awardRecordListParamDTO);
        JSONPageResult jsonPageResult = new JSONPageResult(awardRecordListReqVo.getPageSize(), pageDTO.getCount(), awardRecordListReqVo.getOffset());
        List<AwardRecordListRespVo> awardRecordListRespVoList = new ArrayList<>();
        for (AwardRecordListDTO awardRecordListDTO : pageDTO.getList()) {
            AwardRecordListRespVo awardRecordListRespVo = new AwardRecordListRespVo();
            BeanUtils.copyProperties(awardRecordListRespVo, awardRecordListDTO);
            awardRecordListRespVoList.add(awardRecordListRespVo);
        }
        return jsonPageResult.success(awardRecordListRespVoList);
    }


    @ApiOperation(value = "中奖名单列表导出", httpMethod = "GET", notes = "中奖名单列表导出")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult awardRecordExport(@ApiParam("活动ID") @RequestParam() final int activityId,
                                        @ApiParam("昵称") @RequestParam(required = false) final String nickname,
                                        @ApiParam("联系方式") @RequestParam(required = false) final String mobile,
                                        @ApiParam("中奖时间开始") @RequestParam(required = false) final String startCreateTime,
                                        @ApiParam("中奖时间结束") @RequestParam(required = false) final String endCreateTime,
                                        @ApiParam("奖品名称") @RequestParam(required = false) final String awardName,
                                        @ApiParam("物流地址") @RequestParam(required = false) final String address,
                                        @Ignore HttpServletResponse response) {
        AwardRecordListParamDTO awardRecordListParamDTO = new AwardRecordListParamDTO();
        awardRecordListParamDTO.setActivityId(activityId);
        awardRecordListParamDTO.setNickname(nickname);
        awardRecordListParamDTO.setMobile(mobile);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            if(StringUtils.isNoneBlank(startCreateTime)){
                awardRecordListParamDTO.setStartCreateTime(format.parse(startCreateTime));
            }
            if(StringUtils.isNoneBlank(endCreateTime)){
                awardRecordListParamDTO.setEndCreateTime(format.parse(endCreateTime));
            }
        } catch (ParseException e) {
            throw new ClubException(SystemErrorCode.PARAM_ERROR);
        }
        awardRecordListParamDTO.setAwardName(awardName);
        awardRecordListParamDTO.setAddress(address);
        List<AwardRecordListDTO> awardRecordListRespDTOList = awardRecordService.listAllForWebapp(awardRecordListParamDTO);
        if(null != awardRecordListRespDTOList && awardRecordListRespDTOList.size() > 0){
            List<String> titleList = new ArrayList<>();
            titleList.add("中奖时间");
            titleList.add("用户昵称");
            titleList.add("真实姓名");
            titleList.add("联系方式");
            titleList.add("奖项");
            titleList.add("奖品");
            titleList.add("物流地址");
            titleList.add("备注");
            List<List<Object>> dataList = new ArrayList<>();
            for(int row = 0, dataSize = awardRecordListRespDTOList.size(); row < dataSize; row++){
                List<Object> temp = new ArrayList<>();
                AwardRecordListDTO awardRecordListDTO = awardRecordListRespDTOList.get(row);
                temp.add(format.format(awardRecordListDTO.getCreateTime()));
                temp.add(awardRecordListDTO.getNickname());
                temp.add(awardRecordListDTO.getName());
                temp.add(awardRecordListDTO.getMobile());
                temp.add(awardRecordListDTO.getAwardItemName());
                temp.add(awardRecordListDTO.getAwardName());
                temp.add(awardRecordListDTO.getAddress());
                temp.add(awardRecordListDTO.getRemarks());
                dataList.add(temp);
            }
            String now = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String exportFileName = "中奖名单_" + now + ".xlsx";
            String sheetName = "中奖名单";
            ExportUtils.export(exportFileName, sheetName, titleList, dataList, null, response);
        }else {
            return new JSONResult().fail(AwardRecordErrorCodeEnum.AWARD_RECORD_NOT_FOUND.getCode()
                    , AwardRecordErrorCodeEnum.AWARD_RECORD_NOT_FOUND.getMessage());
        }
        return new JSONResult().success(null);
    }
}
