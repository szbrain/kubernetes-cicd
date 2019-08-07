package com.koolearn.club.web.webapp.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.koolearn.club.constants.ResourceTypeEnum;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.constants.commons.Constants;
import com.koolearn.club.constants.commons.ParamKeys;
import com.koolearn.club.constants.upload.ErrorCodeEnum;
import com.koolearn.club.exception.ClubException;
import com.koolearn.club.utils.ClubUtils;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.club.web.webapp.util.UploadUtil;
import com.koolearn.framework.common.utils.PropertiesConfigUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by lilong01 on 2018/2/9.
 */
@Api(value="common", description = "公用管理")
@RequestMapping(value = "/v1/common", method = RequestMethod.POST)
@Controller
public class CommonController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    /**
     * 附件上传腾讯云
     * @param file
     * @return
     */
    @ApiOperation(value = "附件上传腾讯云", httpMethod = "POST", notes = "附件上传腾讯云")
    @ResponseBody
    @RequestMapping(value = "/upload-cloud", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult<String> uploadToCloud(@ApiParam("附件类型，1图片2语音3视频") @RequestParam(value = ParamKeys.TYPE) short type,
                                            @ApiParam("附件") @RequestParam(value = ParamKeys.FILE) MultipartFile file) {
        String cloudImageHost = PropertiesConfigUtils.getProperty("cloud_image_host");
        String fileName = file.getOriginalFilename();
        String extName=fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = ClubUtils.uuid() + "." + extName.toLowerCase();
        InputStream inputStream;
        int length;
        try {
            inputStream = file.getInputStream();
            length = Integer.parseInt(String.valueOf(file.getSize()));
        } catch (IOException e) {
            throw new ClubException(SystemErrorCode.UPLOAD_FILE_ERROR);
        }
        String eTag = UploadUtil.uploadToQcloud(inputStream, fileName, length);
        if(StringUtils.isNoneBlank(eTag)){
            return new JSONResult<String>().success(cloudImageHost + "/" +fileName);
        }else{
            throw new ClubException(SystemErrorCode.UPLOAD_FILE_ERROR);
        }
    }


    /**
     * 附件上传
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "附件上传CDN", httpMethod = "POST", notes = "附件上传CDN")
    @ResponseBody
    @RequestMapping(value = "/v1/upload-cdn", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult<String> uploadToCdn(@ApiParam("附件类型，1-图片2-语音3-视频4-文档")
                                     @RequestParam(required = true, value = ParamKeys.TYPE) short type,
                                          @ApiParam("附件") @RequestParam(value = ParamKeys.FILE) MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String filePath;
        String cdnType;
        if (type == ResourceTypeEnum.IMAGE.getCode()) {
            cdnType = "picture";
            filePath = Constants.RESOURCE_PICTURE_PATH;
        } else if (type == ResourceTypeEnum.VIDEO.getCode() || type == ResourceTypeEnum.VOICE.getCode()) {
            filePath = Constants.RESOURCE_VIDEO_PATH;
            cdnType = "video";
        } else {
            cdnType = "doc";
            filePath = Constants.RESOURCE_DOC_PATH;
        }
        fileName = ClubUtils.uuid() + "." + extName.toLowerCase();
        Map<String, Object> params = Maps.newHashMap();
        params.put("type", cdnType);
        params.put("filePath", filePath);
        params.put("fileName", fileName);
        params.put("project", PropertiesConfigUtils.getProperty("cdn_project"));
        params.put("appkey", PropertiesConfigUtils.getProperty("cdn_appkey"));
        params.put("serverUrl", PropertiesConfigUtils.getProperty("cdn_upload_url"));
        String responseJson = UploadUtil.upload(file, params);
        Map responseMap = JSON.parseObject(responseJson, Map.class);
        String responseCode = responseMap.get("code") == null ? "" : responseMap.get("code").toString();
        if (responseCode.equals("200")) {
            return new JSONResult().success(responseMap.get("response").toString());
        } else {
            return new JSONResult().fail(ErrorCodeEnum.UPLOAD_CODE_SAVE_FAIL.getCode(),
                    ErrorCodeEnum.UPLOAD_CODE_SAVE_FAIL.getMessage());
        }
    }
}
