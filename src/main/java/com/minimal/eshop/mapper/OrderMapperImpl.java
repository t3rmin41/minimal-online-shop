package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.OrderBean;
import com.minimal.eshop.dto.OrderStatusBean;
import com.minimal.eshop.enums.OrderStatus;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongBeanFormatException;
import com.minimal.eshop.domain.OrderDao;
import com.minimal.eshop.domain.ProductDao;
import com.minimal.eshop.domain.UserDao;
import com.minimal.eshop.repository.OrderRepository;
import com.minimal.eshop.repository.ProductRepository;
import com.minimal.eshop.repository.UserRepository;

@Service
@SuppressWarnings("unused")
public class OrderMapperImpl implements OrderMapper, BeanValidator {

  @Inject
  private OrderRepository orderRepo;
  
  @Inject
  private ProductRepository productRepo;
  
  @Inject
  private UserRepository userRepo;
  
  @Override
  public List<OrderBean> getAllOrders() {
    return convertJpaListToBeans(orderRepo.getOrders());
  }

  @Override
  public OrderBean getOrderById(Long id) {
    return convertJpaToBean(orderRepo.getOrderById(id));
  }

  @Override
  public OrderBean saveOrder(OrderBean bean) {
    validateBean(bean);
    return convertJpaToBean(orderRepo.saveOrder(convertBeanToJpa(bean)));
  }

  @Override
  public OrderBean updateOrder(OrderBean bean) {
    validateBean(bean);
    OrderDao jpa = orderRepo.getOrderById(bean.getId());
    if (null != bean.getPrice()) {
      jpa.setPrice(bean.getPrice());
    }
    if (null != bean.getStatus()) {
      jpa.setStatus(bean.getStatus().getCode());
    }
    return convertJpaToBean(orderRepo.updateOrder(jpa));
  }

  @Override
  public boolean deleteOrderById(Long id) {
    return orderRepo.deleteOrderById(id);
  }

  @Override
  public OrderBean convertOrderToBeanById(Long id) {
    OrderDao jpa = orderRepo.getOrderById(id);
    if (null != jpa) {
        return convertJpaToBean(jpa);
    } else {
        return null;
    }
  }

  @Override
  public List<OrderBean> getUserOrdersByUsername(String email) {
    return convertJpaListToBeans(orderRepo.getUserOrdersByEmail(email));
  }

  @Override
  public List<OrderBean> getUserOrdersById(Long id) {
    return convertJpaListToBeans(orderRepo.getUserOrdersById(id));
  }
  
  @Override
  public OrderStatusBean getTypeBeanByName(String name) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.name().equals(name)) {
        return new OrderStatusBean().setCode(status.name()).setTitle(status.getStatusTitle());
      }
    }
    return null;
  }
  
  @Override
  public List<ErrorField> validateBean(Serializable bean) throws WrongBeanFormatException {
    List<ErrorField> errors = new LinkedList<ErrorField>();
    OrderBean orderBean = (OrderBean) bean;
    return errors;
  }

  @Override
  public List<OrderBean> convertJpaListToBeans(List<OrderDao> jpas) {
    List<OrderBean> beans = new ArrayList<OrderBean>();
    jpas.stream().forEach(order -> {
      beans.add(convertJpaToBean(order));
    });
    return beans;
  }
  
  private OrderBean convertJpaToBean(OrderDao jpa) {
    return new OrderBean().setId(jpa.getId()).setProductId(jpa.getProduct().getId())
        .setProductName(jpa.getTitle()).setShortDescription(jpa.getShortDescription())
        .setPrice(jpa.getPrice()).setOrderedBy(jpa.getOrderedBy()
        .getEmail())
        .setStatus(getTypeBeanByName(jpa.getStatus()))
        .setCreated(jpa.getCreated()).setUpdated(jpa.getUpdated());
  }

  private OrderDao convertBeanToJpa(OrderBean bean) {
    OrderDao jpa = new OrderDao();
    jpa.setId(bean.getId());
    ProductDao product = productRepo.getProductById(bean.getProductId());
    UserDao user = userRepo.getUserByEmail(bean.getOrderedBy());
    jpa.setProduct(product);
    jpa.setPrice(product.getPrice());
    jpa.setTitle(product.getTitle());
    jpa.setShortDescription(bean.getShortDescription());
    jpa.setStatus(bean.getStatus().getCode());
    jpa.setOrderedBy(user);
    jpa.setCreated(bean.getCreated());
    jpa.setUpdated(bean.getUpdated());
    return jpa;
  }



}
