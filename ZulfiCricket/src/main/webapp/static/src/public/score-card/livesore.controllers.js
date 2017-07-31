(function() {
	'use strict';
	angular.module('common').controller('LiveScoreController', LiveScoreController);

	LiveScoreController.$inject = [ 'UserService', 'DataStoreService' ];
	function LiveScoreController(UserService, DataStoreService) {
		var liveScoreCtrl = this
		liveScoreCtrl.selectLeagueOptions = [];
		liveScoreCtrl.selectSeasonOptions = [];
		liveScoreCtrl.selectTeamsOptions = [];
		var ball = 0;

		/** ** Start Bowler *** */
		liveScoreCtrl.bowlerRecord = [ {
			bowlerOvers : 0,
			bowlerScores : 0,
			bowlerWickets: 0,
			bowlerExtras:0
		}

		];
		liveScoreCtrl.bowlerBalls = 0;

		/** ** Start End *** */

		liveScoreCtrl.currentOver = {
			inningss : "Select Innings",
			batsmanSelected : "Select Batsman",
			bowlerSelected : "Select Bowler",
			overSelected : "Select Over",
			scoreSelected : "Select Score",
			inningsSelected : "Select Innings"
		}

		liveScoreCtrl.innings = [ "Select Innings", "1st Innings", "2nd Innings" ];
		liveScoreCtrl.batsman = [ "Select Batsman", "Ahmad", "Majid" ];
		liveScoreCtrl.bowler = [ "Select Bowler", "Akmal", "Zubair" ];
		liveScoreCtrl.over = [ "Select Over", '1', '2', '3', '4', '5', '6' ];
		liveScoreCtrl.score = [ "Select Score", '1', '2', '3', '4', 'four', 'six' ];

		/*
		 * function leagueList (){ console.log("wit Function"); }
		 */
		liveScoreCtrl.submitOver = function submitOver(currentOver) {
			ball++;
			liveScoreCtrl.bowlerRecord.bowlerOvers = ball;
			liveScoreCtrl.bowlerRecord.bowlerScores = currentOver.scoreSelected;

			console.log("Over contains" + currentOver);
		};

		console.log("In Score card controller :: Submitting request for League");
		UserService.getLeagues().then(function(response) {
			// liveScoreCtrl.selectLeagueOptions =
			// liveScoreCtrl.selectLeagueOptions.push(response);
			angular.forEach(response, function(league) {
				liveScoreCtrl.selectLeagueOptions.push(league.leagueAbbrev);
			});
		});
	}
})();