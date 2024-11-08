package cata311.demo311.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import javax.annotation.PostConstruct;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private TemplateEngine templateEngine;

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @PostConstruct
    public void additionalSetup() {
        templateEngine.addDialect(java8TimeDialect());
    }
}