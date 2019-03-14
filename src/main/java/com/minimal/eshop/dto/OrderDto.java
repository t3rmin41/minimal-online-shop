package com.minimal.eshop.dto;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
public class OrderDto implements Serializable {

  private Long id;
  private Long productId;
  private String productName;
  private String shortDescription;
  private Double price;
  private OrderStatusDto status;
  private String orderedBy;
  private Date created;
  private Date updated;

  public Long getId() {
      return id;
  }
  public OrderDto setId(Long id) {
      this.id = id;
      return this;
  }
  public Long getProductId() {
      return productId;
  }
  public OrderDto setProductId(Long productId) {
      this.productId = productId;
      return this;
  }
  public String getProductName() {
      return productName;
  }
  public OrderDto setProductName(String productName) {
      this.productName = productName;
      return this;
  }
  public String getShortDescription() {
    return shortDescription;
  }
  public OrderDto setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }
  public Double getPrice() {
      return price;
  }
  public OrderDto setPrice(Double price) {
      this.price = price;
      return this;
  }
  public OrderStatusDto getStatus() {
      return status;
  }
  public OrderDto setStatus(OrderStatusDto status) {
      this.status = status;
      return this;
  }
  public String getOrderedBy() {
    return orderedBy;
  }
  public OrderDto setOrderedBy(String orderedBy) {
    this.orderedBy = orderedBy;
    return this;
  }
  public Date getCreated() {
      return created;
  }
  public OrderDto setCreated(Date created) {
      this.created = created;
      return this;
  }
  public Date getUpdated() {
      return updated;
  }
  public OrderDto setUpdated(Date updated) {
      this.updated = updated;
      return this;
  }
  
  @Override
  public boolean equals(Object o) {
    if (o instanceof OrderDto) {
      return ((OrderDto) o).productId == this.productId;
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
