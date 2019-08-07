package com.koolearn.club.web.webapp.config;


import com.koolearn.club.web.webapp.mvc.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by lvyangjun on 2018/5/8.
 */

//@Configuration
public class TokenConfig extends WebMvcConfigurerAdapter {


    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getTokenInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");

        // 拦截配置
        addInterceptor.addPathPatterns("/v1/activity/*");
        addInterceptor.addPathPatterns("/v1/award/*");
        addInterceptor.addPathPatterns("/v1/award-record/list");
        addInterceptor.addPathPatterns("/v1/class/*");
        addInterceptor.addPathPatterns("/v1/user/*");
    }
}