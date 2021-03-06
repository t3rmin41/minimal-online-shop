package com.minimal.eshop.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.minimal.eshop.app.ApplicationContextProvider;

@Configuration
@ComponentScan(basePackages = {"com.minimal.eshop.view.controller", "com.minimal.eshop.rest.controller", "com.minimal.eshop.domain",
                               "com.minimal.eshop.service", "com.minimal.eshop.mapper", "com.minimal.eshop.repository",
                               "com.minimal.eshop.aspect",
                               "com.minimal.eshop.dto",
                               "com.minimal.eshop.errorhandling", "com.minimal.eshop.security"
})
public class ApplicationConfig {

  @Bean
  public ApplicationContextProvider applicationContextProvider() {
    return new ApplicationContextProvider();
  }
  
  @Bean
  public ServletRegistrationBean h2servletRegistration(){
      ServletRegistrationBean registrationdto = new ServletRegistrationBean(new WebServlet());
      registrationdto.addUrlMappings("/h2-console/*");
      return registrationdto;
  }
  
}
