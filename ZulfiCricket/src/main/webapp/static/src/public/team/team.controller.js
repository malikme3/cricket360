(function() {
	"use strict";

	angular.module('public')

	.controller('TeamsController', TeamsController);

	TeamsController.$inject = [ 'results', 'team', '$stateParams', 'TeamService' ];
	function TeamsController(results, team, $stateParams, TeamService) {
		var $ctrl = this;
		$ctrl.matchResults = [];
		$ctrl.seasonNames = [];
		$ctrl.teamPosition = [];
		$ctrl.teamsIdTeamsAbbrv = [];
		$ctrl.team = team;
		$ctrl.teamName = $stateParams.teamName;
		$ctrl.seasonYear = "2017";
		$ctrl.seasonName20 = "2017 20 Overs League";
		$ctrl.seasonName35 = "2017 35 Overs League";

		$ctrl.team_group_20_A = [];
		$ctrl.team_group_20_B = [];

		$ctrl.switchNameId = function(teamId, index, group) {

			switch (teamId) {
			case 4:
				group[index].team = 'Hawks';
				break;
			case 6:
				group[index].team = 'Falcons';
				break;
			case 7:
				group[index].team = 'Eagles';
				break;
			case 8:
				group[index].team = 'Lagaan Jaguars';
				break;
			case 9:
				group[index].team = 'Gladiators';
				break;
			case 11:
				group[index].team = 'Tigers';
				break;
			case 13:
				group[index].team = 'UCC';
				break;
			case 19:
				group[index].team = 'Longhorns';
				break;
			case 34:
				group[index].team = 'Panthers';
				break;
			case 35:
				group[index].team = 'Chargers';
				break;
			case 42:
				group[index].team = 'Ravens';
				break;
			case 46:
				group[index].team = 'Royals';
				break;
			case 47:
				group[index].team = 'Lions';
				break;
			case 48:
				group[index].team = 'Bengal Tigers';
				break;
			case 52:
				group[index].team = 'Tigers Pro';
				break;

			}

		};

		// 20 overs

		TeamService.getTeamPositionByGroup('Group B', $ctrl.seasonName20).then(function(response) {
			$ctrl.team_group_20_B = response;
			angular.forEach($ctrl.team_group_20_B, function(teamId, index) {
				$ctrl.switchNameId(teamId.team, index, $ctrl.team_group_20_B);
			});

		});
		TeamService.getTeamPositionByGroup('Group A', $ctrl.seasonName20).then(function(response) {
			$ctrl.team_group_20_A = response;
			angular.forEach($ctrl.team_group_20_A, function(teamId, index) {
				$ctrl.switchNameId(teamId.team, index, $ctrl.team_group_20_A);
			});

		});

		// 35 overs
		TeamService.getTeamPositionByGroup('red', $ctrl.seasonName35).then(function(response) {
			$ctrl.team_group_35_red = response;
			angular.forEach($ctrl.team_group_35_red, function(teamId, index) {
				$ctrl.switchNameId(teamId.team, index, $ctrl.team_group_35_red);
			});

		});
		TeamService.getTeamPositionByGroup('blue', $ctrl.seasonName35).then(function(response) {
			$ctrl.team_group_35_blue = response;
			angular.forEach($ctrl.team_group_35_blue, function(teamId, index) {
				$ctrl.switchNameId(teamId.team, index, $ctrl.team_group_35_blue);
			});

		});
		TeamService.getTeamPositionByGroup('white', $ctrl.seasonName35).then(function(response) {
			$ctrl.team_group_35_white = response;
			angular.forEach($ctrl.team_group_35_white, function(teamId, index) {
				$ctrl.switchNameId(teamId.team, index, $ctrl.team_group_35_white);
			});

		});

		TeamService.getTeamPositionByGroup($ctrl.seasonYear, $ctrl.seasonName).then(function(response) {
			$ctrl.teamPosition = response;
			/*
			 * angular.forEach($ctrl.teamPosition, function(teamP, index) {
			 * $ctrl.switchFuction(index); });
			 */
		});

		$ctrl.getTeamAbb = function getTeamAbb(seasonYear, seasonName) {
			TeamService.getTeamsIdTeamsAbbrv(seasonYear, seasonName).then(function(response) {
				$ctrl.teamsIdTeamsAbbrv = response;

			});
		}

		$ctrl.getTeamP = function getTeamP() {

			TeamService.getTeamPositionByGroup($ctrl.seasonYear, $ctrl.seasonName).then(function(response) {
				$ctrl.teamPosition = response;
				angular.forEach($ctrl.teamPosition, function(teamP, index) {
					$ctrl.switchFuction(index);
				});
			});
		};

	}
	;

})();
