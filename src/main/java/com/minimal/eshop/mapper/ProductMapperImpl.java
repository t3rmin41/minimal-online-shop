package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimal.eshop.bean.ProductBean;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongBeanFormatException;
import com.minimal.eshop.jpa.Product;
import com.minimal.eshop.repository.ProductRepository;

@Service
public class ProductMapperImpl implements ProductMapper, BeanValidator {

  @Autowired
  private ProductRepository productRepo;
  
  @Override
  public ProductBean getProductBeanByProduct(Product jpa) {
    return convertJpaToBean(jpa);
  }

  @Override
  public ProductBean getProductBeanById(Long id) {
    return convertJpaToBean(productRepo.getProductById(id));
  }

  @Override
  public List<ProductBean> getProductBeansByProducts(List<Product> jpas) {
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
    Product jpa = new Product();
    jpa.setPrice(bean.getPrice());
    jpa.setTitle(bean.getTitle());
    jpa.setShortDescription(bean.getShortDescription());
    jpa = productRepo.saveProduct(jpa);
    return convertJpaToBean(jpa);
  }

  @Override
  public ProductBean updateProduct(ProductBean bean) {
    validateBean(bean);
    Product jpa = productRepo.getProductById(bean.getId());
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
      throw new WrongBeanFormatException("Wrong user fields", errors);
    }
    return errors;
  }

  private ProductBean convertJpaToBean(Product jpa) {
    return new ProductBean().setId(jpa.getId()).setTitle(jpa.getTitle())
                            .setShortDescription(jpa.getShortDescription())
                            .setPrice(jpa.getPrice()).setDeleted(jpa.isDeleted());
  }
  
}
