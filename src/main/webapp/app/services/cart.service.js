(function() {
  'use strict';

  angular
    .module('app')
    .factory('CartService', CartService);
  
  CartService.$inject = ['$http', '$location'];
  
  function CartService($http, $location) {
    
    var service = {};

    service.getUserCart = function(success, error) {
      $http({
        url: '/cart',
        method: 'GET',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    service.addProductToCart = function(product, success, error) {
      $http({
        url: '/cart/product/add',
        method: 'POST',
        data: product,
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    service.removeOrderFromCart = function(order, success, error) {
      $http({
        url: '/cart/product/remove',
        method: 'DELETE',
        data: order,
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    service.submitCart = function(cart, success, error) {
      $http({
        url: '/cart/submit',
        method: 'POST',
        data: cart,
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    return service;
  }
  
})();