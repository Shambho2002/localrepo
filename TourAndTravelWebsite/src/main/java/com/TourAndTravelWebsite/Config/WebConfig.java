package com.TourAndTravelWebsite.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /uploads/** to the folder on disk
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:/TourUploads/");
    }
}
