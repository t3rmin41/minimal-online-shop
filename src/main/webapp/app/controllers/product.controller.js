(function () {
  'use strict';

  angular
    .module('app')
    .controller('ProductController', ProductController);

  ProductController.$inject = ['$rootScope', '$scope', '$cookies', '$routeParams', '$uibModal', '$location', 'ProductService', 'ErrorController'];
  function ProductController($rootScope, $scope, $cookies, $routeParams, $uibModal, $location, ProductService, ErrorController) {

    var ctrl = this;

    $scope.products = [];
    
    $rootScope.$on('ProductReload', function (event, message){
      ctrl.getProducts();
    });
    
    ctrl.$onInit = function() {
      //ctrl.getEditableProducts();
      ctrl.getProducts();
    };

    ctrl.getAllProducts = function() {
      ProductService.getAllProducts(getProductsSuccessCb, ErrorController.httpGetErroCb);
    }
    
    ctrl.getScreenProducts = function() {
      ProductService.getEditableProductsByScreenId($rootScope.chosenScreenId, getProductsSuccessCb, ErrorController.httpGetErroCb);
    }
    
    ctrl.getEditableProducts = function() {
      ProductService.getEditableProducts(getProductsSuccessCb, ErrorController.httpGetErroCb);
    }
    
    var getProductsSuccessCb = function(data, status, headers) {
      $scope.Products = data;
    }
    
    var getProductsErrorCb = function(data, status, headers) {
      //console.log(status);
    }
    
    $scope.addProduct = function() {
      var modal = $uibModal.open({
        resolve: {
          currentProduct: { skills : [] }
        },
        templateUrl: 'app/views/product-details.html',
        controller: ProductModalController,
        size: 'md'
      });
      modal.result.then(function(){
        ctrl.getEditableProducts();
      }, ErrorController.httpGetErroCb);
    }
    
    $scope.editProduct = function(Product) {
      var ProductToEdit = Product;
      var modalSize = Product.skills.length > 0 ? 'lg' : 'md';
      var modal = $uibModal.open({
        resolve: {
          currentProduct: angular.copy(ProductToEdit)
        },
        templateUrl: 'app/views/product-details.html',
        controller: ProductModalController,
        size : modalSize
      });
      modal.result.then(function(){
        ctrl.getEditableProducts();
      }, ErrorController.httpGetErroCb);
    }
    
    $scope.deleteProduct = function(Product) {
      $scope.currentProduct = Product;
      var modal = $uibModal.open({
        scope: $scope,
        template: '<div class="modal-header"><h4 class="modal-title">Confirmation</h4></div>'+
                  '<div class="modal-body">Do you want to delete product "{{ currentProduct.ProductName }}" ?</div>'+
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

  ProductModalController.$inject = ['$scope', '$uibModalInstance', '$cookies', '$routeParams', '$location', 'currentProduct', 'ProductService'];
  function ProductModalController($scope, $uibModalInstance, $cookies, $routeParams, $location, currentProduct, ProductService) {
    var ctrl = this;

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