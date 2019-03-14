package com.minimal.eshop.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
public class UserDto implements Serializable {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private List<RoleDto> roles;
  private boolean enabled;

  public Long getId() {
      return id;
  }
  public UserDto setId(Long id) {
      this.id = id;
      return this;
  }
  public String getFirstName() {
    return firstName;
  }
  public UserDto setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
  public String getLastName() {
    return lastName;
  }
  public UserDto setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }
  public String getEmail() {
    return email;
  }
  public UserDto setEmail(String email) {
    this.email = email;
    return this;
  }
  public String getPassword() {
      return password;
  }
  public UserDto setPassword(String password) {
      this.password = password;
      return this;
  }
  public List<RoleDto> getRoles() {
      return roles;
  }
  public UserDto setRoles(List<RoleDto> roles) {
      this.roles = roles;
      return this;
  }
  public boolean isEnabled() {
      return enabled;
  }
  public UserDto setEnabled(boolean enabled) {
      this.enabled = enabled;
      return this;
  }
  public List<String> getRolesAsStrings() {
    List<String> roleStrings = new LinkedList<String>();
    roles.stream().forEach(r -> {
      roleStrings.add(r.getCode());
    });
    return roleStrings;
  }
  
}
