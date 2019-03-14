package com.minimal.eshop.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.ProductDto;
import com.minimal.eshop.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {


  @Inject
  private ProductMapper productMapper;

  @Override
  @Cacheable(cacheNames = {"cachedProducts"}, sync = true, key = "'productsCacheKey'")
  public List<ProductDto> getAllProducts() {
    return productMapper.getAllProducts();
  }

  @Override
  public ProductDto getProductDtoById(Long id) {
    return productMapper.getProductDtoById(id);
  }

  @Override
  @Caching(
      evict = {@CacheEvict(cacheNames = {"cachedProducts"}, key = "'productsCacheKey'", allEntries = true)}
  )
  public ProductDto saveProduct(ProductDto dto) {
    return productMapper.saveProduct(dto);
  }

  @Override
  @Caching(
      put = {@CachePut(cacheNames = {"cachedProducts"}, key = "'productsCacheKey'")},
      evict = {@CacheEvict(cacheNames = {"cachedProducts"}, key = "'productsCacheKey'")}
  )
  public ProductDto updateProduct(ProductDto dto) {
    return productMapper.updateProduct(dto);
  }

  @Override
  @Caching(
      evict = {@CacheEvict(cacheNames = {"cachedProducts"}, key = "'productsCacheKey'")}
  )
  public boolean deleteProductById(Long id) {
    return productMapper.deleteProductById(id);
  }

}
