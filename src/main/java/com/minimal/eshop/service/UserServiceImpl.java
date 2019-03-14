package com.minimal.eshop.service;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.UserDto;
import com.minimal.eshop.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {


  @Inject
  private UserMapper userMapper;

  @Override
  @Cacheable(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'", sync = true)
  public List<UserDto> getAllUsers() {
    return userMapper.getAllUsers();
  }

  @Override
  public UserDto getUserByEmailAndPassword(String email, String password) {
    return userMapper.getUserDtoByEmailAndPassword(email, password);
  }

  @Override
  public UserDto getUserByEmail(String email) {
    return userMapper.getUserDtoByEmail(email);
  }

  @Override
  public UserDto getUserById(Long id) {
    return userMapper.convertUserTodtoByUserId(id);
  }

  @Override
  @Caching(
      evict = {@CacheEvict(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'", allEntries = true)}
  )
  public UserDto saveUser(UserDto dto) {
    return userMapper.saveUser(dto);
  }

  @Override
  @Caching(
      put = {@CachePut(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'")},
      evict = {@CacheEvict(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'")}
  )
  public UserDto updateUser(UserDto dto) {
    return userMapper.updateUser(dto);
  }
  
  @Override
  @Caching(
      evict = {@CacheEvict(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'")}
  )
  public boolean deleteUserById(Long id) {
    return userMapper.deleteUserById(id);
  }

  @Override
  public Set<String> getRolesByEmail(String email) {
    return userMapper.getRolesByEmail(email);
  }

}
