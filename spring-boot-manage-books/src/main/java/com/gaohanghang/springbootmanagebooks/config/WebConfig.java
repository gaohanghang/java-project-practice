package com.gaohanghang.springbootmanagebooks.config;

import org.apache.ibatis.io.ResolverUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 12:28
 */
@EnableWebMvc // 是使用Java 注解快捷配置Spring Webmvc的一个注解
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/managebooks/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);// Specify the order to use for the HandlerMapping used to map view controllers relative to other handler mappings configured in Spring MVC.
        // 指定用于HandlerMapping的顺序，该HandlerMapping用于映射相对于在Spring MVC中配置的其他处理程序映射的视图控制器。
    }
}
