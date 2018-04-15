package com.minimal.eshop.repository;

import java.util.List;
import java.util.Set;
import com.minimal.eshop.jpa.Role;
import com.minimal.eshop.jpa.User;

public interface UserRepository {

  User getUserByEmail(String email);
  
  User getUserByEmailAndPassword(String username, String password);
  
  User getUserById(Long id);
  
  User saveUser(User jpa);
  
  void assignRoles(Set<Role> roles);
  
  void removeRoles(Set<Role> roles);
  
  List<User> getAllUsers();
  
  boolean deleteUser(User jpa);
  
  User updateUser(User jpa, boolean isPasswordChanged);
  
  Set<Role> getUserRolesByNames(User jpa, Set<String> rolesNames);
  
  Set<Role> getUserRolesByEmail(String email);
  
}
