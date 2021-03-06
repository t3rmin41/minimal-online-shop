package com.minimal.eshop.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

  private static final String SHOW_SQL_PROPERTY = "hibernate.show_sql";
  private static final String DIALECT_PROPERTY = "hibernate.dialect";
  private static final String H2_CONSOLE_WEB_ALLOW_PROPERTY = "h2.console.settings.web-allow-others";

  @Value("${spring.profiles.active}")
  private String activeProfile;
  
  @Bean(name = "dataSource")
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    if ("prod".equals(activeProfile)) {
      driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
      driverManagerDataSource.setUrl("jdbc:postgresql://ec2-54-75-244-248.eu-west-1.compute.amazonaws.com:5432/def9rmt7j8882a");
      driverManagerDataSource.setUsername("tbjznubkniaknu"); // using account credentials
      driverManagerDataSource.setPassword("4b95dfb88aa6f03b3c374e74b64dff988848be8a2e41fd6a9787438fdf14e2c1");
    } else if ("test".equals(activeProfile)) {
      driverManagerDataSource.setDriverClassName("org.h2.Driver");
      driverManagerDataSource.setUrl("jdbc:h2:./db/test/bin;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE");
      driverManagerDataSource.setPassword("t3sts3cr3t");
    } else if ("dev".equals(activeProfile)) {
      driverManagerDataSource.setDriverClassName("org.h2.Driver");
      driverManagerDataSource.setUrl("jdbc:h2:./db/dev/bin;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE");
      driverManagerDataSource.setUsername("sa");
      //driverManagerDataSource.setPassword("d3vs3cr3t");
    }
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
    em.setPackagesToScan(new String[] { "com.minimal.eshop.repository", "com.minimal.eshop.domain" });
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());
    return em;
  }
  
  public Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "update");
    if ("prod".equals(activeProfile)) {
      properties.setProperty(SHOW_SQL_PROPERTY, "false");
      properties.setProperty(DIALECT_PROPERTY, "org.hibernate.dialect.PostgreSQL92Dialect");
    } else if ("test".equals(activeProfile)) {
      properties.setProperty(SHOW_SQL_PROPERTY, "true");
      properties.setProperty(DIALECT_PROPERTY, "org.hibernate.dialect.H2Dialect");
      properties.setProperty(H2_CONSOLE_WEB_ALLOW_PROPERTY, "true");
    } else if ("dev".equals(activeProfile)) {
      properties.setProperty(SHOW_SQL_PROPERTY, "true");
      properties.setProperty(DIALECT_PROPERTY, "org.hibernate.dialect.H2Dialect");
      properties.setProperty(H2_CONSOLE_WEB_ALLOW_PROPERTY, "true");
    }
    return properties;
 }

}