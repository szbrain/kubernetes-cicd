package com.kubernetes.cicd.webapp.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                //过滤默认错误api
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo());
        return docket;
    }

    //常用的细节
    //过滤指定的action
    //添加授权token列
    //添加上传文件列
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("jib测试接口")
                .description("jib打包镜像demo")
                //版本
                .version("0.0.1")
                .contact(new Contact("lvyangjun", "", "lv.yangjun@163.com"))
                .build();
    }
}
