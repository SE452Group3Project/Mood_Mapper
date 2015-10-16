/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery(document).ready(function($) {
	tab = $('.tabs h3 a');

	tab.on('click', function(event) {
		event.preventDefault();
		tab.removeClass('active');
		$(this).addClass('active');

		tab_content = $(this).attr('href');
		$('div[id$="tab-content"]').removeClass('active');
		$(tab_content).addClass('active');
	});
  
});

(function(angular) {
  'use strict';
var app = angular.module('loginOrSignUpForm', []); 
app.controller('SignUpController', ['$scope', '$http', function($scope, $http) {  
  //$scope.master = {}; 
  $scope.formData = {}; 


    $scope.update = function(user) {
      $scope.formData = angular.copy(user);
    };

    $scope.reset = function(form) {
      if (form) {
        form.$setPristine();
        form.$setUntouched();
      }
      $scope.user = angular.copy($scope.formData);
    };
  
  $scope.submit = function() {
      
  $http({
  method  : 'POST',
  url     : 'signup',
  data    : $.param($scope.formData),  // pass in data as strings
  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
 })
  .success(function(data) {
    console.log(data);

    if (!data.success) {
      // if not successful, bind errors to error variables
      $scope.errorName = data.errors.name;
      $scope.errorSuperhero = data.errors.superheroAlias;
    } else {
      // if successful, bind success message to message
      $scope.message = data.message;
    }
  });
};

    $scope.reset();
  }]);
app.controller('LoginController', ['$scope', '$http', function($scope, $http) {
    //$scope.master = {};
     $scope.formData = {}; 


    $scope.update = function(user) {
      $scope.formData = angular.copy(user);
    };

    $scope.reset = function(form) {
      if (form) {
        form.$setPristine();
        form.$setUntouched();
      }
      $scope.user = angular.copy($scope.formData);
    };
  
  $scope.submit = console.log($scope.formData); 
//function() {
//  $http({
//  method  : 'POST',
//  url     : 'login',
//  data    : $.param($scope.formData),  // pass in data as strings
//  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
// })
//  .success(function(data) {
//    console.log(data);
//
//    if (!data.success) {
//      // if not successful, bind errors to error variables
//      $scope.errorName = data.errors.name;
//      $scope.errorSuperhero = data.errors.superheroAlias;
//    } else {
//      // if successful, bind success message to message
//      $scope.message = data.message;
//    }
//  });
//};

    $scope.reset();
  }]);
app.directive('usernameAvailable', function($q, $timeout, $http) {
  return { 
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {
      var usernames = ['Jim', 'John', 'Jill', 'Jackie'];

      ctrl.$asyncValidators.username = function(modelValue, viewValue) {

        if (ctrl.$isEmpty(modelValue)) {
          // consider empty model valid
          return $q.when();
        }

          return $http.get('usernameavailability?username='+ modelValue).then(function(res){
          $timeout(function(){
            ctrl.$setValidity('usernameExists', res.data == 'true'); 
          }, 1000);
        }); 
      };
      
    }
  };
});
 })(window.angular);





