package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.OrderDto;
import com.minimal.eshop.dto.OrderStatusDto;
import com.minimal.eshop.enums.OrderStatus;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongDtoFormatException;
import com.minimal.eshop.domain.OrderJpa;
import com.minimal.eshop.domain.ProductJpa;
import com.minimal.eshop.domain.UserJpa;
import com.minimal.eshop.repository.OrderRepository;
import com.minimal.eshop.repository.ProductRepository;
import com.minimal.eshop.repository.UserRepository;

@Service
@SuppressWarnings("unused")
public class OrderMapperImpl implements OrderMapper, DtoValidator {

  @Inject
  private OrderRepository orderRepo;
  
  @Inject
  private ProductRepository productRepo;
  
  @Inject
  private UserRepository userRepo;
  
  @Override
  public List<OrderDto> getAllOrders() {
    return convertJpaListTodtos(orderRepo.getOrders());
  }

  @Override
  public OrderDto getOrderById(Long id) {
    return convertJpaTodto(orderRepo.getOrderById(id));
  }

  @Override
  public OrderDto saveOrder(OrderDto dto) {
    validatedto(dto);
    return convertJpaTodto(orderRepo.saveOrder(convertdtoToJpa(dto)));
  }

  @Override
  public OrderDto updateOrder(OrderDto dto) {
    validatedto(dto);
    OrderJpa jpa = orderRepo.getOrderById(dto.getId());
    if (null != dto.getPrice()) {
      jpa.setPrice(dto.getPrice());
    }
    if (null != dto.getStatus()) {
      jpa.setStatus(dto.getStatus().getCode());
    }
    return convertJpaTodto(orderRepo.updateOrder(jpa));
  }

  @Override
  public boolean deleteOrderById(Long id) {
    return orderRepo.deleteOrderById(id);
  }

  @Override
  public OrderDto convertOrderTodtoById(Long id) {
    OrderJpa jpa = orderRepo.getOrderById(id);
    if (null != jpa) {
        return convertJpaTodto(jpa);
    } else {
        return null;
    }
  }

  @Override
  public List<OrderDto> getUserOrdersByUsername(String email) {
    return convertJpaListTodtos(orderRepo.getUserOrdersByEmail(email));
  }

  @Override
  public List<OrderDto> getUserOrdersById(Long id) {
    return convertJpaListTodtos(orderRepo.getUserOrdersById(id));
  }
  
  @Override
  public OrderStatusDto getTypedtoByName(String name) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.name().equals(name)) {
        return new OrderStatusDto().setCode(status.name()).setTitle(status.getStatusTitle());
      }
    }
    return null;
  }
  
  @Override
  public List<ErrorField> validatedto(Serializable dto) throws WrongDtoFormatException {
    List<ErrorField> errors = new LinkedList<ErrorField>();
    OrderDto OrderDto = (OrderDto) dto;
    return errors;
  }

  @Override
  public List<OrderDto> convertJpaListTodtos(List<OrderJpa> jpas) {
    List<OrderDto> dtos = new ArrayList<OrderDto>();
    jpas.stream().forEach(order -> {
      dtos.add(convertJpaTodto(order));
    });
    return dtos;
  }
  
  private OrderDto convertJpaTodto(OrderJpa jpa) {
    return new OrderDto().setId(jpa.getId()).setProductId(jpa.getProduct().getId())
        .setProductName(jpa.getTitle()).setShortDescription(jpa.getShortDescription())
        .setPrice(jpa.getPrice()).setOrderedBy(jpa.getOrderedBy()
        .getEmail())
        .setStatus(getTypedtoByName(jpa.getStatus()))
        .setCreated(jpa.getCreated()).setUpdated(jpa.getUpdated());
  }

  private OrderJpa convertdtoToJpa(OrderDto dto) {
    OrderJpa jpa = new OrderJpa();
    jpa.setId(dto.getId());
    ProductJpa product = productRepo.getProductById(dto.getProductId());
    UserJpa user = userRepo.getUserByEmail(dto.getOrderedBy());
    jpa.setProduct(product);
    jpa.setPrice(product.getPrice());
    jpa.setTitle(product.getTitle());
    jpa.setShortDescription(dto.getShortDescription());
    jpa.setStatus(dto.getStatus().getCode());
    jpa.setOrderedBy(user);
    jpa.setCreated(dto.getCreated());
    jpa.setUpdated(dto.getUpdated());
    return jpa;
  }



}
