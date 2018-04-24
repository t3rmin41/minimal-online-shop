(function () {
  'use strict';

  angular
    .module('app')
    .controller('ProductController', ProductController);

  ProductController.$inject = ['$rootScope', '$scope', '$cookies', '$routeParams', '$uibModal', '$location', 'ProductService', 'CartService', 'ErrorController'];
  function ProductController($rootScope, $scope, $cookies, $routeParams, $uibModal, $location, ProductService, CartService, ErrorController) {

    var ctrl = this;

    $scope.hasManager = $cookies.getObject('user').roles.filter(function(role){ return role.code == "MANAGER"}).length > 0;
    $scope.hasAdmin = $cookies.getObject('user').roles.filter(function(role){ return role.code == "ADMIN"}).length > 0;
    
    $scope.products = [];

    $rootScope.$on('ProductReload', function (event, message){
      ctrl.getProducts();
    });
    
    ctrl.$onInit = function() {
      ctrl.getProducts();
    };

    ctrl.getProducts = function() {
      ProductService.getAllProducts(getProductsSuccessCb, ErrorController.httpGetErroCb);
    }

    var getProductsSuccessCb = function(data, status, headers) {
      $scope.products = data;
    }
    
    var getProductsErrorCb = function(data, status, headers) {
      //console.log(status);
    }
    
    $scope.addToCart = function(product) {
      CartService.addProductToCart(product, function(){
        $scope.$emit('CartReload', "Reload cart after adding product");
      }, ErrorController.httpGetErroCb);
    }
    
    $scope.addProduct = function() {
      var modal = $uibModal.open({
        resolve: {
          currentProduct: {}
        },
        templateUrl: 'app/views/product-details.html',
        controller: ProductModalController,
        size: 'md'
      });
      modal.result.then(function(){
        ctrl.getProducts();
      }, ErrorController.httpGetErroCb);
    }
    
    $scope.editProduct = function(product) {
      var productToEdit = product;
      var modalSize = 'md';
      var modal = $uibModal.open({
        resolve: {
          currentProduct: angular.copy(productToEdit)
        },
        templateUrl: 'app/views/product-details.html',
        controller: ProductModalController,
        size : modalSize
      });
      modal.result.then(function(){
        ctrl.getProducts();
      }, ErrorController.httpGetErroCb);
    }
    
    $scope.deleteProduct = function(product) {
      $scope.currentProduct = product;
      var modal = $uibModal.open({
        scope: $scope,
        template: '<div class="modal-header"><h4 class="modal-title">Confirmation</h4></div>'+
                  '<div class="modal-body">Do you want to delete product "{{ currentProduct.title }}" ?</div>'+
                  '<div class="modal-footer">'+
                    '<button class="btn btn-small" ng-click="confirm(currentProduct.id)">Yes</button>'+
                    '<button class="btn btn-small" ng-click="cancel()">No</button>'+
                  '</div>',
        controller: function($uibModalInstance, ProductService, ErrorController) {

          $scope.confirm = function(id) {
            ProductService.deleteProductById(id, function(){
              //console.log("Successfully deleted Product");
              $uibModalInstance.close();
            }, ErrorController.httpGetErroCb);
          }
          
          $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          }
        }
      });

      modal.result.then(function(){
        ctrl.getAllProducts();
      }, function() {});
    }
  }
  

  angular.module('app').controller('ProductModalController', ProductModalController);

  ProductModalController.$inject = ['$scope', '$uibModalInstance', '$cookies', '$routeParams', '$location', 'currentProduct', 'ProductService', 'CartService'];
  function ProductModalController($scope, $uibModalInstance, $cookies, $routeParams, $location, currentProduct, ProductService, CartService) {
    var ctrl = this;

    $scope.currentProduct = currentProduct;
    
    $scope.modalTitle = "";
    if (currentProduct.id) {
      $scope.modalTitle = "Edit product";
    } else {
      $scope.modalTitle = "Add new product";
    }

    ctrl.$onInit = function() {
      //
    };

    var getErrorCallback = function(data, status, headers) {
      //console.log(status);
    }

    var saveProductErrorCb = function(data, status, headers) {
      $scope.errors = {};
      angular.forEach(data.errors, function(error, index){
        $scope.errors[error.field] = error.errorMessage;
      });
      $scope.errorMessage = data.errorMessage;
      if (500 == status) {
        $scope.errorMessage = 'Internal server error while processing the request';
      }
    }

    $scope.save = function(currentProduct) {
      if (currentProduct.id) {
        ProductService.editProduct(currentProduct, function() {
          //console.log("Successfully edited Product");
          $uibModalInstance.close();
        }, saveProductErrorCb);
      } else {
        ProductService.saveProduct(currentProduct, function() {
          //console.log("Successfully saved Product");
          $uibModalInstance.close();
        }, saveProductErrorCb);
      }
    }

    $scope.cancel = function() {
      $uibModalInstance.dismiss('cancel');
    }

  }
})();