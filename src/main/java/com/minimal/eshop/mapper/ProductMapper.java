package com.minimal.eshop.mapper;

import java.util.List;
import com.minimal.eshop.dto.ProductDto;
import com.minimal.eshop.domain.ProductJpa;

public interface ProductMapper {

  ProductDto getProductDtoByProduct(ProductJpa jpa);
  
  ProductDto getProductDtoById(Long id);
  
  List<ProductDto> getProductDtosByProducts(List<ProductJpa> jpas);
  
  List<ProductDto> getAllProducts();
  
  ProductDto saveProduct(ProductDto dto);
  
  ProductDto updateProduct(ProductDto dto);
  
  boolean deleteProductById(Long id);
  
}
