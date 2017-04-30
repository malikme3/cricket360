(function() {
	'use strict';
	angular.module('common').controller('LiveScoreController', LiveScoreController);

	LiveScoreController.$inject = [ 'UserService', 'DataStoreService' ];
	function LiveScoreController(UserService, DataStoreService) {
		var liveScoreCtrl = this
		liveScoreCtrl.selectLeagueOptions = [];
		liveScoreCtrl.selectSeasonOptions = [];
		liveScoreCtrl.selectTeamsOptions = [];
		liveScoreCtrl.playerAvailablity = "Available";
		liveScoreCtrl.options = [ "Available", "Not-Available", "Tentive", "Out of town" ];
		
		liveScoreCtrl.batsmanSelected = "Select Batsman";
		liveScoreCtrl.batsman = [ "Select Batsman", "Ahmad", "Majid" ];
		
		liveScoreCtrl.bowlerSelected = "Select Bowler"
		liveScoreCtrl.bowler = [ "Select Bowler", "Akmal", "Zubair" ];
		
		liveScoreCtrl.overSelected = "Select Over";
		liveScoreCtrl.over = [ "Select Over",'1', '2', '3', '4', '5', '6' ];
		
		liveScoreCtrl.scoreSelected = "Select Score";
		liveScoreCtrl.score = [ "Select Score", '1', '2', '3', '4', 'four', 'six' ];

		/*
		 * function leagueList (){ console.log("wit Function"); }
		 */

		console.log("In Score card controller :: Submitting request for League");
		UserService.getLeagues().then(function(response) {
			// liveScoreCtrl.selectLeagueOptions =
			// liveScoreCtrl.selectLeagueOptions.push(response);
			angular.forEach(response, function(league) {
				liveScoreCtrl.selectLeagueOptions.push(league.leagueAbbrev);
			});
		});
		
})();