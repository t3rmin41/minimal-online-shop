package com.minimal.eshop.mapper;

import java.util.List;
import com.minimal.eshop.dto.OrderDto;
import com.minimal.eshop.dto.OrderStatusDto;
import com.minimal.eshop.domain.OrderJpa;

public interface OrderMapper {

  List<OrderDto> getAllOrders();
  
  OrderDto getOrderById(Long id);
  
  OrderDto saveOrder(OrderDto dto);
  
  OrderDto updateOrder(OrderDto dto);
  
  boolean deleteOrderById(Long id);
  
  OrderDto convertOrderTodtoById(Long id);
  
  OrderStatusDto getTypedtoByName(String name);
  
  List<OrderDto> convertJpaListTodtos(List<OrderJpa> jpas);
  
  List<OrderDto> getUserOrdersByUsername(String email);
  
  List<OrderDto> getUserOrdersById(Long id);
}
