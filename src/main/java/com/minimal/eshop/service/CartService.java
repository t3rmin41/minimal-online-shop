package com.minimal.eshop.service;

import com.minimal.eshop.dto.CartDto;

public interface CartService {

  CartDto submitCart(CartDto dto);
}
