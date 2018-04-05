package com.minimal.eshop.repository;

import java.util.List;
import java.util.Set;
import com.minimal.eshop.jpa.Role;
import com.minimal.eshop.jpa.User;

public class UserRepositoryImpl implements UserRepository {

  @Override
  public User getUserByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User getUserByEmailAndPassword(String username, String password) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User getUserById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User saveUser(User jpa) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void assignRoles(Set<Role> roles) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeRoles(Set<Role> roles) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<User> getAllUsers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteUser(User jpa) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public User updateUser(User jpa, boolean isPasswordChanged) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<Role> getUserRolesByNames(User jpa, Set<String> rolesNames) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<Role> getUserRolesByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

}
