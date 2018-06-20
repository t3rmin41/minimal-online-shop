package com.minimal.eshop.test.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.minimal.eshop.service", "com.minimal.eshop.mapper", "com.minimal.eshop.repository"})
@EnableTransactionManagement
@SuppressWarnings("unused")
public class ServiceTestConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Bean(name = "dataSource")
  public DriverManagerDataSource dataSource() {
      DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
      driverManagerDataSource.setDriverClassName("org.h2.Driver");
      driverManagerDataSource.setUsername("sa");
      driverManagerDataSource.setUrl("jdbc:h2:./src/test/resources/db/dev/bin");
      return driverManagerDataSource;
  }
  
  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
     JpaTransactionManager transactionManager = new JpaTransactionManager();
     transactionManager.setEntityManagerFactory(emf);

     return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
     return new PersistenceExceptionTranslationPostProcessor();
  }
  
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan(new String[] { "com.domain.servicedesk.wallscreen.repository", "com.domain.servicedesk.wallscreen.jpa" });

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());

    return em;
  }
  
  Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "update");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    return properties;
  }
  
}
