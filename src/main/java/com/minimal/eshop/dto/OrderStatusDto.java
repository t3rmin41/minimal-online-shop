package com.minimal.eshop.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
public class OrderStatusDto implements Serializable {

  private String code;
  private String title;
  
  public String getCode() {
    return code;
  }
  public OrderStatusDto setCode(String code) {
    this.code = code;
    return this;
  }
  public String getTitle() {
    return title;
  }
  public OrderStatusDto setTitle(String title) {
    this.title = title;
    return this;
  }
  
}
