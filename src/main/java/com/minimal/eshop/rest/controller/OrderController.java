package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.minimal.eshop.dto.OrderDto;
import com.minimal.eshop.dto.OrderStatusDto;
import com.minimal.eshop.enums.OrderStatus;
import com.minimal.eshop.service.OrderService;

@Controller
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_UTF8_VALUE)
public class OrderController {

  @Inject
  private OrderService orderService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public @ResponseBody List<OrderDto> getAllOrders(UsernamePasswordAuthenticationToken token) {
    return orderService.getAllOrders();
  }
  
  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  public @ResponseBody List<OrderDto> getUserOrdersByName(UsernamePasswordAuthenticationToken token, @PathVariable("username") String username) {
    return orderService.getUserOrdersByUsername(username);
  }
  
  @RequestMapping(value = "/user/id/{userId}", method = RequestMethod.GET)
  public @ResponseBody List<OrderDto> getUserOrdersById(UsernamePasswordAuthenticationToken token, @PathVariable("userId") Long id) {
    return orderService.getUserOrdersById(id);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody OrderDto getOrderById(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return orderService.getOrderById(id);
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody OrderDto saveOrder(UsernamePasswordAuthenticationToken token, @RequestBody OrderDto dto) {
    return orderService.saveOrder(dto);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody OrderDto updateOrder(UsernamePasswordAuthenticationToken token, @RequestBody OrderDto dto) {
    return orderService.updateOrder(dto);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteOrder(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return orderService.deleteOrderById(id);
  }
  
  @RequestMapping(value = "/statuslist", method = RequestMethod.GET)
  public @ResponseBody List<OrderStatusDto> getOrderStatusList() {
    List<OrderStatusDto> statusList = new ArrayList<OrderStatusDto>();
    for (OrderStatus status : OrderStatus.values()) {
      statusList.add(new OrderStatusDto().setCode(status.name()).setTitle(status.getStatusTitle()));
    }
    return statusList;
  }
  
}
