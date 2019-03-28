package com.minimal.eshop.aspect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.minimal.eshop.security.UserNotAllowedException;
import com.minimal.eshop.service.RequestValidator;

@Aspect
@Component
@SuppressWarnings("unused")
public class RoleValidator implements RequestValidator {

  private static final Logger logger = LoggerFactory.getLogger(RoleValidator.class);

  private static final String ACTION_NOT_ALLOWED = "Action is not allowed";
  private static final String ADMIN = "ROLE_ADMIN";
  private static final String MANAGER = "ROLE_MANAGER";
  private static final String CUSTOMER = "ROLE_CUSTOMER";
  
  private List<String> allowedProductPurchaseRoles = new ArrayList<String>(Arrays.asList(new String[]{ADMIN, MANAGER, CUSTOMER}));
  private List<String> allowedManageRoles = new ArrayList<String>(Arrays.asList(new String[]{ADMIN, MANAGER}));
  private List<String> allowedModifyUserRoles = new ArrayList<String>(Arrays.asList(new String[]{ADMIN}));

  @Override
  public boolean validateRequestAgainstUserRoles(UsernamePasswordAuthenticationToken token, List<String> allowedRoles, String path)
  throws UserNotAllowedException {
    for (String role : allowedRoles) {
      if (token.getAuthorities().contains(new SimpleGrantedAuthority(role))) {
        return true;
      }
    }
    logger.error(ACTION_NOT_ALLOWED);
    throw new UserNotAllowedException(ACTION_NOT_ALLOWED).setPath(path);
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.CartController..*(..))")
  public void allCartControllerMethods() {
    //For checkUserRolesBeforeCart
  }

  @Before("allCartControllerMethods()")
  public void checkUserRolesBeforeCart(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    validateRequestAgainstUserRoles(token, allowedProductPurchaseRoles, request.getRequestURI());
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.OrderController..*(..))")
  public void allOrderControllerMethods() {
    //For checkUserRolesBeforeOrder
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.OrderController.getUserOrdersByName(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.OrderController.getUserOrdersById(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.OrderController.getOrderById(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.OrderController.deleteOrder(..))"
           )
  public void customerAllowedOrderControllerMethods() {
    //For checkUserRolesBeforeOrder
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.OrderController.getOrderStatusList(..))")
  public void allowedOrderControllerMethods() {
    //For checkUserRolesBeforeOrder
  }
  
  @Before("allOrderControllerMethods() && !(customerAllowedOrderControllerMethods() || allowedOrderControllerMethods())")
  public void checkUserRolesBeforeOrder(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    validateRequestAgainstUserRoles(token, allowedManageRoles, request.getRequestURI());
  }
  
  @Before("customerAllowedOrderControllerMethods()")
  public void checkUserRolesBeforeCustomerOrder(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    validateRequestAgainstUserRoles(token, allowedProductPurchaseRoles, request.getRequestURI());
  }

  @Pointcut("execution(* com.minimal.eshop.rest.controller.ProductController..*(..))")
  public void allProductControllerMethods() {
    //For checkUserRolesBeforeProduct
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.ProductController.getAllProducts(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.ProductController.getProductById(..))"
     )
  public void customerAllowedProductControllerMethods() {
    //For checkUserRolesBeforeProduct
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.ProductController.getAllProducts(..)) || "+
      "execution(* com.minimal.eshop.rest.controller.ProductController.getProductById(..))"
  )
  public void allowedProductControllerMethods() {
    //For checkUserRolesBeforeGetProduct
  }
  
  @Before("allProductControllerMethods() && !customerAllowedProductControllerMethods()")
  public void checkUserRolesBeforeProduct(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    validateRequestAgainstUserRoles(token, allowedManageRoles, request.getRequestURI());
  }

  @Before("customerAllowedProductControllerMethods()")
  public void checkUserRolesBeforeGetProduct(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    validateRequestAgainstUserRoles(token, allowedProductPurchaseRoles, request.getRequestURI());
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.UserController..*(..))")
  public void allUserControllerMethods() {
    //For checkUserRolesBeforeUser
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.UserController.loginSuccessfull(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.UserController.logout(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.UserController.getUserRoleMap(..))"
     )
  public void allowedUserControllerMethods() {
    //For checkUserRolesBeforeUser
  }
  
  @Before("allUserControllerMethods() && !allowedUserControllerMethods()")
  public void checkUserRolesBeforeUser(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    validateRequestAgainstUserRoles(token, allowedModifyUserRoles, request.getRequestURI());
  }

  
}
