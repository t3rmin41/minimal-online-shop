package com.minimal.eshop.repository;

import java.util.List;
import com.minimal.eshop.jpa.Product;

public interface ProductRepository {

  List<Product> getAllProducts();
  
  Product getProductById(Long id);
  
  Product saveProduct(Product jpa);
  
  Product updateProduct(Product jpa);
  
  boolean deleteProductById(Long id);
}
