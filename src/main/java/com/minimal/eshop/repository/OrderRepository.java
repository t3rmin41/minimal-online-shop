package com.minimal.eshop.repository;

import java.util.List;
import com.minimal.eshop.jpa.Order;

public interface OrderRepository {

  Order saveOrder(Order order);
  
  List<Order> getOrders();
  
  List<Order> getUserOrdersByEmail(String email);
  
  Order getOrderById(Long id);
  
  Order updateOrder(Order order);
  
  boolean deleteOrderById(Long id);
}
