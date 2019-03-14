package com.minimal.eshop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Scope(value = "session")
@JsonIgnoreProperties(ignoreUnknown=true)
@SuppressWarnings("serial")
public class CartDto implements Serializable {

  //private Long userId;
  
  private List<OrderDto> items = new ArrayList<OrderDto>();

//  public Long getUserId() {
//    return userId;
//  }
//
//  public void setUserId(Long userId) {
//    this.userId = userId;
//  }
  
  public List<OrderDto> getItems() {
    return this.items;
  }

  public void clear() {
    this.items.clear();
  }
  
}
