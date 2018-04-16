package com.minimal.eshop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.minimal.eshop.enums.OrderStatus;
import com.minimal.eshop.jpa.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  @Transactional
  public List<Product> getAllProducts() {
    String q = "SELECT p FROM Product p WHERE p.deleted = false";
    TypedQuery<Product> query = em.createQuery(q, Product.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public Product getProductById(Long id) {
    String q = "SELECT p FROM Product p WHERE p.id = :pid AND p.deleted = false";
    TypedQuery<Product> query = em.createQuery(q, Product.class);
    query.setParameter("pid", id);
    return query.getSingleResult();
  }

  @Override
  @Transactional
  public Product saveProduct(Product jpa) {
    return em.merge(jpa);
  }

  @Override
  @Transactional
  public Product updateProduct(Product jpa) {
    return em.merge(jpa);
  }

  @Override
  @Transactional
  public boolean deleteProductById(Long id) {
    String q = "UPDATE Product p SET p.deleted = true WHERE p.id = :pid";
    Query query = em.createQuery(q);
    query.setParameter("pid", id);
    int productQueryStatus = query.executeUpdate();
    
    String orderQuery = "UPDATE Order o SET o.status = :pstatus WHERE o.product = :pproduct";
    Query queryOrder = em.createQuery(orderQuery);
    queryOrder.setParameter("pproduct", getAnyProductById(id));
    queryOrder.setParameter("pstatus", OrderStatus.PRODUCT_DELETED.toString());
    int orderQueryStatus = queryOrder.executeUpdate();
    
    return productQueryStatus == orderQueryStatus;
  }
  
  @Transactional
  private Product getAnyProductById(Long id) {
    String q = "SELECT p FROM Product p WHERE p.id = :pid";
    TypedQuery<Product> query = em.createQuery(q, Product.class);
    query.setParameter("pid", id);
    return query.getSingleResult();
  }

}
