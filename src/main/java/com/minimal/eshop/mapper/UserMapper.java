package com.minimal.eshop.mapper;

import java.util.List;
import java.util.Set;
import com.minimal.eshop.dto.RoleDto;
import com.minimal.eshop.dto.UserDto;
import com.minimal.eshop.domain.RoleJpa;

public interface UserMapper {

  UserDto getUserDtoByEmail(String email);
  
  UserDto getUserDtoByEmailAndPassword(String email, String password);

  UserDto convertUserTodtoByUserId(Long id);
  
  UserDto saveUser(UserDto dto);
  
  List<UserDto> getAllUsers();
  
  boolean deleteUserById(Long id);
  
  UserDto updateUser(UserDto dto);

  List<RoleDto> convertUserRolesToRoleDtos(Set<RoleJpa> roles);

  void addRoles(Long userId, Set<String> roles);

  void removeRoles(Long userId, Set<String> roles);
  
  Set<String> getRolesByEmail(String email);
  
  Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles);
  
  Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles);
  
}
