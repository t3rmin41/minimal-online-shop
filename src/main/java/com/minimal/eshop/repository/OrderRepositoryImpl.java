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
import com.minimal.eshop.jpa.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

  private static Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
  
  @PersistenceContext
  private EntityManager em;
  
  @Override
  @Transactional
  public Order saveOrder(Order order) {
    Calendar cal = Calendar.getInstance();
    //cal.set(Calendar.HOUR_OF_DAY, 0);
    //cal.set(Calendar.MINUTE, 0);
    //cal.set(Calendar.SECOND, 0);
    order.setCreated(cal.getTime());
    return em.merge(order);
  }

  @Override
  @Transactional
  public List<Order> getOrders() {
    String q = "SELECT o FROM Order o";
    TypedQuery<Order> query = em.createQuery(q, Order.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public List<Order> getUserOrdersByEmail(String email) {
    String q = "SELECT o FROM Order o WHERE o.orderedBy = (SELECT u FROM User u WHERE u.email = :pemail)";
    TypedQuery<Order> query = em.createQuery(q, Order.class);
    query.setParameter("pemail", email);
    return query.getResultList();
  }

  @Override
  @Transactional
  public List<Order> getUserOrdersById(Long userId) {
    String q = "SELECT o FROM Order o WHERE o.orderedBy = (SELECT u FROM User u WHERE u.id = :pid)";
    TypedQuery<Order> query = em.createQuery(q, Order.class);
    query.setParameter("pid", userId);
    return query.getResultList();
  }
  
  @Override
  @Transactional
  public Order getOrderById(Long id) {
    String q = "SELECT o FROM Order o WHERE o.id = :pid";
    TypedQuery<Order> query = em.createQuery(q, Order.class);
    query.setParameter("pid", id);
    return query.getSingleResult();
  }

  @Override
  @Transactional
  public Order updateOrder(Order order) {
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
    Order jpa = this.getOrderById(id);
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
