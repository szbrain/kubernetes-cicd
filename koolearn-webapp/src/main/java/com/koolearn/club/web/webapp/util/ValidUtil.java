package com.koolearn.club.web.webapp.util;

import com.koolearn.club.utils.JSONPageResult;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.club.web.webapp.constants.SysErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 验证Util
 */
public class ValidUtil {
    private static Logger logger = LoggerFactory.getLogger(ValidUtil.class);

    public static JSONResult validateParam(BindingResult result) {
        List<ObjectError> list = result.getAllErrors();
        int index=0;
        String message="";
        for (ObjectError error : list) {
            index++;
            if (index==1){
               message = error.getDefaultMessage();
            }
            logger.error("参数校验失败：{},错误信息：{}", error.getArguments(), error.getDefaultMessage());
        }
        return new JSONResult().fail(SysErrorCodeEnum.ERR_ILLEGAL_PARAM.getCode(), !"".equals(message)?message:SysErrorCodeEnum.ERR_ILLEGAL_PARAM.getMessage());
    }

    public static JSONPageResult validatePageParam(BindingResult result) {
        List<ObjectError> list = result.getAllErrors();
        int index=0;
        String message="";
        for (ObjectError error : list) {
            index++;
            if (index==1){
                message = error.getDefaultMessage();
            }
            logger.error("参数校验失败：{},错误信息：{}", error.getArguments(), error.getDefaultMessage());
        }
        return new JSONPageResult().fail(SysErrorCodeEnum.ERR_ILLEGAL_PARAM.getCode(), !"".equals(message)?message:SysErrorCodeEnum.ERR_ILLEGAL_PARAM.getMessage());
    }
}
