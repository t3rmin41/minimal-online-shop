package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.minimal.eshop.dto.ProductDto;
import com.minimal.eshop.service.ProductService;

@Controller
@RequestMapping(value = "/products", produces = APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

  @Inject
  private ProductService productService;

  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<ProductDto> getAllProducts(UsernamePasswordAuthenticationToken token) {
    return productService.getAllProducts();
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ProductDto saveProduct(UsernamePasswordAuthenticationToken token, @RequestBody ProductDto dto) {
    return productService.saveProduct(dto);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody ProductDto getProductById(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return productService.getProductDtoById(id);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ProductDto updateProduct(UsernamePasswordAuthenticationToken token, @RequestBody ProductDto dto) {
    return productService.updateProduct(dto);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteProduct(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return productService.deleteProductById(id);
  }
  
}
