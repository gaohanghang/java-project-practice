package com.gaohanghang.springbootgephi.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求配置
 * 解决No 'Access-Control-Allow-Origin' header is present on the requested resource.
 */

@SpringBootConfiguration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}