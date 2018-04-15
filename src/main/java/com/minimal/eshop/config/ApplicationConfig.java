package com.minimal.eshop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.minimal.eshop.view.controller", "com.minimal.eshop.rest.controller",
                               "com.minimal.eshop.service", "com.minimal.eshop.mapper", "com.minimal.eshop.repository",
                               "com.minimal.eshop.errorhandling", "com.minimal.eshop.security"
})
public class ApplicationConfig {

}
