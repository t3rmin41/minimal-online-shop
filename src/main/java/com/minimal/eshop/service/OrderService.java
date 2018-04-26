package com.minimal.eshop.service;

import java.util.List;
import com.minimal.eshop.domain.OrderBean;

public interface OrderService {

  List<OrderBean> getAllOrders();

  OrderBean getOrderById(Long id);

  OrderBean saveOrder(OrderBean bean);

  OrderBean updateOrder(OrderBean bean);

  boolean deleteOrderById(Long id);

  List<OrderBean> getUserOrdersByUsername(String username);
  
  List<OrderBean> getUserOrdersById(Long id);
}
