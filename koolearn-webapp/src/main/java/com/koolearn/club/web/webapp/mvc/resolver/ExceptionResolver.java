package com.koolearn.club.web.webapp.mvc.resolver;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.google.common.collect.Maps;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.exception.ClubException;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by lilong01 on 2018/2/11.
 */
public class ExceptionResolver extends HandlerExceptionResolverComposite {
    private static final Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

    private static final ResourceBundle RESOURCEBUNDLE = ResourceBundle.getBundle("ApplicationResources");

    /**
     * 统一记录错误日志
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = Maps.newConcurrentMap();
        if (!(ex instanceof ClubException)) {
            log.error("System Inter Error", ex);
            attributes.put("code", SystemErrorCode.FAIL);
            attributes.put("message",RESOURCEBUNDLE.getString(String.valueOf(SystemErrorCode.FAIL)));

            String errorInfo = ex.getMessage();
            if (StringUtils.isBlank(errorInfo)) {
                //没有异常信息，取堆栈
                ByteArrayOutputStream exceptionStream = new ByteArrayOutputStream();
                try {
                    ex.printStackTrace(new PrintStream(exceptionStream, true, Charsets.UTF_8.toString()));
                    errorInfo = exceptionStream.toString(Charsets.UTF_8.toString());
                    exceptionStream.close();
                } catch (IOException ioe) {
                    log.debug("IOException:[{}] ,ErrorInfo:[{}]", ioe);
                }
            }
            log.debug("IOException:[{}] ,ErrorInfo:[{}]", errorInfo);
            attributes.put("additional-error-message", errorInfo);

        } else {
            ClubException ctex = (ClubException) ex;
            attributes.put("code", ctex.getErrorCode());
            attributes.put("message",RESOURCEBUNDLE.getString(String.valueOf(ctex.getErrorCode())));
        }
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}
