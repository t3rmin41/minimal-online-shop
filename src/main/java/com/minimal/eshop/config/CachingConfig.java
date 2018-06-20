package com.minimal.eshop.config;

import java.util.Arrays;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new  SimpleCacheManager();

    ConcurrentMapCache allProductsCache = new ConcurrentMapCache("cachedProducts");
    ConcurrentMapCache allUsersCache = new ConcurrentMapCache("cachedUsers");

    cacheManager.setCaches(Arrays.asList(allProductsCache, allUsersCache));
    return cacheManager;
  }

}
