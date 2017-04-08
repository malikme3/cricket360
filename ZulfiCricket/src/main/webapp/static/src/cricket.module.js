(function() {
	"use strict";

	/**
	 * Cricket module that includes the public module as a dependency
	 */
	angular.module('cricketApp', [ 'public' ]).config(config).run(run);

	config.$inject = [ '$urlRouterProvider', '$httpProvider' ];
	function config($urlRouterProvider, $httpProvider) {

		// If user goes to a path that doesn't exist, redirect to public root
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		$urlRouterProvider.otherwise('/');
	}
	;
	run.$inject = [ 'UserService', 'UserSessionInfo' ];
	function run(UserService, UserSessionInfo) {

		UserService.getUserSessionInfo().then(function(response) {
			console.log("In Cricket Module : User session from middle tier is : " + response);
			UserSessionInfo.setUserSession(response);

		});
	}
	;

})();
