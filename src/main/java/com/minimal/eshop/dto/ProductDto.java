package com.minimal.eshop.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
public class ProductDto implements Serializable {

  private Long id;
  private String title;
  private String shortDescription;
  private Double price;
  private boolean deleted;

  public Long getId() {
    return id;
  }
  public ProductDto setId(Long id) {
    this.id = id;
    return this;
  }
  public String getTitle() {
      return title;
  }
  public ProductDto setTitle(String title) {
      this.title = title;
      return this;
  }
  public String getShortDescription() {
    return shortDescription;
  }
  public ProductDto setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }
  public Double getPrice() {
      return price;
  }
  public ProductDto setPrice(Double price) {
      this.price = price;
      return this;
  }
  public boolean isDeleted() {
    return deleted;
  }
  public ProductDto setDeleted(boolean deleted) {
    this.deleted = deleted;
    return this;
  }
  
}
