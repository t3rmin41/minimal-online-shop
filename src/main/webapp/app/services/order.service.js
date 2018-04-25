(function() {
  'use strict';

  angular
    .module('app')
    .factory('OrderService', OrderService);
  
  OrderService.$inject = ['$http', '$location'];
  
  function OrderService($http, $location) {
    
    var service = {};

    service.getAllOrders = function(success, error) {
      $http({
        url: '/orders/all',
        method: 'GET',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    service.getUserOrdersById = function(userId, success, error) {
      $http({
        url: '/orders/user/id/'+userId,
        method: 'GET',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    service.getUserOrdersByName = function(username, success, error) {
      $http({
        url: '/orders/user/'+username,
        method: 'GET',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };
    
    service.getOrderById = function(id, success, error) {
      $http({
        url: '/orders/'+id,
        method: 'GET',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    service.getOrderStatusList = function(success, error) {
      $http({
        url: '/orders/statuslist',
        method: 'GET',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };
    
    service.saveOrder = function(order, success, error) {
      $http({
        url: '/orders/save',
        method: 'POST',
        data: order,
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };
    
    service.editOrder = function(order, success, error) {
      $http({
        url: '/orders/update',
        method: 'PUT',
        data: order,
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };
    
    service.deleteOrderById = function(id, success, error) {
      $http({
        url: '/orders/delete/'+id,
        method: 'DELETE',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    return service;
  }
  
})();