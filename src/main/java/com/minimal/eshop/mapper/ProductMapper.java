package com.minimal.eshop.mapper;

import java.util.List;
import com.minimal.eshop.bean.ProductBean;
import com.minimal.eshop.jpa.Product;

public interface ProductMapper {

  ProductBean getProductBeanByProduct(Product jpa);
  
  ProductBean getProductBeanById(Long id);
  
  List<ProductBean> getProductBeansByProducts(List<Product> jpas);
  
  List<ProductBean> getAllProducts();
  
  ProductBean saveProduct(ProductBean bean);
  
  ProductBean updateProduct(ProductBean bean);
  
  boolean deleteProductById(Long id);
  
}
