package com.minimal.eshop.repository;

import java.util.List;
import com.minimal.eshop.domain.ProductDao;

public interface ProductRepository {

  List<ProductDao> getAllProducts();
  
  ProductDao getProductById(Long id);
  
  ProductDao saveProduct(ProductDao jpa);
  
  ProductDao updateProduct(ProductDao jpa);
  
  boolean deleteProductById(Long id);
}
