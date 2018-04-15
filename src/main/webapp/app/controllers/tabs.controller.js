(function () {
    'use strict';

    angular
        .module('app')
        .controller('TabsController', TabsController);

    TabsController.$inject = ['$rootScope', '$scope',  '$cookies', '$filter', '$location', '$sce'];

    function TabsController($rootScope, $scope, $cookies, $filter, $location, $sce) {

      var ctrl = this;

      var hasAdmin = $cookies.getObject('user').roles.filter(function(role){ return role.code == "ADMIN"}).length > 0;
      var hasManager = $cookies.getObject('user').roles.filter(function(role){ return role.code == "MANAGER"}).length > 0;

      $scope.tabs = [
                      { id: '0', title: 'Products', path: 'app/views/product.html', ctrl : 'ProductController', reloadEvent: 'ProductReload' },
                      { id: '1', title: 'Orders', path: 'app/views/order.html', ctrl : 'OrderController', reloadEvent: 'OrderReload' },
                    ];

      var adminTabs = [
                        { id: '2', title: 'Users', path: 'app/views/user.html', ctrl : 'UserController', reloadEvent: 'UserReload' }
                      ];

      if (hasAdmin) {
        $scope.tabs = $scope.tabs.concat(adminTabs);
      }
      
      $scope.currentTab = $scope.tabs[0];
      $rootScope.currentTab = $scope.currentTab;
      
      $scope.setCurrentTab = function(i) {
        $scope.currentTab = $scope.tabs.filter(function(tab) { return tab.id == i})[0];
        $rootScope.currentTab = $scope.currentTab;
        $rootScope.httpErrorMessage = null;
      }

      $scope.templateUrl = function(index) {
        return $scope.tabs.filter(function(tab) { return tab.id == index})[0].path;
      }
      
    }
})();