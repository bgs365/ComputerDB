package com.excilys.cdb.restConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.excilys.cdb.restController","com.excilys.cdb.springConfig"})
public class ServiceConfig implements WebMvcConfigurer {
}