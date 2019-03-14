package com.minimal.eshop.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.OrderBean;
import com.minimal.eshop.enums.OrderStatus;
import com.minimal.eshop.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

  @Inject
  private OrderMapper orderMapper;
  
  @Override
  public List<OrderBean> getAllOrders() {
    return orderMapper.getAllOrders();
  }

  @Override
  public OrderBean getOrderById(Long id) {
    return orderMapper.getOrderById(id);
  }

  @Override
  public OrderBean saveOrder(OrderBean bean) {
    return orderMapper.saveOrder(bean);
  }

  @Override
  public OrderBean updateOrder(OrderBean bean) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.name().equals(bean.getStatus().getCode())) {
        return orderMapper.updateOrder(bean);
      }
    }
    return null;
  }

  @Override
  public boolean deleteOrderById(Long id) {
    return orderMapper.deleteOrderById(id);
  }

  @Override
  public List<OrderBean> getUserOrdersByUsername(String username) {
    return orderMapper.getUserOrdersByUsername(username);
  }

  @Override
  public List<OrderBean> getUserOrdersById(Long id) {
    return orderMapper.getUserOrdersById(id);
  }

}
