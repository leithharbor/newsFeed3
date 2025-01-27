package com.example.newsFeed.config;

import com.example.newsFeed.aop.Aspects;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public Aspects aspects () {
        return new Aspects();
    }
}
