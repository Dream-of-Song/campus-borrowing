package com.example.campusborrowing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    注册拦截类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 注入JwtInterceptor
    @Autowired
    private JwtInterceptor jwtInterceptor;
    // 注册拦截器 需要重写一个方法
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/user/signup","/upload/**","/file/upload");
    }
    @Value("${file.update_dir.file}")
    private String uploadDir;
    // 配置静态资源映射，让前端代码的路径映射到 服务器真正存储文件的路径
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:"+uploadDir+"/");
    }
}
