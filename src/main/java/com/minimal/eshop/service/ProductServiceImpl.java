package com.minimal.eshop.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.minimal.eshop.domain.ProductBean;
import com.minimal.eshop.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

  @Inject
  private ProductMapper productMapper;

  @Override
  @Cacheable(cacheNames = {"cachedProducts"}, sync = true, key = "'productsCacheKey'")
  public List<ProductBean> getAllProducts() {
    simulateSlowService();
    return productMapper.getAllProducts();
  }

  @Override
  @Cacheable(cacheNames = {"cachedProducts"}, sync = true, key = "'productsCacheKey'")
  public ProductBean getProductBeanById(Long id) {
    return productMapper.getProductBeanById(id);
  }

  @Override
  @Caching(
      evict = {@CacheEvict(cacheNames = {"cachedProducts"}, key = "'productsCacheKey'", allEntries = true)}
  )
  public ProductBean saveProduct(ProductBean bean) {
    return productMapper.saveProduct(bean);
  }

  @Override
  @Caching(
      put = {@CachePut(cacheNames = {"cachedProducts"}, key = "'productsCacheKey'")},
      evict = {@CacheEvict(cacheNames = {"cachedProducts"}, key = "'productsCacheKey'")}
  )
  public ProductBean updateProduct(ProductBean bean) {
    return productMapper.updateProduct(bean);
  }

  @Override
  @Caching(
      evict = {@CacheEvict(cacheNames = {"cachedProducts"}, key = "'productsCacheKey'")}
  )
  public boolean deleteProductById(Long id) {
    return productMapper.deleteProductById(id);
  }

  private void simulateSlowService() {
    try {
        long time = 5000L;
        Thread.sleep(time);
    } catch (InterruptedException e) {
        throw new IllegalStateException(e);
    }
  }
  
}
