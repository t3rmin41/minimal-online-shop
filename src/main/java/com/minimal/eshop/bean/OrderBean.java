package com.minimal.eshop.bean;

import java.io.Serializable;
import java.util.Date;

public class OrderBean implements Serializable {

  private Long id;
  private Long productId;
  private String productName;
  private Double price;
  private String status;
  private String orderedBy;
  private Date created;
  private Date updated;

  public Long getId() {
      return id;
  }
  public OrderBean setId(Long id) {
      this.id = id;
      return this;
  }
  public Long getProductId() {
      return productId;
  }
  public OrderBean setProductId(Long productId) {
      this.productId = productId;
      return this;
  }
  public String getProductName() {
      return productName;
  }
  public OrderBean setProductName(String productName) {
      this.productName = productName;
      return this;
  }
  public Double getPrice() {
      return price;
  }
  public OrderBean setPrice(Double price) {
      this.price = price;
      return this;
  }
  public String getStatus() {
      return status;
  }
  public OrderBean setStatus(String status) {
      this.status = status;
      return this;
  }
  public String getOrderedBy() {
    return orderedBy;
  }
  public OrderBean setOrderedBy(String orderedBy) {
    this.orderedBy = orderedBy;
    return this;
  }
  public Date getCreated() {
      return created;
  }
  public OrderBean setCreated(Date created) {
      this.created = created;
      return this;
  }
  public Date getUpdated() {
      return updated;
  }
  public OrderBean setUpdated(Date updated) {
      this.updated = updated;
      return this;
  }
  
}
