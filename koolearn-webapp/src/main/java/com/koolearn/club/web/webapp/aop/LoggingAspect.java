package com.koolearn.club.web.webapp.aop;

import com.alibaba.fastjson.JSON;
import com.koolearn.club.constants.SystemErrorCode;
import com.koolearn.club.exception.ClubException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by lilong on 8/19/17.
 */
@Aspect
@Order(5)
@Component
public class LoggingAspect {

    @Pointcut("@annotation(com.koolearn.club.web.webapp.aop.Logging)")
    private void cut(){}


    @Around("cut()")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuffer stringBuffer = new StringBuffer();
        long startTime = System.currentTimeMillis();
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Method targetMethod = getMethod(joinPoint);
        Object object = null;

        stringBuffer.append("请求方法名称:" + targetMethod.getName() + ",");
        Object[] args = filter(joinPoint.getArgs(),targetMethod);
        if(args.length > 0){
            int index = 0;
            for(Object arg : args){
                stringBuffer.append("请求参数"+ (index + 1) +":" + JSON.toJSON(arg) + ",");
                index ++;
            }
        }
        try {
            object = joinPoint.proceed();
        }catch (ClubException e){
            throw e;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            throw  new ClubException(SystemErrorCode.FAIL);
        }
        long endTime = System.currentTimeMillis();
        if(null != object){
            stringBuffer.append("返回数据:" + JSON.toJSON(object) + ",");
        }
        stringBuffer.append("请求耗时:" + (endTime - startTime) + "耗秒.");
        Logger logger = Logger.getLogger(targetClass);
        logger.info(stringBuffer.toString());
        return object;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if(!(signature instanceof MethodSignature)){
            throw new IllegalArgumentException("该注解只能用在方法上");
        }
        MethodSignature methodSignature = (MethodSignature)signature;
        return methodSignature.getMethod();
    }

    private Object[] filter(Object[] args, Method targetMethod) {
        Annotation[][] annotationList = targetMethod.getParameterAnnotations();
        Object[] values = new Object[args.length];
        for (int i = 0; i < annotationList.length; i++) {
            Ignore ignore = null;
            for (int j = 0; j < annotationList[i].length; j++) {
                if (annotationList[i][j].annotationType() == Ignore.class) {
                    ignore = (Ignore) annotationList[i][j];
                    break;
                }
            }
            if (ignore != null) {
                values[i] = ignore.replacer();
            } else {
                values[i] = args[i];
            }
        }
        return values;
    }

}
