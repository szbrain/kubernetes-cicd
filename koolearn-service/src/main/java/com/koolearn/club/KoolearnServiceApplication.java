package com.koolearn.club;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KoolearnServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoolearnServiceApplication.class, args);
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
