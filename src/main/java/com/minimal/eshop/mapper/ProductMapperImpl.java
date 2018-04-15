package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.minimal.eshop.bean.ProductBean;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongBeanFormatException;
import com.minimal.eshop.jpa.Product;

@Service
public class ProductMapperImpl implements ProductMapper, BeanValidator {

  @Override
  public ProductBean getProductBeanByProduct(Product jpa) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProductBean getProductBeanById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ProductBean> getProductBeansByProducts(List<Product> jpas) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ProductBean> getAllProducts() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProductBean saveProduct(ProductBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProductBean updateProduct(ProductBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteProductById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<ErrorField> validateBean(Serializable bean) throws WrongBeanFormatException {
    // TODO Auto-generated method stub
    return null;
  }

}
