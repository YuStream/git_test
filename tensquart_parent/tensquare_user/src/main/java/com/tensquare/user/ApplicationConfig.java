package com.tensquare.user;

import com.tensquare.user.filter.FilterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private FilterUtils filterUtils;

    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(filterUtils)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
