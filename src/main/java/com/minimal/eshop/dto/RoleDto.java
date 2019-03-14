package com.minimal.eshop.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
public class RoleDto implements Serializable {

  private String code;
  private String title;

  public RoleDto setCode(String code) {
    this.code = code;
    return this;
  }
  
  public RoleDto setTitle(String title) {
    this.title = title;
    return this;
  }
  
  public String getCode() {
    return code;
  }

  public String getTitle() {
    return title;
  }
  
}
