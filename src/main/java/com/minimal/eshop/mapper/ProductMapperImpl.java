package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.ArrayList;
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
    List<ProductBean> beans = new ArrayList<ProductBean>();
    for(Product jpa : jpas) {
        beans.add(convertJpaToBean(jpa));
    }
    return beans;
  }

  @Override
  public List<ProductBean> getAllProducts() {
    return getProductBeansByProducts(productRepo.getAllProducts());
  }

  @Override
  public ProductBean saveProduct(ProductBean bean) {
    Product jpa = new Product();
    jpa.setPrice(bean.getPrice());
    jpa.setTitle(bean.getTitle());
    jpa = productRepo.saveProduct(jpa);
    return convertJpaToBean(jpa);
  }

  @Override
  public ProductBean updateProduct(ProductBean bean) {
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
    // TODO Auto-generated method stub
    return null;
  }

  private ProductBean convertJpaToBean(Product jpa) {
    return new ProductBean().setId(jpa.getId()).setTitle(jpa.getTitle()).setPrice(jpa.getPrice()).setDeleted(jpa.isDeleted());
}
  
}
