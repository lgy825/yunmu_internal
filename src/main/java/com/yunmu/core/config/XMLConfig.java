package com.yunmu.core.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations= {"classpath:spring-shiro.xml"})
public class XMLConfig {
}
