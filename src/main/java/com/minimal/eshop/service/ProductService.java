package com.minimal.eshop.service;

import java.util.List;
import com.minimal.eshop.bean.ProductBean;

public interface ProductService {

  List<ProductBean> getAllProducts();
  
  ProductBean getProductBeanById(Long id);
  
  ProductBean saveProduct(ProductBean bean);
  
  ProductBean updateProduct(ProductBean bean);
  
  boolean deleteProductById(Long id);
  
}
