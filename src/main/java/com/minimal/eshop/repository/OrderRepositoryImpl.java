package com.minimal.eshop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.minimal.eshop.jpa.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  @Transactional
  public Order saveOrder(Order order) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public List<Order> getOrders() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public List<Order> getUserOrdersByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public Order getOrderById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public Order updateOrder(Order order) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public boolean deleteOrderById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

}
