package com.minimal.eshop.service;

import java.util.List;
import java.util.Set;
import com.minimal.eshop.bean.UserBean;

public interface UserService {

  UserBean getUserByUsernamAndPassword(String userName, String password);
  
  UserBean getUserByUsername(String username);
  
  UserBean getUserById(Long id);
  
  UserBean createUser(UserBean bean);
  
  List<UserBean> getAllUsers();
  
  boolean deleteUserById(Long id);
  
  UserBean updateUser(UserBean bean);
  
  Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles);
  
  Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles);
  
}
