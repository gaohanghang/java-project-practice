package com.gaohanghang.mail.common.interceptor;

import org.hibernate.criterion.Order;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/30 18:27
 */
public class MyAdapter extends WebMvcConfigurerAdapter {
    public void addViewController(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/login.shtml" );
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers( registry );
    }
}
