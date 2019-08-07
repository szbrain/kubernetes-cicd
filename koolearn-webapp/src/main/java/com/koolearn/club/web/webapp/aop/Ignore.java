package com.koolearn.club.web.webapp.aop;

/**
 * Created by lilong on 8/19/17.
 */

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Ignore {
    /**
     * 此字段替换成为什么字符.
     *
     * @return
     */
    String replacer() default "*IG*";
}
