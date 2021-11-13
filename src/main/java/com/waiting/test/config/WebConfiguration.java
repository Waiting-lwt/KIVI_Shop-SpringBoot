
//package com.waiting.test.config;
//
//import java.io.File;
//import java.util.concurrent.Executors;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
//import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//
//
///**
// *
// * @author Administrator
// *
// */
//@Configuration
//public class WebConfiguration extends WebMvcConfigurationSupport{
//
//    //读取配置文件配置
//    @Value("${file.staticAccessPath}")
//    private String staticAccessPath;
//    @Value("${file.uploadFolder}")
//    private String uploadFolder;
//
//    // 跨域设置
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
//                .allowedOrigins("*")
//                .maxAge(3600)
//                .allowCredentials(true)
//                .allowedHeaders("*");
//        super.addCorsMappings(registry);
//    }
//
//    //配置图片回显
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(staticAccessPath+"/**").addResourceLocations("file:" + uploadFolder+File.separator);
//        // 解决静态资源无法访问
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
////      // 解决swagger无法访问
////      registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
////      // 解决swagger的js文件无法访问
////      registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
//    }
//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
//        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
//        configurer.setDefaultTimeout(30000);
//    }
//}
