package com.minimal.eshop.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import com.minimal.eshop.config.ApplicationConfig;
import com.minimal.eshop.config.CachingConfig;
import com.minimal.eshop.config.H2DatabaseStop;
import com.minimal.eshop.config.JpaConfig;
import com.minimal.eshop.config.MvcConfig;
import com.minimal.eshop.config.SwaggerConfig;
import com.minimal.eshop.security.SecurityConfig;

@SpringBootApplication
@Import({ApplicationConfig.class, MvcConfig.class, JpaConfig.class, SwaggerConfig.class, SecurityConfig.class, CachingConfig.class}) // H2DatabaseStop.class})
@SuppressWarnings("unused")
public class ShopApplication { // extends SpringBootServletInitializer {
  
  private static Logger log = LoggerFactory.getLogger(ShopApplication.class);

  //for traditional .war deployment need to extend SpringBootServletInitializer
  //@Override
  //protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
  //    return application.sources(ShopApplication.class);
  //}

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(ShopApplication.class);
    springApplication.setBannerMode(Banner.Mode.OFF);
    ApplicationContext context = springApplication.run(args);
    log.warn("Context : " + context.getId());
  }
  
}
