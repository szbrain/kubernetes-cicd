package com.koolearn.club.web.webapp.aop;

/**
 * Created by lilong on 8/19/17.
 */

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logging {
    String description() default "";
}
