package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.security.Principal;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.minimal.eshop.bean.CartBean;
import com.minimal.eshop.bean.OrderBean;
import com.minimal.eshop.bean.ProductBean;
import com.minimal.eshop.bean.UserBean;
import com.minimal.eshop.service.CartService;
import com.minimal.eshop.service.ProductService;
import com.minimal.eshop.service.UserService;

@Controller
@RequestMapping(value = "/cart", produces = APPLICATION_JSON_UTF8_VALUE)
@Scope(value = "session")
public class CartController {

  @Autowired
  private CartService cartService;
  
  @Autowired
  private ProductService productService;
  
  @Autowired
  private UserService userService;
  
  @Inject
  private CartBean cartBean;
  
  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody CartBean getCart() {
      return cartBean;
  }
  
  @RequestMapping(value = "/product/add", method = RequestMethod.POST, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartBean addProductToCart(@RequestBody ProductBean productBean, Principal principal) {
      ProductBean product = productService.getProductBeanById(productBean.getId());
      OrderBean orderBean = new OrderBean().setProductId(product.getId()).setOrderedBy(principal.getName());
      orderBean.setProductName(product.getTitle());
      orderBean.setShortDescription(productBean.getShortDescription());
      orderBean.setPrice(product.getPrice());
      cartBean.getItems().add(orderBean);
      return cartBean;
  }
  
  @RequestMapping(value = "/product/remove", method = RequestMethod.DELETE, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartBean removeProductFromCart(@RequestBody OrderBean orderBean, Principal principal) {
      cartBean.getItems().remove(orderBean);
      return cartBean;
  }
  
  @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartBean submitCart(Principal principal) {
    UserBean userBean = userService.getUserByEmail(principal.getName());
    cartBean.setUserId(userBean.getId());
    cartService.submitCart(cartBean);
    cartBean.clear();
    return cartBean;
  }
  
}
