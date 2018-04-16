package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.minimal.eshop.bean.ProductBean;
import com.minimal.eshop.service.ProductService;

@Controller
@RequestMapping(value = "/products", produces = APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<ProductBean> getProductsAll() {
      return productService.getAllProducts();
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ProductBean saveProduct(@RequestBody ProductBean bean) {
      return productService.saveProduct(bean);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody ProductBean getProductById(@PathVariable("id") Long id) {
      return productService.getProductBeanById(id);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ProductBean updateProduct(@RequestBody ProductBean bean) {
      return productService.updateProduct(bean);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteProduct(@PathVariable("id") Long id) {
      return productService.deleteProductById(id);
  }
  
}
