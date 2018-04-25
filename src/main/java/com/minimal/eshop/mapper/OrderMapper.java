package com.minimal.eshop.mapper;

import java.util.List;
import com.minimal.eshop.bean.OrderBean;
import com.minimal.eshop.bean.OrderStatusBean;
import com.minimal.eshop.jpa.Order;

public interface OrderMapper {

  List<OrderBean> getAllOrders();
  
  OrderBean getOrderById(Long id);
  
  OrderBean saveOrder(OrderBean bean);
  
  OrderBean updateOrder(OrderBean bean);
  
  boolean deleteOrderById(Long id);
  
  OrderBean convertOrderToBeanById(Long id);
  
  OrderStatusBean getTypeBeanByName(String name);
  
  List<OrderBean> convertJpaListToBeans(List<Order> jpas);
  
  List<OrderBean> getUserOrdersByUsername(String email);
  
  List<OrderBean> getUserOrdersById(Long id);
}
