package com.minimal.eshop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.minimal.eshop.jpa.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  @Transactional
  public List<Product> getAllProducts() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public Product getProductById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public Product saveProduct(Product jpa) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public Product updateProduct(Product jpa) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public boolean deleteProductById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

}
