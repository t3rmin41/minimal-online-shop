package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.minimal.eshop.dto.RoleDto;
import com.minimal.eshop.dto.UserDto;
import com.minimal.eshop.enums.RoleType;
import com.minimal.eshop.service.UserService;

@Controller
@RequestMapping(value = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
public class UserController {

  @Inject
  private UserService users;

  @RequestMapping(value = "/login/success", method = RequestMethod.POST)
  public @ResponseBody UserDto loginSuccessfull(HttpSession session, Principal principal) {
      UserDto dto = users.getUserByEmail(principal.getName());
      return dto;
  }
  
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public @ResponseBody UserDto logout(HttpSession session, Principal principal) {
    UserDto dto = users.getUserByEmail(principal.getName());
    return dto;
  }

  @RequestMapping(value = "/roles", method = RequestMethod.GET)
  public @ResponseBody List<RoleDto> getUserRoleMap() {
      List<RoleDto> roleList = new ArrayList<RoleDto>();
      for (RoleType role : RoleType.values()) {
        roleList.add(new RoleDto().setCode(role.toString()).setTitle(role.getTitle()));
      }
      return roleList;
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public @ResponseBody List<UserDto> getUsers(UsernamePasswordAuthenticationToken token, Principal principal) {
    return users.getAllUsers();
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody UserDto saveUser(UsernamePasswordAuthenticationToken token, @RequestBody UserDto dto) {
    return users.saveUser(dto);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody UserDto getUserById(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return users.getUserById(id);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody UserDto updateUser(UsernamePasswordAuthenticationToken token, @RequestBody UserDto dto) {
    return users.updateUser(dto);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteUser(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return users.deleteUserById(id);
  }

}
