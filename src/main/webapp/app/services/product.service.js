(function() {
  'use strict';

  angular
    .module('app')
    .factory('ProductService', ProductService);
  
  ProductService.$inject = ['$http', '$location'];
  
  function ProductService($http, $location) {
    
    var service = {};

    service.getAllProducts = function(success, error) {
      $http({
        url: '/products/all',
        method: 'GET',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    service.getProductById = function(id, success, error) {
      $http({
        url: '/products/'+id,
        method: 'GET',
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };

    service.saveProduct = function(product, success, error) {
      $http({
        url: '/products/save',
        method: 'POST',
        data: product,
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };
    
    service.editProduct = function(product, success, error) {
      $http({
        url: '/products/update',
        method: 'PUT',
        data: product,
        headers : {
          "Content-Type" : "application/json;charset=UTF-8",
          "Accept" : "application/json;charset=UTF-8"
        }
      }).success(success).error(error);
    };
    
    service.deleteProductById = function(id, success, error) {
      $http({
        url: '/products/delete/'+id,
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