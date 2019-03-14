package com.minimal.eshop.repository;

import java.util.List;
import java.util.Set;
import com.minimal.eshop.domain.RoleDao;
import com.minimal.eshop.domain.UserDao;

public interface UserRepository {

  UserDao getUserByEmail(String email);
  
  UserDao getUserByEmailAndPassword(String username, String password);
  
  UserDao getUserById(Long id);
  
  UserDao saveUser(UserDao jpa);
  
  void assignRoles(Set<RoleDao> roles);
  
  void removeRoles(Set<RoleDao> roles);
  
  List<UserDao> getAllUsers();
  
  boolean deleteUser(UserDao jpa);
  
  UserDao updateUser(UserDao jpa, boolean isPasswordChanged);
  
  Set<RoleDao> getUserRolesByNames(UserDao jpa, Set<String> rolesNames);
  
  Set<RoleDao> getUserRolesByEmail(String email);
  
}
