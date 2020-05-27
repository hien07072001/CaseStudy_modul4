package com.codegym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.codegym")
@PropertySource("classpath:global_config_app.properties")
public class AppConfigUploadFile extends WebMvcConfigurerAdapter  {
    @Autowired
    Environment environment;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fileUpload = environment.getProperty("file_upload").toString();
        registry.addResourceHandler("/a/**") //
                .addResourceLocations("file:" + fileUpload);
    }
}
