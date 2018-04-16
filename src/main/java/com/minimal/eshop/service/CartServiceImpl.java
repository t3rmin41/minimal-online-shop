package com.minimal.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimal.eshop.bean.CartBean;
import com.minimal.eshop.bean.OrderBean;

@Service
public class CartServiceImpl implements CartService {

  @Autowired
  private OrderService orderService;
  
  @Override
  public CartBean submitCart(CartBean bean) {
    for (OrderBean orderBean : bean.getItems()) {
      orderService.saveOrder(orderBean);
    }
    return bean;
  }

}
