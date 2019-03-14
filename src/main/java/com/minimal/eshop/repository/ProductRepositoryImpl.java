package com.minimal.eshop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.minimal.eshop.enums.OrderStatus;
import com.minimal.eshop.domain.ProductDao;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  @Transactional
  public List<ProductDao> getAllProducts() {
    String q = "SELECT p FROM ProductDao p WHERE p.deleted = false ORDER BY p.id ASC";
    TypedQuery<ProductDao> query = em.createQuery(q, ProductDao.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public ProductDao getProductById(Long id) {
    String q = "SELECT p FROM ProductDao p WHERE p.id = :pid AND p.deleted = false ORDER BY p.id ASC";
    TypedQuery<ProductDao> query = em.createQuery(q, ProductDao.class);
    query.setParameter("pid", id);
    return query.getSingleResult();
  }

  @Override
  @Transactional
  public ProductDao saveProduct(ProductDao jpa) {
    return em.merge(jpa);
  }

  @Override
  @Transactional
  public ProductDao updateProduct(ProductDao jpa) {
    return em.merge(jpa);
  }

  @Override
  @Transactional
  public boolean deleteProductById(Long id) {
    String q = "UPDATE ProductDao p SET p.deleted = true WHERE p.id = :pid";
    Query query = em.createQuery(q);
    query.setParameter("pid", id);
    int productQueryStatus = query.executeUpdate();
    
    String orderQuery = "UPDATE OrderDao o SET o.status = :pstatus WHERE o.product = :pproduct";
    Query queryOrder = em.createQuery(orderQuery);
    queryOrder.setParameter("pproduct", getAnyProductById(id));
    queryOrder.setParameter("pstatus", OrderStatus.PRODUCT_DELETED.toString());
    int orderQueryStatus = queryOrder.executeUpdate();
    
    return productQueryStatus == orderQueryStatus;
  }
  
  @Transactional
  private ProductDao getAnyProductById(Long id) {
    String q = "SELECT p FROM ProductDao p WHERE p.id = :pid ORDER BY p.id ASC";
    TypedQuery<ProductDao> query = em.createQuery(q, ProductDao.class);
    query.setParameter("pid", id);
    return query.getSingleResult();
  }

}
