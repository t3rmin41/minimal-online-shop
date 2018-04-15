package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.minimal.eshop.bean.OrderBean;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongBeanFormatException;

@Service
public class OrderMapperImpl implements OrderMapper, BeanValidator {

  @Override
  public List<OrderBean> getAllOrders() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OrderBean getOrderById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OrderBean saveOrder(OrderBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OrderBean updateOrder(OrderBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteOrderById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public OrderBean convertOrderToBeanById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<OrderBean> getUserOrdersByUsername(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ErrorField> validateBean(Serializable bean) throws WrongBeanFormatException {
    // TODO Auto-generated method stub
    return null;
  }

}
