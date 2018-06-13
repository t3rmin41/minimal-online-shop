package com.minimal.eshop.aspect;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoleValidator {

  private static final Logger logger = LoggerFactory.getLogger(RoleValidator.class);
  
  private List<String> allowedProductPurchaseRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_CUSTOMER"}));
  private List<String> allowedManageRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_MANAGER"}));
  private List<String> allowedUserChangeRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN"}));
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.CartController..*(..))")
  public void allCartControllerMethods() {}

  @Before("allCartControllerMethods()")
  public void checkUserRolesBeforeCart(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    logger.info("{}", joinPoint);
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.OrderController..*(..))")
  public void allOrderControllerMethods() {}
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.OrderController.getUserOrdersByName(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.OrderController.getUserOrdersById(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.OrderController.getOrderById(..))"
           )
  public void allowedOrderControllerMethods() {}
  
  @Before("allOrderControllerMethods() && !allowedOrderControllerMethods()")
  public void checkUserRolesBeforeOrder(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    logger.info("{}", joinPoint);
  }
  
//  @Before("execution (* com.minimal.eshop.rest.controller.OrderController.getOrders(..))")
//  public void checkUserRolesBeforeGetOrders(JoinPoint joinPoint) {
//    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
//    logger.info("{}", joinPoint);
//  }
//  
//  @Before("execution (* com.minimal.eshop.rest.controller.OrderController.getUserOrdersByName(..))")
//  public void checkUserRolesBeforeGetUserOrdersByName(JoinPoint joinPoint) {
//    logger.info("{}", joinPoint);
//  }
//  
//  @Before("execution (* com.minimal.eshop.rest.controller.OrderController.getUserOrdersById(..))")
//  public void checkUserRolesBeforeGetUserOrdersById(JoinPoint joinPoint) {
//    logger.info("{}", joinPoint);
//  }
//  
//  @Before("execution (* com.minimal.eshop.rest.controller.OrderController.getOrderById(..))")
//  public void checkUserRolesBeforeGetOrderById(JoinPoint joinPoint) {
//    logger.info("{}", joinPoint);
//  }
//  
//  @Before("execution (* com.minimal.eshop.rest.controller.OrderController.saveOrder(..))")
//  public void checkUserRolesBeforeSaveOrder(JoinPoint joinPoint) {
//    logger.info("{}", joinPoint);
//  }
//  
//  @Before("execution (* com.minimal.eshop.rest.controller.OrderController.updateOrder(..))")
//  public void checkUserRolesBeforeUpdateOrder(JoinPoint joinPoint) {
//    logger.info("{}", joinPoint);
//  }
//  
//  @Before("execution (* com.minimal.eshop.rest.controller.OrderController.deleteOrder(..))")
//  public void checkUserRolesBeforeDeleteOrder(JoinPoint joinPoint) {
//    logger.info("{}", joinPoint);
//  }
  
  @Before("execution(* com.minimal.eshop.rest.controller.ProductController..*(..))")
  public void checkUserRolesBeforeProduct(JoinPoint joinPoint) {
    logger.info("{}", joinPoint);
  }
  
  @Before("execution(* com.minimal.eshop.rest.controller.ProductController.getAllProducts(..))")
  public void checkUserRolesBeforeGetAllProducts(JoinPoint joinPoint) {
    logger.info("{}", joinPoint);
  }
  
  @Before("execution(* com.minimal.eshop.rest.controller.ProductController.getProductById(..))")
  public void checkUserRolesBeforeGetProductById(JoinPoint joinPoint) {
    logger.info("{}", joinPoint);
  }
  
  @Before("execution(* com.minimal.eshop.rest.controller.UserController..*(..))")
  public void checkUserRolesBeforeUser(JoinPoint joinPoint) {
    logger.info("{}", joinPoint);
  }
  
}
