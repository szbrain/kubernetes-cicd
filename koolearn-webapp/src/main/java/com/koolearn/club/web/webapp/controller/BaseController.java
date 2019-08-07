package com.koolearn.club.web.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lilong01 on 2018/2/9.
 */
public abstract class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    public Integer getTeachId(HttpServletRequest request){
        Integer teachId = request.getAttribute("teachId") == null ? 38 : Integer.parseInt(request.getAttribute("teachId").toString());
        return teachId;
    }

}
