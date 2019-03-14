package com.minimal.eshop.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.CartDto;
import com.minimal.eshop.dto.OrderDto;
import com.minimal.eshop.dto.OrderStatusDto;
import com.minimal.eshop.enums.OrderStatus;

@Service
public class CartServiceImpl implements CartService {

  @Inject
  private OrderService orderService;
  
  @Override
  public CartDto submitCart(CartDto dto) {
    for (OrderDto OrderDto : dto.getItems()) {
      OrderDto.setStatus(new OrderStatusDto().setCode(OrderStatus.PENDING.toString()).setTitle(OrderStatus.PENDING.getStatusTitle()));
      orderService.saveOrder(OrderDto);
    }
    return dto;
  }

}
