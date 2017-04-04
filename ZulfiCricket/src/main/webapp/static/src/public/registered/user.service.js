(function() {
	"use strict";

	angular.module('common').service('UserService', UserService);

	UserService.$inject = [ '$http', 'ApiPath', '$q', 'ApiMVC' ];
	function UserService($http, ApiPath, $q, ApiMVC) {
		var service = this;
		service.userInfo = {
			"firstName" : "",
			"lastName" : "",
			"email" : "",
			"phone" : "",
			"fave" : "",
			"short_name" : "",
			"name" : "",
			"description" : "",
			"imgPath" : ""
		};

		service.getUserInfo = function() {
			return service.userInfo;
		};

		service.setPlayerInfo = function(playerInfo) {
			console.log("In service for registration");
			service.playerInfo = playerInfo;
			var deferred = $q.defer();

			$http.post(ApiMVC + '/player/registration', playerInfo, {
				headers : {
					'Content-Type' : 'application/json'
				}
			}).then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				console.error('in User Service: Error while registrating player');
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}

		// Checking if user already exist
		service.checkExistingPayer = function(playerInfo) {
			console.log("In service for checking existing player");
			service.playerInfo = playerInfo;
			var deferred = $q.defer();

			$http.post(ApiMVC + '/player/exist', playerInfo, {
				headers : {
					'Content-Type' : 'application/json'
				}
			}).then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				console.error('in User Service: Error while checking existing player');
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}

		// For login
		service.goLogin = function(loginInfo) {
			console.log("In service: For Login");
			var deferred = $q.defer();
			$http.post(ApiMVC + '/login', $.param({
				ssoId : loginInfo.username,
				password : loginInfo.password
			}), {
				headers : {
					'Access-Control-Allow-Origin' : '*',
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				console.error('Error while login');
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}

		service.getMenuItem = function(category) {
			return $http.get(ApiPath + '/menu_items/' + category + '.json');
		};

	}
})();
