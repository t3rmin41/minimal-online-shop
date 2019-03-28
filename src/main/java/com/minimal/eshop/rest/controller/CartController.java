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
  private CartDto cartDto;
  
  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody CartDto getCart(UsernamePasswordAuthenticationToken token) {
    return cartDto;
  }
  
  @RequestMapping(value = "/product/add", method = RequestMethod.POST, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartDto addProductToCart(UsernamePasswordAuthenticationToken token, @RequestBody ProductDto productDto, Principal principal) {
    ProductDto product = productService.getProductDtoById(productDto.getId());
    OrderDto orderDto = new OrderDto().setProductId(product.getId()).setOrderedBy(principal.getName());
    orderDto.setProductName(product.getTitle());
    orderDto.setShortDescription(productDto.getShortDescription());
    orderDto.setPrice(product.getPrice());
    cartDto.getItems().add(orderDto);
    return cartDto;
  }
  
  @RequestMapping(value = "/product/remove", method = RequestMethod.DELETE, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartDto removeProductFromCart(UsernamePasswordAuthenticationToken token, @RequestBody OrderDto orderDto, Principal principal) {
    cartDto.getItems().remove(orderDto);
    return cartDto;
  }
  
  @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes=APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CartDto submitCart(UsernamePasswordAuthenticationToken token, Principal principal) {
    //UserDto UserDto = userService.getUserByEmail(principal.getName());
    //CartDto.setUserId(UserDto.getId());
    cartService.submitCart(cartDto);
    cartDto.clear();
    return cartDto;
  }
  
}
