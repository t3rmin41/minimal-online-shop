package com.minimal.eshop.mapper;

import java.util.List;
import java.util.Set;
import com.minimal.eshop.domain.RoleBean;
import com.minimal.eshop.domain.UserBean;
import com.minimal.eshop.jpa.RoleDao;

public interface UserMapper {

  UserBean getUserBeanByEmail(String email);
  
  UserBean getUserBeanByEmailAndPassword(String email, String password);

  UserBean convertUserToBeanByUserId(Long id);
  
  UserBean saveUser(UserBean bean);
  
  List<UserBean> getAllUsers();
  
  boolean deleteUserById(Long id);
  
  UserBean updateUser(UserBean bean);

  List<RoleBean> convertUserRolesToRoleBeans(Set<RoleDao> roles);

  void addRoles(Long userId, Set<String> roles);

  void removeRoles(Long userId, Set<String> roles);
  
  Set<String> getRolesByEmail(String email);
  
  Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles);
  
  Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles);
  
}
