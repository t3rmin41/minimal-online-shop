(function () {
    'use strict';

    angular
        .module('app')
        .controller('TabsController', TabsController);

    TabsController.$inject = ['$rootScope', '$scope',  '$cookies', '$filter', '$location', '$sce', 'CartService'];

    function TabsController($rootScope, $scope, $cookies, $filter, $location, $sce, CartService) {

      var ctrl = this;

      var hasAdmin = $cookies.getObject('user').roles.filter(function(role){ return role.code == "ADMIN"}).length > 0;
      var hasManager = $cookies.getObject('user').roles.filter(function(role){ return role.code == "MANAGER"}).length > 0;

      $scope.tabs = [
                      { id: '0', title: 'Products', path: 'app/views/product.html', ctrl : 'ProductController', selected: true, newOrdersAdded: false, reloadEvent: 'ProductReload' },
                      { id: '1', title: 'Orders', path: 'app/views/order.html', ctrl : 'OrderController', selected: false, newOrdersAdded: false, reloadEvent: 'OrderReload' },
                    ];

      var adminTabs = [
                        { id: '2', title: 'Users', path: 'app/views/user.html', ctrl : 'UserController', selected: false, newOrdersAdded: false, reloadEvent: 'UserReload' }
                      ];

      if (hasAdmin) {
        $scope.tabs = $scope.tabs.concat(adminTabs);
      }
      
      $scope.$on('CartSubmit', function(event, message){
        //console.log(message);
        $scope.ordersTab = $scope.tabs.filter(function(tab) { return tab.ctrl == 'OrderController'})[0];
        $scope.ordersTab.newOrdersAdded = true;
      });
      
      $scope.currentTab = $scope.tabs[0];
      $rootScope.currentTab = $scope.currentTab;
      
      $scope.setCurrentTab = function(i) {
        ctrl.tabsUnselected();
        $scope.currentTab = $scope.tabs.filter(function(tab) { return tab.id == i})[0];
        $scope.currentTab.selected = true;
        $scope.currentTab.newOrdersAdded = false;
        $rootScope.currentTab = $scope.currentTab;
        $rootScope.httpErrorMessage = null;
      }

      $scope.templateUrl = function(index) {
        return $scope.tabs.filter(function(tab) { return tab.id == index})[0].path;
      }
      
      ctrl.tabsUnselected = function() {
        for (var i = 0; i < $scope.tabs.length; i++) {
          $scope.tabs[i].selected = false;
        }
      }
      
    }
})();