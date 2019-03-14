package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.security.Principal;
import javax.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.minimal.eshop.dto.CartDto;
import com.minimal.eshop.dto.OrderDto;
import com.minimal.eshop.dto.ProductDto;
import com.minimal.eshop.service.CartService;
import com.minimal.eshop.service.ProductService;
import com.minimal.eshop.service.UserService;

@Controller
@RequestMapping(value = "/cart", produces = APPLICATION_JSON_UTF8_VALUE)
@Scope(value = "session")
@SuppressWarnings("unused")
public class CartController {

  @Inject
  private CartService cartService;
  
  @Inject
  private ProductService productService;
  
  @Inject
  private UserService userService;
  
  @Inject
  private CartDto CartDto;
  
  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody CartDto getCart(UsernamePasswordAuthenticationToken token) {
    return CartDto;
  }
  
  @RequestMapping(value = "/product/add", method = RequestMethod.POST, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartDto addProductToCart(UsernamePasswordAuthenticationToken token, @RequestBody ProductDto ProductDto, Principal principal) {
    ProductDto product = productService.getProductDtoById(ProductDto.getId());
    OrderDto OrderDto = new OrderDto().setProductId(product.getId()).setOrderedBy(principal.getName());
    OrderDto.setProductName(product.getTitle());
    OrderDto.setShortDescription(ProductDto.getShortDescription());
    OrderDto.setPrice(product.getPrice());
    CartDto.getItems().add(OrderDto);
    return CartDto;
  }
  
  @RequestMapping(value = "/product/remove", method = RequestMethod.DELETE, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartDto removeProductFromCart(UsernamePasswordAuthenticationToken token, @RequestBody OrderDto OrderDto, Principal principal) {
    CartDto.getItems().remove(OrderDto);
    return CartDto;
  }
  
  @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartDto submitCart(UsernamePasswordAuthenticationToken token, Principal principal) {
    //UserDto UserDto = userService.getUserByEmail(principal.getName());
    //CartDto.setUserId(UserDto.getId());
    cartService.submitCart(CartDto);
    CartDto.clear();
    return CartDto;
  }
  
}
