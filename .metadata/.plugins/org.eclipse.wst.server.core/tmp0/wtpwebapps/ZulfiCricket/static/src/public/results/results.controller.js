(function() {
	"use strict";

	angular.module('public')
	// .controller('TeamsController', TeamsController)
	.controller('ResultController', ResultController);

	ResultController.$inject = [ 'results', 'TeamService' ];
	function ResultController(results, TeamService) {
		var $ctrl = this;
		$ctrl.matchResults = results;

		var seasonId = 31;
		TeamService.getBasicScoreCard(seasonId).then(function(response) {
			$ctrl.basicScoreCard_t35 = response;

		});

		var seasonId = 30;
		TeamService.getBasicScoreCard(seasonId).then(function(response) {
			$ctrl.basicScoreCard_t20 = response;

		});
	}
	;

})();
