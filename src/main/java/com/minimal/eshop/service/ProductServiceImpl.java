package com.minimal.eshop.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.domain.ProductBean;
import com.minimal.eshop.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

  @Inject
  private ProductMapper productMapper;
  
  @Override
  public List<ProductBean> getAllProducts() {
    return productMapper.getAllProducts();
  }

  @Override
  public ProductBean getProductBeanById(Long id) {
    return productMapper.getProductBeanById(id);
  }

  @Override
  public ProductBean saveProduct(ProductBean bean) {
    return productMapper.saveProduct(bean);
  }

  @Override
  public ProductBean updateProduct(ProductBean bean) {
    return productMapper.updateProduct(bean);
  }

  @Override
  public boolean deleteProductById(Long id) {
    return productMapper.deleteProductById(id);
  }

}
