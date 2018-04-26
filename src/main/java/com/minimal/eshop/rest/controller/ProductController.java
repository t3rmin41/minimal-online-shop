package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.minimal.eshop.domain.ProductBean;
import com.minimal.eshop.service.ProductService;
import com.minimal.eshop.service.RequestValidator;

@Controller
@RequestMapping(value = "/products", produces = APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

  private List<String> allowedRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_MANAGER"}));
  
  @Autowired
  private ProductService productService;

  @Autowired
  private RequestValidator requestValidator;
  
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List<ProductBean> getProductsAll(UsernamePasswordAuthenticationToken token) {
    List<String> allowed = new LinkedList<String>();
    allowed.addAll(allowedRoles); allowed.add("ROLE_CUSTOMER");
    requestValidator.validateRequestAgainstUserRoles(token, allowed, "GET /products/all");
    return productService.getAllProducts();
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ProductBean saveProduct(@RequestBody ProductBean bean, UsernamePasswordAuthenticationToken token) {
    requestValidator.validateRequestAgainstUserRoles(token, allowedRoles, "POST /products/save");
    return productService.saveProduct(bean);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody ProductBean getProductById(@PathVariable("id") Long id, UsernamePasswordAuthenticationToken token) {
    List<String> allowed = new LinkedList<String>();
    allowed.addAll(allowedRoles); allowed.add("ROLE_CUSTOMER");
    requestValidator.validateRequestAgainstUserRoles(token, allowed, "GET /products/id");
    return productService.getProductBeanById(id);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ProductBean updateProduct(@RequestBody ProductBean bean, UsernamePasswordAuthenticationToken token) {
    requestValidator.validateRequestAgainstUserRoles(token, allowedRoles, "PUT /products/update");
    return productService.updateProduct(bean);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteProduct(@PathVariable("id") Long id, UsernamePasswordAuthenticationToken token) {
    requestValidator.validateRequestAgainstUserRoles(token, allowedRoles, "DELETE /products/delete");
    return productService.deleteProductById(id);
  }
  
}
