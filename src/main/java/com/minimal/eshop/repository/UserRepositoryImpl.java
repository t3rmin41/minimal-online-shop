package com.minimal.eshop.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.minimal.eshop.domain.RoleJpa;
import com.minimal.eshop.domain.UserJpa;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private static Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
  
  @PersistenceContext
  private EntityManager em;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Override
  @Transactional
  public UserJpa getUserByEmail(String email) {
    String q = "SELECT u FROM UserJpa u LEFT JOIN FETCH u.roles WHERE u.email = :pemail ORDER BY u.id ASC";
    TypedQuery<UserJpa> query = em.createQuery(q, UserJpa.class);
    query.setParameter("pemail", email);
    return query.getSingleResult();
  }

  @Override
  @Transactional
  public UserJpa getUserByEmailAndPassword(String email, String password) {
    String q = "SELECT u FROM UserJpa u WHERE u.email = :pemail AND u.password = :ppassword ORDER BY u.id ASC";
    TypedQuery<UserJpa> query = em.createQuery(q, UserJpa.class);
    query.setParameter("pemail", email);
    query.setParameter("ppassword", password);
    List<UserJpa> users = query.getResultList();
    if (1 == users.size()) {
        return users.get(0);
    } else {
        return null;
    }
  }

  @Override
  @Transactional
  public UserJpa getUserById(Long id) {
    String q = "SELECT u FROM UserJpa u LEFT JOIN FETCH u.roles WHERE u.id = :pid ORDER BY u.id ASC";
    TypedQuery<UserJpa> query = em.createQuery(q, UserJpa.class);
    query.setParameter("pid", id);
    return query.getSingleResult();
  }

  @Override
  @Transactional
  public UserJpa saveUser(UserJpa jpa) {
    jpa.setPassword(passwordEncoder.encode(jpa.getPassword()));
    return em.merge(jpa);
  }

  @Override
  @Transactional
  public void assignRoles(Set<RoleJpa> roles) {
    for (RoleJpa role : roles) {
      em.merge(role);
    }
  }

  @Override
  @Transactional
  public void removeRoles(Set<RoleJpa> roles) {
    for (RoleJpa role : roles) {
      String q = "DELETE FROM RoleJpa r WHERE r.role = :role AND r.user = :user AND r.active = 1";
      Query query = em.createQuery(q);
      query.setParameter("role", role.getRole());
      query.setParameter("user", role.getUser());
      query.executeUpdate();
    }
  }

  @Override
  @Transactional
  public List<UserJpa> getAllUsers() {
    String q = "SELECT u FROM UserJpa u WHERE u.id IS NOT NULL ORDER BY u.id ASC";
    TypedQuery<UserJpa> query = em.createQuery(q, UserJpa.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public boolean deleteUser(UserJpa jpa) {
    boolean result = false;
    try {
      em.remove(jpa);
      result = true;
    } catch (IllegalArgumentException | TransactionRequiredException e) {
      log.error(e.getMessage());
    }
    return result;
  }

  @Override
  @Transactional
  public UserJpa updateUser(UserJpa jpa, boolean isPasswordChanged) {
    if (isPasswordChanged) {
      jpa.setPassword(passwordEncoder.encode(jpa.getPassword()));
    } else {
      jpa.setPassword(jpa.getPassword());
    }
    UserJpa updated = em.merge(jpa);
    return getUserById(updated.getId());
  }

  @Override
  @Transactional
  public Set<RoleJpa> getUserRolesByNames(UserJpa jpa, Set<String> rolesNames) {
    String q = "SELECT r FROM RoleJpa r WHERE r.role IN :roles AND r.user = :user AND r.active = 1 ORDER BY r.id ASC";
    TypedQuery<RoleJpa> query = em.createQuery(q, RoleJpa.class);
    query.setParameter("roles", rolesNames);
    query.setParameter("user", jpa);
    List<RoleJpa> resultList = query.getResultList();
    Set<RoleJpa> roles = new HashSet<RoleJpa>();
    roles.addAll(resultList);
    return roles;
  }

  @Override
  @Transactional
  public Set<RoleJpa> getUserRolesByEmail(String email) {
    String q = "SELECT r FROM RoleJpa r WHERE r.user.email = :pemail AND r.active = 1 ORDER BY r.id ASC";
    TypedQuery<RoleJpa> query = em.createQuery(q, RoleJpa.class);
    query.setParameter("pemail", email);
    List<RoleJpa> resultList = query.getResultList();
    Set<RoleJpa> roles = new HashSet<RoleJpa>();
    roles.addAll(resultList);
    return roles;
  }

}
