package com.minimal.eshop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Scope(value = "session")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CartBean implements Serializable {

  //private Long userId;
  
  private List<OrderBean> items = new ArrayList<OrderBean>();

//  public Long getUserId() {
//    return userId;
//  }
//
//  public void setUserId(Long userId) {
//    this.userId = userId;
//  }
  
  public List<OrderBean> getItems() {
    return this.items;
  }

  public void clear() {
    this.items.clear();
  }
  
}
