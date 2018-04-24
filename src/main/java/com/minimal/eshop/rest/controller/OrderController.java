package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.minimal.eshop.bean.OrderBean;
import com.minimal.eshop.bean.OrderStatusBean;
import com.minimal.eshop.enums.OrderStatus;
import com.minimal.eshop.service.OrderService;

@Controller
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_UTF8_VALUE)
public class OrderController {

  @Autowired
  private OrderService orderService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public @ResponseBody List<OrderBean> getOrders() {
      return orderService.getAllOrders();
  }
  
  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  public @ResponseBody List<OrderBean> getUserOrdersByName(@PathVariable("username") String username) {
      return orderService.getUserOrdersByUsername(username);
  }
  
  @RequestMapping(value = "/user/id/{userId}", method = RequestMethod.GET)
  public @ResponseBody List<OrderBean> getUserOrdersById(@PathVariable("userId") Long id) {
      return orderService.getUserOrdersById(id);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody OrderBean getOrderById(@PathVariable("id") Long id) {
      return orderService.getOrderById(id);
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody OrderBean createOrder(@RequestBody OrderBean bean) {
      return orderService.saveOrder(bean);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody OrderBean updateOrder(@RequestBody OrderBean bean) {
      return orderService.updateOrder(bean);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteOrder(@PathVariable("id") Long id) {
      return orderService.deleteOrderById(id);
  }
  
  @RequestMapping(value = "/statuslist", method = RequestMethod.GET)
  public @ResponseBody List<OrderStatusBean> getOrderStatusList() {
    List<OrderStatusBean> statusList = new LinkedList<OrderStatusBean>();
    for (OrderStatus status : OrderStatus.values()) {
      statusList.add(new OrderStatusBean().setCode(status.name()).setTitle(status.getStatusTitle()));
    }
    return statusList;
  }
  
}
