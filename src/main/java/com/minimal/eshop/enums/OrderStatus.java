package com.minimal.eshop.enums;

public enum OrderStatus {

  PENDING("Pending"),
  IN_PROGRESS("In progress"),
  DELIVERED("Delivered"),
  PRODUCT_DELETED("Product deleted"),
  COMPLETED("Completed");

  private String code;

  private OrderStatus(String status) {
    this.code = status;
  }

  public String getCode() {
    return this.code;
  }

  public static String getOrderStatusName(OrderStatus status) {
    for (OrderStatus current : OrderStatus.values()) {
      if (status.equals(current)) {
        return current.code;
      }
    }
    return null;
  }

  public static OrderStatus getOrderStatusByName(String statusName) {
    for (OrderStatus current : OrderStatus.values()) {
      if (current.code.equals(statusName)) {
        return current;
      }
    }
    return null;
  }
  
}
