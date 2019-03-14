package com.minimal.eshop.repository;

import java.util.List;
import com.minimal.eshop.domain.ProductJpa;

public interface ProductRepository {

  List<ProductJpa> getAllProducts();
  
  ProductJpa getProductById(Long id);
  
  ProductJpa saveProduct(ProductJpa jpa);
  
  ProductJpa updateProduct(ProductJpa jpa);
  
  boolean deleteProductById(Long id);
}
