package com.minimal.eshop.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.minimal.eshop.jpa.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

  @Override
  public Order saveOrder(Order order) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Order> getOrders() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Order> getUserOrdersByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Order getOrderById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Order updateOrder(Order order) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteOrderById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

}
