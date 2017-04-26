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
	run.$inject = [ 'UserService', 'UserSessionInfo', 'TablePointService', 'TeamService' ];
	function run(UserService, UserSessionInfo, TablePointService, TeamService) {

		UserService.getUserSessionInfo().then(function(response) {
			console.log("In Cricket Module : User session from middle tier is : " + response);
			UserSessionInfo.setUserSession(response);

		});

		var seasonName20 = "2017 20 Overs League";
		var seasonName35 = "2017 35 Overs League";

		TeamService.getTeamPositionByGroup('Group A', seasonName20).then(function(response) {

			TablePointService.teams_match_points('T20_A', response);

		});

		TeamService.getTeamPositionByGroup('Group B', seasonName20).then(function(response) {

			TablePointService.teams_match_points('T20_B', response);
		});

		// 35 overs
		TeamService.getTeamPositionByGroup('red', seasonName35).then(function(response) {
			TablePointService.teams_match_points('T35_Red', response);
		});

		TeamService.getTeamPositionByGroup('blue', seasonName35).then(function(response) {
			TablePointService.teams_match_points('T35_Blue', response);
		});

		TeamService.getTeamPositionByGroup('white', seasonName35).then(function(response) {
			TablePointService.teams_match_points('T35_White', response);
		});
	}

})();
