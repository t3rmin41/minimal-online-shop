package com.minimal.eshop.service;

import java.util.List;
import com.minimal.eshop.dto.ProductDto;

public interface ProductService {

  List<ProductDto> getAllProducts();
  
  ProductDto getProductDtoById(Long id);
  
  ProductDto saveProduct(ProductDto dto);
  
  ProductDto updateProduct(ProductDto dto);
  
  boolean deleteProductById(Long id);
  
}
