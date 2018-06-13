package com.minimal.eshop.aspect;

import java.util.Arrays;
import java.util.LinkedList;
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
public class RoleValidator implements RequestValidator {

  private static final Logger logger = LoggerFactory.getLogger(RoleValidator.class);
  
  private List<String> allowedProductPurchaseRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_CUSTOMER"}));
  private List<String> allowedManageRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_MANAGER"}));
  private List<String> allowedModifyUserRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN"}));

  @Override
  public boolean validateRequestAgainstUserRoles(UsernamePasswordAuthenticationToken token, List<String> allowedRoles, String path)
  throws UserNotAllowedException {
    for (String role : allowedRoles) {
      if (token.getAuthorities().contains(new SimpleGrantedAuthority(role))) {
        return true;
      }
    }
    throw new UserNotAllowedException("Action is not allowed").setPath(path);
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.CartController..*(..))")
  public void allCartControllerMethods() {}

  @Before("allCartControllerMethods()")
  public void checkUserRolesBeforeCart(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    validateRequestAgainstUserRoles(token, allowedProductPurchaseRoles, request.getRequestURI());
  }
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.OrderController..*(..))")
  public void allOrderControllerMethods() {}
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.OrderController.getUserOrdersByName(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.OrderController.getUserOrdersById(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.OrderController.getOrderById(..))"
           )
  public void customerAllowedOrderControllerMethods() {}
  
  @Before("allOrderControllerMethods() && !customerAllowedOrderControllerMethods()")
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
  public void allProductControllerMethods() {}
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.ProductController.getAllProducts(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.ProductController.getProductById(..))"
     )
  public void customerAllowedProductControllerMethods() {}
  
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
  public void allUserControllerMethods() {}
  
  @Pointcut("execution(* com.minimal.eshop.rest.controller.UserController.loginSuccessfull(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.UserController.logout(..)) || "+
            "execution(* com.minimal.eshop.rest.controller.UserController.getUserRoleMap(..))"
     )
  public void allowedUserControllerMethods() {}
  
  @Before("allUserControllerMethods() && !allowedUserControllerMethods()")
  public void checkUserRolesBeforeUser(JoinPoint joinPoint) {
    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) joinPoint.getArgs()[0];
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    validateRequestAgainstUserRoles(token, allowedModifyUserRoles, request.getRequestURI());
    logger.info("{}", joinPoint);
  }

  
}
