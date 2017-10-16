var myApp = angular.module('user', []);

myApp.controller('userCtrl', [ '$scope', 'userService',
		function($scope, userService) {
			userService.list().success(function(json) {
				console.log('# Retorno: ' + JSON.stringify(json));
				if (json && json.ok) {
					$scope.users = json.users;
				} else {
					$scope.error = json.msg;
				}
			});
		} ]);

myApp.service('userService', function($http) {
	this.list = function() {
		console.log('userService - meu');
		return $http.get("/user/getAll.do", {
			params : {
				param : "user",
			}
		});
	}
});

myApp.service('userService2', function($http) {
	this.list = function() {
		console.log('userService2 - original');
		return $http.get("/ajax/execute.do", {
			params : {
				command : "user",
				group : "list"
			}
		});
	}
});

/** Original
;
(function(angular) {
	"use strict";
	angular.module("user", []).controller("userCtrl",
			[ "$scope", "userService", function($scope, userService) {
				userService.list().success(function(json) {
					if (json && json.ok) {
						$scope.users = json.users;
					}
				})
			} ]).service("userServiceold", [ "$http", function($http) {
		this.list = function() {
			return $http.get("/ajax/execute.do", {
				params : {
					command : "user",
					group : "list"
				}
			});
		}
	} ]);
})(angular);
*/