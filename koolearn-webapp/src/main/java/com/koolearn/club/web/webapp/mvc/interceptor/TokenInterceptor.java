package com.koolearn.club.web.webapp.mvc.interceptor;

import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.exception.ClubException;
import com.koolearn.club.web.webapp.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  token
 */
public class TokenInterceptor implements HandlerInterceptor {

    private final String API_DOCS_URL = "/api-docs";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        if(httpServletRequest.getRequestURI().contains(API_DOCS_URL)){
            return true;
        }
        String token = httpServletRequest.getHeader("Authorization");
        if(StringUtils.isNoneBlank(token)){
            Integer teachId = JWTUtil.unsign(token,Integer.class);
            if(null == teachId){
                throw new ClubException(SystemErrorCode.TOKEN_INVALID);
            }else{
                httpServletRequest.setAttribute("teachId", teachId);
            }
        }else{
            throw new ClubException(SystemErrorCode.TOKEN_IS_NULL);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
