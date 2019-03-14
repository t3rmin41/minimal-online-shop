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
import com.minimal.eshop.dto.ProductBean;
import com.minimal.eshop.service.ProductService;

@Controller
@RequestMapping(value = "/products", produces = APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

  @Inject
  private ProductService productService;

  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<ProductBean> getAllProducts(UsernamePasswordAuthenticationToken token) {
    return productService.getAllProducts();
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ProductBean saveProduct(UsernamePasswordAuthenticationToken token, @RequestBody ProductBean bean) {
    return productService.saveProduct(bean);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody ProductBean getProductById(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return productService.getProductBeanById(id);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ProductBean updateProduct(UsernamePasswordAuthenticationToken token, @RequestBody ProductBean bean) {
    return productService.updateProduct(bean);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteProduct(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return productService.deleteProductById(id);
  }
  
}
