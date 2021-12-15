package com.waiting.test.config;

import com.waiting.test.componet.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    //关键，将拦截器作为bean写入配置中
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(new LoginInterceptor());

        addInterceptor.excludePathPatterns("/user/getUser","/good/getGood",
                "/good/selectGood","/good/searchGood","/images/**");

        addInterceptor.addPathPatterns("/**");
    }

//    // 跨域设置
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
//                .allowedOrigins("*")
//                .maxAge(3600)
//                .allowCredentials(true)
//                .allowedHeaders("*");
//        WebMvcConfigurer.super.addCorsMappings(registry);
//    }

}
