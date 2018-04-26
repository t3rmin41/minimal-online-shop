package com.minimal.eshop.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minimal.eshop.enums.OrderStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBean implements Serializable {

  private Long id;
  private Long productId;
  private String productName;
  private String shortDescription;
  private Double price;
  private OrderStatusBean status;
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
  public String getShortDescription() {
    return shortDescription;
  }
  public OrderBean setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }
  public Double getPrice() {
      return price;
  }
  public OrderBean setPrice(Double price) {
      this.price = price;
      return this;
  }
  public OrderStatusBean getStatus() {
      return status;
  }
  public OrderBean setStatus(OrderStatusBean status) {
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
  
  @Override
  public boolean equals(Object o) {
    if (o instanceof OrderBean) {
      return ((OrderBean) o).productId == this.productId;
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    int hash = 3;
    hash = (int) (this.id * this.productId * hash);
    return hash;
  }
  
}
