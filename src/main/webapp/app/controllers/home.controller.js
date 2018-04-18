(function () {
  'use strict';

  angular
    .module('app')
    .controller('HomeController', HomeController);

  HomeController.$inject = ['$rootScope', '$scope', '$cookies', '$route', '$routeParams', '$location', 'LoginService', 'CartService', 'ErrorController'];

  function HomeController($rootScope, $scope, $cookies, $route, $routeParams, $location, LoginService, CartService, ErrorController) {

    var ctrl = this;

    $scope.$on('CartReload', function (event, message){
      console.log(message);
      ctrl.getUserCart();
    });
    
    $scope.route = $route;

    $scope.authenticated = ('true' == $cookies.get('authenticated'));
    
    $scope.user = $cookies.getObject('user');
    
    $scope.cart = {};
    
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
      $scope.cart = data;
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