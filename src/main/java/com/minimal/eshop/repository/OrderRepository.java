package com.minimal.eshop.repository;

import java.util.List;
import com.minimal.eshop.domain.OrderDao;

public interface OrderRepository {

  OrderDao saveOrder(OrderDao order);
  
  List<OrderDao> getOrders();
  
  List<OrderDao> getUserOrdersByEmail(String email);
  
  List<OrderDao> getUserOrdersById(Long userId);
  
  OrderDao getOrderById(Long id);
  
  OrderDao updateOrder(OrderDao order);
  
  boolean deleteOrderById(Long id);
}
