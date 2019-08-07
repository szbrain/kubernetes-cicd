/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koolearn.club.ocr.op;

import com.koolearn.club.ocr.ClientConfig;
import com.koolearn.club.ocr.exception.AbstractImageException;
import com.koolearn.club.ocr.http.*;
import com.koolearn.club.ocr.request.GeneralOcrRequest;
import com.koolearn.club.ocr.sign.Credentials;
import com.koolearn.club.ocr.sign.Sign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;

import static com.koolearn.club.ocr.ClientConfig.OCR_GENERAL;


/**
 *
 * @author jusisli 此类封装了图片识别操作
 */
public class DetectionOp extends BaseOp {
    private static final Logger LOG = LoggerFactory.getLogger(DetectionOp.class);

    public DetectionOp(ClientConfig config, Credentials cred, AbstractImageHttpClient client) {
        super(config, cred, client);
    }

     /**
     * 通用OCR
     * 
     * @param request 标签识别请求参数
     * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功, 其他为失败,
     *         message为success或者失败原因
     * @throws AbstractImageException SDK定义的Image异常, 通常是输入参数有误或者环境问题(如网络不通)
     */  
    public String generalOcr(GeneralOcrRequest request) throws AbstractImageException {
        request.check_param();
        String sign = Sign.appSign(cred, request.getBucketName(), this.config.getSignExpired());
        String url = "http://" + this.config.getQCloudOcrDomain()+ OCR_GENERAL;
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setMethod(HttpMethod.POST);
        httpRequest.setUrl(url);
        httpRequest.addHeader(RequestHeaderKey.Authorization, sign);
        httpRequest.addParam(RequestBodyKey.APPID, String.valueOf(cred.getAppId()));
        httpRequest.addParam(RequestBodyKey.BUCKET, request.getBucketName());
        if (request.isUrl()) {
            httpRequest.setContentType(HttpContentType.APPLICATION_JSON);
            httpRequest.addHeader(RequestHeaderKey.Content_TYPE, String.valueOf(HttpContentType.APPLICATION_JSON));
            httpRequest.addParam(RequestBodyKey.URL, request.getUrl());
        } else {
            httpRequest.setContentType(HttpContentType.MULTIPART_FORM_DATA);
            httpRequest.addFile("image",request.getImage());
        }
        return httpClient.sendHttpRequest(httpRequest);

    }

    
    
}
