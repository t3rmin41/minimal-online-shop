(function () {
  'use strict';

  angular
    .module('app')
    .controller('OrderController', OrderController);

  OrderController.$inject = ['$rootScope', '$scope', '$cookies', '$routeParams', '$uibModal', '$location', 'OrderService', 'ErrorController'];
  function OrderController($rootScope, $scope, $cookies, $routeParams, $uibModal, $location, OrderService, ErrorController) {

    var ctrl = this;

    $scope.orders = [];
    
    $rootScope.$on('OrderReload', function (event, message){
      ctrl.getOrders();
    });
    
    ctrl.$onInit = function() {
      //ctrl.getEditableOrders();
      ctrl.getOrders();
    };

    ctrl.getAllOrders = function() {
      OrderService.getAllOrders(getOrdersSuccessCb, ErrorController.httpGetErroCb);
    }
    
    ctrl.getScreenOrders = function() {
      OrderService.getEditableOrdersByScreenId($rootScope.chosenScreenId, getOrdersSuccessCb, ErrorController.httpGetErroCb);
    }
    
    ctrl.getEditableOrders = function() {
      OrderService.getEditableOrders(getOrdersSuccessCb, ErrorController.httpGetErroCb);
    }
    
    var getOrdersSuccessCb = function(data, status, headers) {
      $scope.orders = data;
    }
    
    var getOrdersErrorCb = function(data, status, headers) {
      //console.log(status);
    }
    
    $scope.addOrder = function() {
      var modal = $uibModal.open({
        resolve: {
          currentOrder: { skills : [] }
        },
        templateUrl: 'app/views/order-details.html',
        controller: OrderModalController,
        size: 'md'
      });
      modal.result.then(function(){
        ctrl.getEditableOrders();
      }, ErrorController.httpGetErroCb);
    }
    
    $scope.editOrder = function(order) {
      var orderToEdit = order;
      var modalSize = Order.skills.length > 0 ? 'lg' : 'md';
      var modal = $uibModal.open({
        resolve: {
          currentOrder: angular.copy(orderToEdit)
        },
        templateUrl: 'app/views/order-details.html',
        controller: OrderModalController,
        size : modalSize
      });
      modal.result.then(function(){
        ctrl.getEditableOrders();
      }, ErrorController.httpGetErroCb);
    }
    
    $scope.deleteOrder = function(order) {
      $scope.currentOrder = order;
      var modal = $uibModal.open({
        scope: $scope,
        template: '<div class="modal-header"><h4 class="modal-title">Confirmation</h4></div>'+
                  '<div class="modal-body">Do you want to delete order "{{ currentOrder.productName }}" ?</div>'+
                  '<div class="modal-footer">'+
                    '<button class="btn btn-small" ng-click="confirm(currentOrder.id)">Yes</button>'+
                    '<button class="btn btn-small" ng-click="cancel()">No</button>'+
                  '</div>',
        controller: function($uibModalInstance, OrderService, ErrorController) {

          $scope.confirm = function(id) {
            OrderService.deleteOrderById(id, function(){
              //console.log("Successfully deleted Order");
              $uibModalInstance.close();
            }, ErrorController.httpGetErroCb);
          }
          
          $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          }
        }
      });

      modal.result.then(function(){
        ctrl.getAllOrders();
      }, function() {});
    }
  }
  

  angular.module('app').controller('OrderModalController', OrderModalController);

  OrderModalController.$inject = ['$scope', '$uibModalInstance', '$cookies', '$routeParams', '$location', 'currentOrder', 'OrderService'];
  function OrderModalController($scope, $uibModalInstance, $cookies, $routeParams, $location, currentOrder, OrderService) {
    var ctrl = this;

    $scope.modalTitle = "";
    if (currentOrder.id) {
      $scope.modalTitle = "Edit order";
    } else {
      $scope.modalTitle = "Add new order";
    }

    ctrl.$onInit = function() {
      //
    };

    var getErrorCallback = function(data, status, headers) {
      //console.log(status);
    }

    var saveOrderErrorCb = function(data, status, headers) {
      $scope.errors = {};
      angular.forEach(data.errors, function(error, index){
        $scope.errors[error.field] = error.errorMessage;
      });
      $scope.errorMessage = data.errorMessage;
      if (500 == status) {
        $scope.errorMessage = 'Internal server error while processing the request';
      }
    }

    $scope.save = function(currentOrder) {
      if (currentOrder.id) {
        OrderService.editOrder(currentOrder, function() {
          //console.log("Successfully edited Order");
          $uibModalInstance.close();
        }, saveOrderErrorCb);
      } else {
        OrderService.saveOrder(currentOrder, function() {
          //console.log("Successfully saved Order");
          $uibModalInstance.close();
        }, saveOrderErrorCb);
      }
    }

    $scope.cancel = function() {
      $uibModalInstance.dismiss('cancel');
    }

  }
})();