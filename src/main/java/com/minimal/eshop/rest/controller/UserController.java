package com.minimal.eshop.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import java.security.Principal;
import java.util.LinkedList;
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
import com.minimal.eshop.dto.RoleBean;
import com.minimal.eshop.dto.UserBean;
import com.minimal.eshop.enums.RoleType;
import com.minimal.eshop.service.UserService;

@Controller
@RequestMapping(value = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
public class UserController {

  @Inject
  private UserService users;

  @RequestMapping(value = "/login/success", method = RequestMethod.POST)
  public @ResponseBody UserBean loginSuccessfull(HttpSession session, Principal principal) {
      UserBean bean = users.getUserByEmail(principal.getName());
      return bean;
  }
  
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public @ResponseBody UserBean logout(HttpSession session, Principal principal) {
    UserBean bean = users.getUserByEmail(principal.getName());
    return bean;
  }

  @RequestMapping(value = "/roles", method = RequestMethod.GET)
  public @ResponseBody List<RoleBean> getUserRoleMap() {
      List<RoleBean> roleList = new LinkedList<RoleBean>();
      for (RoleType role : RoleType.values()) {
        roleList.add(new RoleBean().setCode(role.toString()).setTitle(role.getTitle()));
      }
      return roleList;
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public @ResponseBody List<UserBean> getUsers(UsernamePasswordAuthenticationToken token, Principal principal) {
    return users.getAllUsers();
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody UserBean saveUser(UsernamePasswordAuthenticationToken token, @RequestBody UserBean bean) {
    return users.saveUser(bean);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody UserBean getUserById(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return users.getUserById(id);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody UserBean updateUser(UsernamePasswordAuthenticationToken token, @RequestBody UserBean bean) {
    return users.updateUser(bean);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteUser(UsernamePasswordAuthenticationToken token, @PathVariable("id") Long id) {
    return users.deleteUserById(id);
  }

}
