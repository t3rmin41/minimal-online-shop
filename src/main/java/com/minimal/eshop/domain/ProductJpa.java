package com.minimal.eshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class ProductJpa {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column(name = "TITLE")
  private String title;
  @Column(name = "SHORT_DESCRIPTION")
  private String shortDescription;
  @Column(name = "PRICE")
  private Double price;
  @Column(name = "DELETED")
  private boolean deleted = false;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
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
  public boolean isDeleted() {
    return deleted;
  }
  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

}
