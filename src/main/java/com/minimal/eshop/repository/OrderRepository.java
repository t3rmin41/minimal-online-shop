package com.minimal.eshop.repository;

import java.util.List;
import com.minimal.eshop.domain.OrderJpa;

public interface OrderRepository {

  OrderJpa saveOrder(OrderJpa order);
  
  List<OrderJpa> getOrders();
  
  List<OrderJpa> getUserOrdersByEmail(String email);
  
  List<OrderJpa> getUserOrdersById(Long userId);
  
  OrderJpa getOrderById(Long id);
  
  OrderJpa updateOrder(OrderJpa order);
  
  boolean deleteOrderById(Long id);
}
