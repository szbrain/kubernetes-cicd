package com.koolearn.club.web.webapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.koolearn.club.constants.commons.ParamKeys;
import com.koolearn.club.service.ITeachTokenService;
import com.koolearn.club.utils.JSONResult;
import com.koolearn.club.web.webapp.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lilong01 on 2018/2/9.
 */
@Api(value = "token", description = "Token管理")
@RequestMapping(value = "/v1/token", method = RequestMethod.POST)
@Controller
public class TokenController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(TokenController.class);
    private static final Integer TOKEN_MAX_AGE = 1000*60*60*24;

    @Reference(application = "${dubbo.application.name}", registry = "${dubbo.registry.address}")
    private ITeachTokenService teachTokenService;

    /**
     * 获取code
     *
     * @return
     */
    @ApiOperation(value = "获取code", httpMethod = "POST", notes = "获取code")
    @ResponseBody
    @RequestMapping(value = "/get-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult<String> getCode() {
        String code = teachTokenService.insertCode();
        return new JSONResult<String>().success(code);
    }

    /**
     * 检查是否登录
     * @param code
     * @return
     */
    @ApiOperation(value = "检查是否登录", httpMethod = "POST", notes = "检查是否登录")
    @ResponseBody
    @RequestMapping(value = "/check-login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult<Map> checkLogin(@ApiParam("code") @RequestParam(value = ParamKeys.CODE) final String code) {
        Map map = teachTokenService.checkLogin(code);
        if(null != map.get("teachId")){
            Integer teachId = Integer.parseInt(map.get("teachId").toString());
            String token = JWTUtil.sign(teachId, TOKEN_MAX_AGE); //生成Token
            map.remove("teachId");
            map.put("token",token);
        }
        return new JSONResult<Map>().success(map);
    }


    /**
     * 扫码登录
     * @param code
     * @return
     */
    @ApiOperation(value = "扫码登录", httpMethod = "POST", notes = "扫码登录")
    @ResponseBody
    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult<Integer> login(@ApiParam("code") @RequestParam(value = ParamKeys.CODE) final String code,
                                     @ApiParam("教师ID") @RequestParam(value = ParamKeys.TEACH_ID) final int teachId) {
        int id = teachTokenService.login(code,teachId);
        return new JSONResult<Integer>().success(id);
    }


    /**
     * 确认登录
     * @param id
     * @return
     */
    @ApiOperation(value = "确认登录", httpMethod = "POST", notes = "确认登录")
    @ResponseBody
    @RequestMapping(value = "/confirm-login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResult<Boolean> confirmLogin(@ApiParam("id") @RequestParam(value = ParamKeys.ID) final int id) {
        boolean flag = false;
        int count = teachTokenService.confirmLogin(id);
        if(count > 0){
            flag = true;
        }
        return new JSONResult<Boolean>().success(flag);
    }

}
