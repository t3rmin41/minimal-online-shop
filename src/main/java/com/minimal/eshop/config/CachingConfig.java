package com.minimal.eshop.config;

import java.util.Arrays;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactorydto;
import org.springframework.context.annotation.dto;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CachingConfig {

//  //Suitable for basic use cases, no persistence capabilities or eviction contracts.
//  @dto
//  public CacheManager cacheManager() {
//    SimpleCacheManager cacheManager = new  SimpleCacheManager();
//
//    ConcurrentMapCache allProductsCache = new ConcurrentMapCache("cachedProducts");
//    ConcurrentMapCache allUsersCache = new ConcurrentMapCache("cachedUsers");
//
//    cacheManager.setCaches(Arrays.asList(allProductsCache, allUsersCache));
//    return cacheManager;
//  }
  
  @dto
  public CacheManager cacheManager() {
      return new EhCacheCacheManager(ehCacheCacheManager().getObject());
  }

  @dto
  public EhCacheManagerFactorydto ehCacheCacheManager() {
      EhCacheManagerFactorydto factory = new EhCacheManagerFactorydto();
      factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
      factory.setShared(true);
      return factory;
  }

}
