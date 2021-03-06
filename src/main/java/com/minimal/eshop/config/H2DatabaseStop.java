package com.minimal.eshop.config;

import java.lang.reflect.Field;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.IdentityHashMap;
import javax.inject.Inject;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

//@Configuration
@SuppressWarnings("rawtypes")
public class H2DatabaseStop implements ApplicationListener<ContextClosedEvent> {

  @Inject
  private ApplicationContext appContext;

  public void onApplicationEvent(ContextClosedEvent event) {
    Thread h2ShutDownHook = findDbCloserHook(getShutdownHooks());
    if (h2ShutDownHook != null) {
      closeDataSource();
      deregisterAllDrivers();
      h2ShutDownHook.start();
    }
  }

  private IdentityHashMap getShutdownHooks() {
    return (IdentityHashMap) getStaticFieldValue("java.lang.ApplicationShutdownHooks", "hooks");
  }

  private Object getStaticFieldValue(String className, String fieldName) {
    try {
      Class<?> clazz = Class.forName(className);
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(null);

    } catch (Exception ex) {
      return null;
    }
  }

  private Thread findDbCloserHook(IdentityHashMap shutdownHooks) {
    if (shutdownHooks != null) {
      for (Object key : shutdownHooks.keySet()) {
        Object threadObj = shutdownHooks.get(key);
        if (isInstance("org.h2.engine.DatabaseCloser", threadObj)) {
          return (Thread) threadObj;
        }
      }
    }
    return null;
  }

  private boolean isInstance(String className, Object obj) {
    try {
      return Class.forName(className).isInstance(obj);
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  private void closeDataSource() {
    DataSource ds = (DataSource) appContext.getBean("dataSource");
    if (ds != null) {
      ds.close(true);
      ds = null;
    }
  }

  private void deregisterAllDrivers() {
    Enumeration<Driver> drivers = DriverManager.getDrivers();

    while (drivers.hasMoreElements()) {
      Driver driver = drivers.nextElement();
      try {
        DriverManager.deregisterDriver(driver);
      } catch (SQLException e) {
      }
    }
  }

}
