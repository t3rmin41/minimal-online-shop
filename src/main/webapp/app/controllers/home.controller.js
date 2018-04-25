(function () {
  'use strict';

  angular
    .module('app')
    .controller('HomeController', HomeController);

  HomeController.$inject = ['$rootScope', '$scope', '$cookies', '$route', '$routeParams', '$location', 'LoginService', 'CartService', 'ErrorController'];

  function HomeController($rootScope, $scope, $cookies, $route, $routeParams, $location, LoginService, CartService, ErrorController) {

    var ctrl = this;

    $scope.route = $route;

    $scope.authenticated = ('true' == $cookies.get('authenticated'));

    if ($scope.authenticated) {
      $rootScope.user = $cookies.getObject('user');
    }

    if ($scope.authenticated) {
      $rootScope.hasManager = $scope.user.roles.filter(function(role){ return role.code == "MANAGER"}).length > 0;
      $rootScope.hasAdmin = $scope.user.roles.filter(function(role){ return role.code == "ADMIN"}).length > 0;
    }
    
    $scope.cart = {};
    
    $scope.$on('CartReload', function (event, message){
        console.log(message);
        ctrl.getUserCart();
    })

    ctrl.$onInit = function() {
      if (undefined != $cookies.get('token') && null != $cookies.get('token')) {
      }
      ctrl.getUserCart();
    };

    if (!$scope.authenticated) {
      $location.path("/login");
    }

    ctrl.getUserCart = function() {
      CartService.getUserCart(getCartSuccessCb, getCartErrorCb);
    }
    
    var getCartSuccessCb = function(data, status, headers) {
      angular.element($('#cart')).scope().cart = data;
    }
    
    var getCartErrorCb = function(data, status, headers) {
      //console.log(status);
    }
    
    ctrl.removeFromCart = function(order) {
      CartService.removeOrderFromCart(order, removeOrderSuccessCb, removeOrderErrorCb);
    }
    
    var removeOrderSuccessCb = function(data, status, headers) {
      ctrl.getUserCart();
    }
    
    var removeOrderErrorCb = function(data, status, headers) {
      //console.log(status);
    }
    
    $scope.sumbitCart = function(cart) {
      CartService.submitCart(cart, function(){ 
        ctrl.getUserCart();
      }, function(){});
    };

    $scope.logout = function() {
      LoginService.logout(logoutCallback);
    };

    var logoutCallback = function(data, status, headers) {
      $cookies.put('authenticated', false);
      $scope.authenticated = false;
      $scope.userLoggedOut = true;
      $location.path("/login");
    };

  }
})();