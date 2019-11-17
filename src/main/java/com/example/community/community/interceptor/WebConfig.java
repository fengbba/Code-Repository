package com.example.community.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-16 16:26
 **/
@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要拦截的
        String[] addPathPatterns = {
                "/**",
                "/active/**"
        };

        // 不拦截的
        String[] excludePathPatterns = {

        };
        /*

            registry.addInterceptor(new LocaleInterceptor());
            对 /** 地址进行拦截 , 对 /admin/** 地址 略过
            registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
            registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
        */
        registry.addInterceptor(sessionInterceptor).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
