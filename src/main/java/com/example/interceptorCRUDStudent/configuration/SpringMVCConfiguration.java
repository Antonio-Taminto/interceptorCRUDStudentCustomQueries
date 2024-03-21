package com.example.interceptorCRUDStudent.configuration;

import com.example.interceptorCRUDStudent.interceptors.StudentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringMVCConfiguration implements WebMvcConfigurer {
    @Autowired
    private StudentInterceptor studentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(studentInterceptor);
    }
}
