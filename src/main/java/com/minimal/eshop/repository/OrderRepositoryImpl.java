package com.minimal.eshop.repository;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.minimal.eshop.jpa.OrderDao;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

  private static Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
  
  @PersistenceContext
  private EntityManager em;
  
  @Override
  @Transactional
  public OrderDao saveOrder(OrderDao order) {
    Calendar cal = Calendar.getInstance();
    //cal.set(Calendar.HOUR_OF_DAY, 0);
    //cal.set(Calendar.MINUTE, 0);
    //cal.set(Calendar.SECOND, 0);
    order.setCreated(cal.getTime());
    return em.merge(order);
  }

  @Override
  @Transactional
  public List<OrderDao> getOrders() {
    String q = "SELECT o FROM OrderDao o ORDER BY o.id ASC";
    TypedQuery<OrderDao> query = em.createQuery(q, OrderDao.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public List<OrderDao> getUserOrdersByEmail(String email) {
    String q = "SELECT o FROM OrderDao o WHERE o.orderedBy = (SELECT u FROM UserDao u WHERE u.email = :pemail) ORDER BY o.id ASC";
    TypedQuery<OrderDao> query = em.createQuery(q, OrderDao.class);
    query.setParameter("pemail", email);
    return query.getResultList();
  }

  @Override
  @Transactional
  public List<OrderDao> getUserOrdersById(Long userId) {
    String q = "SELECT o FROM OrderDao o WHERE o.orderedBy = (SELECT u FROM UserDao u WHERE u.id = :pid) ORDER BY o.id ASC";
    TypedQuery<OrderDao> query = em.createQuery(q, OrderDao.class);
    query.setParameter("pid", userId);
    return query.getResultList();
  }
  
  @Override
  @Transactional
  public OrderDao getOrderById(Long id) {
    String q = "SELECT o FROM OrderDao o WHERE o.id = :pid ORDER BY o.id ASC";
    TypedQuery<OrderDao> query = em.createQuery(q, OrderDao.class);
    query.setParameter("pid", id);
    return query.getSingleResult();
  }

  @Override
  @Transactional
  public OrderDao updateOrder(OrderDao order) {
    Calendar cal = Calendar.getInstance();
    //cal.set(Calendar.HOUR, 0);
    //cal.set(Calendar.MINUTE, 0);
    //cal.set(Calendar.SECOND, 0);
    order.setUpdated(cal.getTime());
    return em.merge(order);
  }

  @Override
  @Transactional
  public boolean deleteOrderById(Long id) {
    OrderDao jpa = this.getOrderById(id);
    boolean result = false;
    try {
        em.remove(jpa);
        result = true;
    } catch (IllegalArgumentException e) {
        logger.error(e.getMessage());
    } catch (TransactionRequiredException e) {
        logger.error(e.getMessage());
    }
    return result;
  }

}
