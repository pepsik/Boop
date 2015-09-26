package org.pepsik.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by pepsik on 9/25/2015.
 */

@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/build/");
        registry.addResourceHandler("/uploads/**").addResourceLocations("/uploads/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/vendor/bootstrap/fonts/"); //temp
    }
}
