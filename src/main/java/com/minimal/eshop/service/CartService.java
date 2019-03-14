package com.minimal.eshop.service;

import com.minimal.eshop.dto.CartBean;

public interface CartService {

  CartBean submitCart(CartBean bean);
}
