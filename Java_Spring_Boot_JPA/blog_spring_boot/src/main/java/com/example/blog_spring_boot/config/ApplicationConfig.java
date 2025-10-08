//package com.example.blog_spring_boot.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.web.config.EnableSpringDataWebSupport;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//@EnableWebMvc
//@EnableTransactionManagement
//@ComponentScan(basePackages = "com.example.blog_spring_boot")
//@EnableJpaRepositories("com.example.blog_spring_boot.repository")
//@EnableSpringDataWebSupport
//public class ApplicationConfig implements WebMvcConfigurer, ApplicationContextAware {
//    private ApplicationContext applicationContext;
//
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}
