(function() {
	'use strict';
	angular.module('common').controller('ScoreCardController', ScoreCardController);

	ScoreCardController.$inject = [ 'UserService', 'DataStoreService' ];
	function ScoreCardController(UserService, DataStoreService) {
		var scoreCardCtrl = this
		scoreCardCtrl.selectLeagueOptions = [];
		scoreCardCtrl.selectSeasonOptions = [];

		/*
		 * function leagueList (){ console.log("wit Function"); }
		 */

		console.log("In Score card controller :: Submitting request for League");
		UserService.getLeagues().then(function(response) {
			// scoreCardCtrl.selectLeagueOptions =
			// scoreCardCtrl.selectLeagueOptions.push(response);
			angular.forEach(response, function(league) {
				scoreCardCtrl.selectLeagueOptions.push(league.leagueAbbrev);
			});
		});

		console.log("In Score card controller :: Submitting request for Seasons");
		UserService.getSeasons().then(function(response) {
			angular.forEach(response, function(season) {
				scoreCardCtrl.selectSeasonOptions.push(season.seasonName);
			});
		});

		console.log("In score card controller");
	}

})();