package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.ProductBean;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongBeanFormatException;
import com.minimal.eshop.domain.ProductDao;
import com.minimal.eshop.repository.ProductRepository;

@Service
public class ProductMapperImpl implements ProductMapper, BeanValidator {

  @Inject
  private ProductRepository productRepo;
  
  @Override
  public ProductBean getProductBeanByProduct(ProductDao jpa) {
    return convertJpaToBean(jpa);
  }

  @Override
  public ProductBean getProductBeanById(Long id) {
    return convertJpaToBean(productRepo.getProductById(id));
  }

  @Override
  public List<ProductBean> getProductBeansByProducts(List<ProductDao> jpas) {
    List<ProductBean> beans = new LinkedList<ProductBean>();
    jpas.stream().forEach(p -> {
      beans.add(convertJpaToBean(p));
    });
    return beans;
  }

  @Override
  public List<ProductBean> getAllProducts() {
    return getProductBeansByProducts(productRepo.getAllProducts());
  }

  @Override
  public ProductBean saveProduct(ProductBean bean) {
    validateBean(bean);
    ProductDao jpa = new ProductDao();
    jpa.setPrice(bean.getPrice());
    jpa.setTitle(bean.getTitle());
    jpa.setShortDescription(bean.getShortDescription());
    jpa = productRepo.saveProduct(jpa);
    return convertJpaToBean(jpa);
  }

  @Override
  public ProductBean updateProduct(ProductBean bean) {
    validateBean(bean);
    ProductDao jpa = productRepo.getProductById(bean.getId());
    jpa.setPrice(bean.getPrice());
    return convertJpaToBean(productRepo.updateProduct(jpa));
  }

  @Override
  public boolean deleteProductById(Long id) {
    return productRepo.deleteProductById(id);
  }

  @Override
  public List<ErrorField> validateBean(Serializable bean) throws WrongBeanFormatException {
    List<ErrorField> errors = new LinkedList<ErrorField>();
    ProductBean productBean = (ProductBean) bean;
    if (productBean.getShortDescription().length() > 15) {
      errors.add(new ErrorField("shortDescription", "Short description maximum length is 15 chars"));
    }
    if (productBean.getPrice() < 0) {
      errors.add(new ErrorField("price", "Price cannot be negative"));
    }
    if (errors.size() > 0) {
      throw new WrongBeanFormatException("Wrong data entered", errors);
    }
    return errors;
  }

  private ProductBean convertJpaToBean(ProductDao jpa) {
    return new ProductBean().setId(jpa.getId()).setTitle(jpa.getTitle())
                            .setShortDescription(jpa.getShortDescription())
                            .setPrice(jpa.getPrice()).setDeleted(jpa.isDeleted());
  }
  
}
