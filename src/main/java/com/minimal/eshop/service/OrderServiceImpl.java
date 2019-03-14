package com.minimal.eshop.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.OrderDto;
import com.minimal.eshop.enums.OrderStatus;
import com.minimal.eshop.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

  @Inject
  private OrderMapper orderMapper;
  
  @Override
  public List<OrderDto> getAllOrders() {
    return orderMapper.getAllOrders();
  }

  @Override
  public OrderDto getOrderById(Long id) {
    return orderMapper.getOrderById(id);
  }

  @Override
  public OrderDto saveOrder(OrderDto dto) {
    return orderMapper.saveOrder(dto);
  }

  @Override
  public OrderDto updateOrder(OrderDto dto) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.name().equals(dto.getStatus().getCode())) {
        return orderMapper.updateOrder(dto);
      }
    }
    return null;
  }

  @Override
  public boolean deleteOrderById(Long id) {
    return orderMapper.deleteOrderById(id);
  }

  @Override
  public List<OrderDto> getUserOrdersByUsername(String username) {
    return orderMapper.getUserOrdersByUsername(username);
  }

  @Override
  public List<OrderDto> getUserOrdersById(Long id) {
    return orderMapper.getUserOrdersById(id);
  }

}
