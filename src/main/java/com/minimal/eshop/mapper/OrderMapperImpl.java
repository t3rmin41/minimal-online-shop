package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimal.eshop.bean.OrderBean;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongBeanFormatException;
import com.minimal.eshop.jpa.Order;
import com.minimal.eshop.jpa.Product;
import com.minimal.eshop.jpa.User;
import com.minimal.eshop.repository.OrderRepository;
import com.minimal.eshop.repository.ProductRepository;
import com.minimal.eshop.repository.UserRepository;

@Service
public class OrderMapperImpl implements OrderMapper, BeanValidator {

  @Autowired
  private OrderRepository orderRepo;
  
  @Autowired
  private ProductRepository productRepo;
  
  @Autowired
  private UserRepository userRepo;
  
  @Override
  public List<OrderBean> getAllOrders() {
    List<Order> orders = orderRepo.getOrders();
    List<OrderBean> beans = new ArrayList<OrderBean>();
    for (Order jpa : orders) {
        beans.add(convertJpaToBean(jpa));
    }
    return beans;
  }

  @Override
  public OrderBean getOrderById(Long id) {
    return convertJpaToBean(orderRepo.getOrderById(id));
  }

  @Override
  public OrderBean saveOrder(OrderBean bean) {
    return convertJpaToBean(orderRepo.saveOrder(convertBeanToJpa(bean)));
  }

  @Override
  public OrderBean updateOrder(OrderBean bean) {
    Order jpa = orderRepo.getOrderById(bean.getId());
    if (null != bean.getPrice()) {
        jpa.setPrice(bean.getPrice());
    }
    if (null != bean.getStatus()) {
        jpa.setStatus(bean.getStatus());
    }
    return convertJpaToBean(orderRepo.updateOrder(jpa));
  }

  @Override
  public boolean deleteOrderById(Long id) {
    return orderRepo.deleteOrderById(id);
  }

  @Override
  public OrderBean convertOrderToBeanById(Long id) {
    Order jpa = orderRepo.getOrderById(id);
    if (null != jpa) {
        return convertJpaToBean(jpa);
    } else {
        return null;
    }
  }

  @Override
  public List<OrderBean> getUserOrdersByUsername(String email) {
    List<Order> orders = orderRepo.getUserOrdersByEmail(email);
    List<OrderBean> beans = new ArrayList<OrderBean>();
    for (Order jpa : orders) {
        beans.add(convertJpaToBean(jpa));
    }
    return beans;
  }

  @Override
  public List<OrderBean> getUserOrdersById(Long id) {
    List<Order> orders = orderRepo.getUserOrdersById(id);
    List<OrderBean> beans = new ArrayList<OrderBean>();
    for (Order jpa : orders) {
        beans.add(convertJpaToBean(jpa));
    }
    return beans;
  }
  
  @Override
  public List<ErrorField> validateBean(Serializable bean) throws WrongBeanFormatException {
    // TODO Auto-generated method stub
    return null;
  }

  private OrderBean convertJpaToBean(Order jpa) {
    return new OrderBean().setId(jpa.getId()).setProductId(jpa.getProduct().getId())
        .setProductName(jpa.getTitle()).setShortDescription(jpa.getShortDescription())
        .setPrice(jpa.getPrice()).setOrderedBy(jpa.getOrderedBy()
        .getEmail()).setStatus(jpa.getStatus())
        .setCreated(jpa.getCreated()).setUpdated(jpa.getUpdated());
  }

  private Order convertBeanToJpa(OrderBean bean) {
    Order jpa = new Order();
    jpa.setId(bean.getId());
    Product product = productRepo.getProductById(bean.getProductId());
    User user = userRepo.getUserByEmail(bean.getOrderedBy());
    jpa.setProduct(product);
    jpa.setPrice(product.getPrice());
    jpa.setTitle(product.getTitle());
    jpa.setShortDescription(bean.getShortDescription());
    jpa.setStatus(bean.getStatus());
    jpa.setOrderedBy(user);
    jpa.setCreated(bean.getCreated());
    jpa.setUpdated(bean.getUpdated());
    return jpa;
  }

}
