package com.minimal.eshop.service;

import java.util.List;
import com.minimal.eshop.dto.OrderDto;

public interface OrderService {

  List<OrderDto> getAllOrders();

  OrderDto getOrderById(Long id);

  OrderDto saveOrder(OrderDto dto);

  OrderDto updateOrder(OrderDto dto);

  boolean deleteOrderById(Long id);

  List<OrderDto> getUserOrdersByUsername(String username);
  
  List<OrderDto> getUserOrdersById(Long id);
}
