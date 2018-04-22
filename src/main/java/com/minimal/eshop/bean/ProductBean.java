package com.minimal.eshop.bean;

import java.io.Serializable;

public class ProductBean implements Serializable {

  private Long id;
  private String title;
  private String shortDescription;
  private Double price;
  private boolean deleted;

  public Long getId() {
    return id;
  }
  public ProductBean setId(Long id) {
    this.id = id;
    return this;
  }
  public String getTitle() {
      return title;
  }
  public ProductBean setTitle(String title) {
      this.title = title;
      return this;
  }
  public String getShortDescription() {
    return shortDescription;
  }
  public ProductBean setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }
  public Double getPrice() {
      return price;
  }
  public ProductBean setPrice(Double price) {
      this.price = price;
      return this;
  }
  public boolean isDeleted() {
    return deleted;
  }
  public ProductBean setDeleted(boolean deleted) {
    this.deleted = deleted;
    return this;
  }
  
}
