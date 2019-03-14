package com.minimal.eshop.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.CartBean;
import com.minimal.eshop.dto.OrderBean;
import com.minimal.eshop.dto.OrderStatusBean;
import com.minimal.eshop.enums.OrderStatus;

@Service
public class CartServiceImpl implements CartService {

  @Inject
  private OrderService orderService;
  
  @Override
  public CartBean submitCart(CartBean bean) {
    for (OrderBean orderBean : bean.getItems()) {
      orderBean.setStatus(new OrderStatusBean().setCode(OrderStatus.PENDING.toString()).setTitle(OrderStatus.PENDING.getStatusTitle()));
      orderService.saveOrder(orderBean);
    }
    return bean;
  }

}
