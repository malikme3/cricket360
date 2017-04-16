(function() {
	"use strict";

	angular.module('public')

	.controller('TeamsController', TeamsController);

	TeamsController.$inject = [ 'results', 'team', '$stateParams', 'TeamService' ];
	function TeamsController(results, team, $stateParams, TeamService) {
		var $ctrl = this;
		$ctrl.matchResults = [];
		$ctrl.team = team;
		$ctrl.name = $stateParams.teamName;

		TeamService.getTeamPoints("Lion","38").then(function(response) {
			$ctrl.matchResults = response;
		});
	}
	;

})();
