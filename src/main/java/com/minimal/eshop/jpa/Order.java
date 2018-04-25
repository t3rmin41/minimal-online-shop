package com.minimal.eshop.jpa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRODUCT_ID", nullable = false)
  private Product product;
  @Column(name = "TITLE")
  private String title;
  @Column(name = "SHORT_DESCRIPTION")
  private String shortDescription;
  @Column(name = "PRICE")
  private Double price;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ORDERED_BY", nullable = false)
  private User orderedBy;
  @Column(name = "STATUS")
  private String status;
  @Column(name = "CREATED")
  private Date created;
  @Column(name = "UPDATED")
  private Date updated;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getShortDescription() {
    return shortDescription;
  }
  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }
  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    this.price = price;
  }
  public User getOrderedBy() {
    return orderedBy;
  }
  public void setOrderedBy(User orderedBy) {
    this.orderedBy = orderedBy;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public Date getCreated() {
    return created;
  }
  public void setCreated(Date created) {
    this.created = created;
  }
  public Date getUpdated() {
    return updated;
  }
  public void setUpdated(Date updated) {
    this.updated = updated;
  }

}
