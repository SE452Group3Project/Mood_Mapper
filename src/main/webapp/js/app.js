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
app.controller('SignUpController', ['$scope', '$http', '$location', function($scope, $http, $location) {  
  $scope.user = {}; 
  
  $scope.submit = function() {

      console.log("posting data....");
        console.log($.param($scope.user)); 
  $http({
  method  : 'POST',
  url     : 'signup',
  data    : $.param($scope.user),  // pass in data as strings
  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
 })
  .success(function(data) {
    console.log(data);
     window.location = 'home.jsp';
  });
};

  }]);
app.controller('LoginController', ['$scope', '$http', '$location', function($scope, $http, $location) {
    $scope.user = {};

  $scope.submit = function() {

      console.log("posting data....");
        console.log($.param($scope.user)); 
  $http({
  method  : 'POST',
  url     : 'login',
  data    : $.param($scope.user),  // pass in data as strings
  headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
 })
  .success(function(data) {
    console.log(data);
     window.location = 'home.jsp';

  });
};

  }]);
app.directive('usernameAvailable', function($q, $timeout, $http) {
  return { 
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {

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

app.directive('emailAvailable', function($q, $timeout, $http) {
  return { 
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {

      ctrl.$asyncValidators.email = function(modelValue, viewValue) {

        if (ctrl.$isEmpty(modelValue)) {
          // consider empty model valid
          return $q.when();
        }

          return $http.get('email_availability?email='+ modelValue).then(function(res){
          $timeout(function(){
            ctrl.$setValidity('emailExists', res.data == 'true'); 
          }, 1000);
        }); 
      };
      
    }
  };
});
 })(window.angular);





