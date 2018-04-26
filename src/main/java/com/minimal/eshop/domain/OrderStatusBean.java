package com.minimal.eshop.domain;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderStatusBean implements Serializable {

  private String code;
  private String title;
  
  public String getCode() {
    return code;
  }
  public OrderStatusBean setCode(String code) {
    this.code = code;
    return this;
  }
  public String getTitle() {
    return title;
  }
  public OrderStatusBean setTitle(String title) {
    this.title = title;
    return this;
  }
  
}
