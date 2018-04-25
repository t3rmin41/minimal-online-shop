package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import com.minimal.eshop.service.RequestValidator;

@Controller
@RequestMapping(value = "/orders", produces = APPLICATION_JSON_UTF8_VALUE)
public class OrderController {

  private List<String> allowedRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_MANAGER"}));
  
  @Autowired
  private OrderService orderService;

  @Autowired
  private RequestValidator requestValidator;
  
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public @ResponseBody List<OrderBean> getOrders(UsernamePasswordAuthenticationToken token) {
    requestValidator.validateRequestAgainstUserRoles(token, allowedRoles, "GET /orders/all");
    return orderService.getAllOrders();
  }
  
  @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
  public @ResponseBody List<OrderBean> getUserOrdersByName(@PathVariable("username") String username, UsernamePasswordAuthenticationToken token) {
    List<String> allowed = new LinkedList<String>();
    allowed.addAll(allowedRoles); allowed.add("ROLE_CUSTOMER");
    requestValidator.validateRequestAgainstUserRoles(token, allowed, "GET /orders/user/username");
    return orderService.getUserOrdersByUsername(username);
  }
  
  @RequestMapping(value = "/user/id/{userId}", method = RequestMethod.GET)
  public @ResponseBody List<OrderBean> getUserOrdersById(@PathVariable("userId") Long id, UsernamePasswordAuthenticationToken token) {
    List<String> allowed = new LinkedList<String>();
    allowed.addAll(allowedRoles); allowed.add("ROLE_CUSTOMER");
    requestValidator.validateRequestAgainstUserRoles(token, allowed, "GET /orders/user/id");
    return orderService.getUserOrdersById(id);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody OrderBean getOrderById(@PathVariable("id") Long id, UsernamePasswordAuthenticationToken token) {
    List<String> allowed = new LinkedList<String>();
    allowed.addAll(allowedRoles); allowed.add("ROLE_CUSTOMER");
    requestValidator.validateRequestAgainstUserRoles(token, allowed, "GET /orders/id");
    return orderService.getOrderById(id);
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody OrderBean createOrder(@RequestBody OrderBean bean, UsernamePasswordAuthenticationToken token) {
    requestValidator.validateRequestAgainstUserRoles(token, allowedRoles, "POST /orders/save");
    return orderService.saveOrder(bean);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody OrderBean updateOrder(@RequestBody OrderBean bean, UsernamePasswordAuthenticationToken token) {
    requestValidator.validateRequestAgainstUserRoles(token, allowedRoles, "PUT /orders/update");
    return orderService.updateOrder(bean);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteOrder(@PathVariable("id") Long id, UsernamePasswordAuthenticationToken token) {
    List<String> allowed = new LinkedList<String>();
    allowed.addAll(allowedRoles); allowed.add("ROLE_CUSTOMER");
    requestValidator.validateRequestAgainstUserRoles(token, allowed, "DELETE /orders/id");
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
