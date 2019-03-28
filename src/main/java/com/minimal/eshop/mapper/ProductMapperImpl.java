package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.ProductDto;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongDtoFormatException;
import com.minimal.eshop.domain.ProductJpa;
import com.minimal.eshop.repository.ProductRepository;

@Service
public class ProductMapperImpl implements ProductMapper, DtoValidator {

  @Inject
  private ProductRepository productRepo;
  
  @Override
  public ProductDto getProductDtoByProduct(ProductJpa jpa) {
    return convertJpaTodto(jpa);
  }

  @Override
  public ProductDto getProductDtoById(Long id) {
    return convertJpaTodto(productRepo.getProductById(id));
  }

  @Override
  public List<ProductDto> getProductDtosByProducts(List<ProductJpa> jpas) {
    List<ProductDto> dtos = new ArrayList<ProductDto>();
    jpas.stream().forEach(p -> {
      dtos.add(convertJpaTodto(p));
    });
    return dtos;
  }

  @Override
  public List<ProductDto> getAllProducts() {
    return getProductDtosByProducts(productRepo.getAllProducts());
  }

  @Override
  public ProductDto saveProduct(ProductDto dto) {
    validatedto(dto);
    ProductJpa jpa = new ProductJpa();
    jpa.setPrice(dto.getPrice());
    jpa.setTitle(dto.getTitle());
    jpa.setShortDescription(dto.getShortDescription());
    jpa = productRepo.saveProduct(jpa);
    return convertJpaTodto(jpa);
  }

  @Override
  public ProductDto updateProduct(ProductDto dto) {
    validatedto(dto);
    ProductJpa jpa = productRepo.getProductById(dto.getId());
    jpa.setPrice(dto.getPrice());
    return convertJpaTodto(productRepo.updateProduct(jpa));
  }

  @Override
  public boolean deleteProductById(Long id) {
    return productRepo.deleteProductById(id);
  }

  @Override
  public List<ErrorField> validatedto(Serializable dto) throws WrongDtoFormatException {
    List<ErrorField> errors = new ArrayList<ErrorField>();
    ProductDto ProductDto = (ProductDto) dto;
    if (ProductDto.getShortDescription().length() > 15) {
      errors.add(new ErrorField("shortDescription", "Short description maximum length is 15 chars"));
    }
    if (ProductDto.getPrice() < 0) {
      errors.add(new ErrorField("price", "Price cannot be negative"));
    }
    if (errors.size() > 0) {
      throw new WrongDtoFormatException("Wrong data entered", errors);
    }
    return errors;
  }

  private ProductDto convertJpaTodto(ProductJpa jpa) {
    return new ProductDto().setId(jpa.getId()).setTitle(jpa.getTitle())
                            .setShortDescription(jpa.getShortDescription())
                            .setPrice(jpa.getPrice()).setDeleted(jpa.isDeleted());
  }
  
}
