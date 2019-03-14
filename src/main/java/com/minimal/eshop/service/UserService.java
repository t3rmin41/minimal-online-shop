package com.minimal.eshop.service;

import java.util.List;
import java.util.Set;
import com.minimal.eshop.dto.UserDto;

public interface UserService {

  UserDto getUserByEmailAndPassword(String email, String password);
  
  UserDto getUserByEmail(String email);
  
  UserDto getUserById(Long id);
  
  UserDto saveUser(UserDto dto);
  
  List<UserDto> getAllUsers();
  
  boolean deleteUserById(Long id);
  
  UserDto updateUser(UserDto dto);

  Set<String> getRolesByEmail(String email);
  
}
