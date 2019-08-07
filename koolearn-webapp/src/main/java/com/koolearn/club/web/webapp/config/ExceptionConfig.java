package com.koolearn.club.web.webapp.config;


import com.koolearn.club.web.webapp.mvc.resolver.ExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by lvyangjun on 2018/5/8.
 */

@Configuration
public class ExceptionConfig extends WebMvcConfigurerAdapter {


    @Bean
    public ExceptionResolver getExceptionResolver() {
        return new ExceptionResolver();
    }


}