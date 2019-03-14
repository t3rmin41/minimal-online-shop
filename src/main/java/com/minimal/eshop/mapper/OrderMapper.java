package com.minimal.eshop.mapper;

import java.util.List;
import com.minimal.eshop.dto.OrderBean;
import com.minimal.eshop.dto.OrderStatusBean;
import com.minimal.eshop.domain.OrderDao;

public interface OrderMapper {

  List<OrderBean> getAllOrders();
  
  OrderBean getOrderById(Long id);
  
  OrderBean saveOrder(OrderBean bean);
  
  OrderBean updateOrder(OrderBean bean);
  
  boolean deleteOrderById(Long id);
  
  OrderBean convertOrderToBeanById(Long id);
  
  OrderStatusBean getTypeBeanByName(String name);
  
  List<OrderBean> convertJpaListToBeans(List<OrderDao> jpas);
  
  List<OrderBean> getUserOrdersByUsername(String email);
  
  List<OrderBean> getUserOrdersById(Long id);
}
