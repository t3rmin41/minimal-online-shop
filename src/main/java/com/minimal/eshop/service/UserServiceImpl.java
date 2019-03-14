package com.minimal.eshop.service;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.UserBean;
import com.minimal.eshop.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {


  @Inject
  private UserMapper userMapper;

  @Override
  @Cacheable(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'", sync = true)
  public List<UserBean> getAllUsers() {
    return userMapper.getAllUsers();
  }

  @Override
  public UserBean getUserByEmailAndPassword(String email, String password) {
    return userMapper.getUserBeanByEmailAndPassword(email, password);
  }

  @Override
  public UserBean getUserByEmail(String email) {
    return userMapper.getUserBeanByEmail(email);
  }

  @Override
  public UserBean getUserById(Long id) {
    return userMapper.convertUserToBeanByUserId(id);
  }

  @Override
  @Caching(
      evict = {@CacheEvict(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'", allEntries = true)}
  )
  public UserBean saveUser(UserBean bean) {
    return userMapper.saveUser(bean);
  }

  @Override
  @Caching(
      put = {@CachePut(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'")},
      evict = {@CacheEvict(cacheNames = {"cachedUsers"}, key = "'usersCacheKey'")}
  )
  public UserBean updateUser(UserBean bean) {
    return userMapper.updateUser(bean);
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
