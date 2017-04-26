(function() {
	"use strict";

	angular.module('public')

	.controller('TeamsController', TeamsController);

	TeamsController.$inject = [ 'results', 'team', '$stateParams', 'TeamService', 'PointsDataService', 'TablePointService' ];
	function TeamsController(results, team, $stateParams, TeamService, PointsDataService, TablePointService) {
		var $ctrl = this;
		$ctrl.matchResults = [];
		$ctrl.seasonNames = [];
		$ctrl.teamPosition = [];
		$ctrl.teamsIdTeamsAbbrv = [];
		$ctrl.team = team;
		$ctrl.teamName = $stateParams.teamName;

		$ctrl.tableType = "Shabash !"
		$ctrl.tableHeader = PointsDataService.getTableHeader();

		// 20 overs
		$ctrl.team_group_20_A = PointsDataService.getTablePoints_2O_A();
		$ctrl.team_group_20_B = PointsDataService.getTablePoints_2O_B();

		// 30 overs
		$ctrl.team_group_35_red = PointsDataService.getTablePoints_35_red();
		$ctrl.team_group_35_blue = PointsDataService.getTablePoints_35_blue();
		$ctrl.team_group_35_white = PointsDataService.getTablePoints_35_white();

		$ctrl.getTeamAbb = function getTeamAbb(seasonYear, seasonName) {
			TeamService.getTeamsIdTeamsAbbrv(seasonYear, seasonName).then(function(response) {
				$ctrl.teamsIdTeamsAbbrv = response;

			});
		}

	}

})();
