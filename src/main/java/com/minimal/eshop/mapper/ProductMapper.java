package com.minimal.eshop.mapper;

import java.util.List;
import com.minimal.eshop.dto.ProductBean;
import com.minimal.eshop.domain.ProductDao;

public interface ProductMapper {

  ProductBean getProductBeanByProduct(ProductDao jpa);
  
  ProductBean getProductBeanById(Long id);
  
  List<ProductBean> getProductBeansByProducts(List<ProductDao> jpas);
  
  List<ProductBean> getAllProducts();
  
  ProductBean saveProduct(ProductBean bean);
  
  ProductBean updateProduct(ProductBean bean);
  
  boolean deleteProductById(Long id);
  
}
