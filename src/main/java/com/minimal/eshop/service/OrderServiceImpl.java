package com.minimal.eshop.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.minimal.eshop.bean.OrderBean;

@Service
public class OrderServiceImpl implements OrderService {

  @Override
  public List<OrderBean> getAllOrders() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OrderBean getOrderById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OrderBean saveOrder(OrderBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OrderBean updateOrder(OrderBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteOrderById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<OrderBean> getUserOrdersByUsername(String username) {
    // TODO Auto-generated method stub
    return null;
  }

}
