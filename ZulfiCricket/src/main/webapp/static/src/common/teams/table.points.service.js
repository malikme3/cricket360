(function() {
	"use strict";
	angular.module('common').service('TablePointService', TablePointService);
	TablePointService.$inject = [ '$http', '$filter', '$q', 'ApiPath', 'ApiMVC', 'PointsDataService' ];

	function TablePointService($http, $filter, $q, ApiPath, ApiMVC, PointsDataService) {
		var service = this;

		service.teams_match_points = function get_teams_points(group, response) {

			angular.forEach(response, function(teamId, index) {
				service.switchNameId(teamId.team, index, response);

				if (group == 'T20_A') {
					PointsDataService.setTablePoints_2O_A(response);
				}
				if (group == 'T20_B') {
					PointsDataService.setTablePoints_2O_B(response);
				}
				if (group == 'T35_Red') {
					PointsDataService.setTablePoints_35_red(response);
				}
				if (group == 'T35_White') {
					PointsDataService.setTablePoints_35_white(response);
				}
				if (group == 'T35_Blue') {
					PointsDataService.setTablePoints_35_blue(response);
				}
			});

			return response;

		}

		service.team_group_20_B = function get_teams_points(group, seasonName) {
			var points_20_B = service.getTeamPositionByGroup(group, seasonName);
			angular.forEach(points_20_B, function(teamId, index) {
				service.switchNameId(teamId.team, index, points_20_B);
				PointsDataService.setTablePoints_2O_A(points_20_B);
				points_20_B = PointsDataService.getTablePoints_2O_B();
			});
			return points_20_B;
		}

		service.getTeamPositionByGroup = function(seasonYear, seasonName) {
			console.log("In user.service: for getting getTeamPositionByGroup");
			var deferred = $q.defer();

			$http.get(ApiMVC + '/team/position/', {
				headers : {
					'Access-Control-Allow-Origin' : '*',
					'Content-Type' : 'application/x-www-form-urlencoded'
				},
				params : {
					seasonYear : seasonYear,
					seasonName : seasonName
				}
			}, {
				cache : true
			}).then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				console.error('Error while getting Team list');
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}
		service.switchNameId = function(teamId, index, group) {

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
			case 49:
				group[index].team = 'Star XI';
				break;
			case 51:
				group[index].team = 'TBD';
				break;
			case 52:
				group[index].team = 'Tigers Pro';
				break;
			case 53:
				group[index].team = 'Pelicans';
				break;
			case 54:
				group[index].team = 'Scorchers';
				break;
			case 55:
				group[index].team = 'Thunders';
				break;
			case 56:
				group[index].team = 'Hurricanes';
				break;
			case 57:
				group[index].team = 'Freshers';
				break;
			default:
				group[index].team = 'Team Id is missing'

			}

		};

	}
})();