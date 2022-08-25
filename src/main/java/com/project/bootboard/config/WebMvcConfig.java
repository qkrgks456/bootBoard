package com.project.bootboard.config;

import com.project.bootboard.intercepter.CommonInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final CommonInterceptor commonInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터 경로 설정
        registry.addInterceptor(commonInterceptor)
                .excludePathPatterns("/board/list", "/test"); // 제외할 경로 적어라
    }

}
