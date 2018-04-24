package com.minimal.eshop.enums;

public enum OrderStatus {

  PENDING("Pending"),
  IN_PROGRESS("In progress"),
  DELIVERED("Delivered"),
  PRODUCT_DELETED("Product deleted"),
  COMPLETED("Completed");

  private String name;
  private String statusTitle;

  private OrderStatus(String status) {
    this.statusTitle = status;
  }

  public String getName() {
    return this.name;
  }
  
  public String getStatusTitle() {
    return this.statusTitle;
  }

  public static String getOrderStatusTitle(OrderStatus status) {
    for (OrderStatus current : OrderStatus.values()) {
      if (status.equals(current)) {
        return current.statusTitle;
      }
    }
    return null;
  }

  public static OrderStatus getOrderStatusByName(String code) {
    for (OrderStatus current : OrderStatus.values()) {
      if (current.name.equals(code)) {
        return current;
      }
    }
    return null;
  }
  
}
