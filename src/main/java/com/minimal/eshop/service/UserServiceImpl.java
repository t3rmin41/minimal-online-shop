package com.minimal.eshop.service;

import java.util.List;
import java.util.Set;
import com.minimal.eshop.bean.UserBean;

public class UserServiceImpl implements UserService {

  @Override
  public UserBean getUserByUsernamAndPassword(String userName, String password) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean getUserByUsername(String username) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean getUserById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean createUser(UserBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UserBean> getAllUsers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteUserById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public UserBean updateUser(UserBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
    // TODO Auto-generated method stub
    return null;
  }

}
