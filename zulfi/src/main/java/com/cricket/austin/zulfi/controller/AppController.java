package com.cricket.austin.zulfi.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.Leagues;
import com.cricket.austin.zulfi.model.Player;
import com.cricket.austin.zulfi.model.PlayerCtcl;
import com.cricket.austin.zulfi.model.Schedule;
import com.cricket.austin.zulfi.model.ScoreCardBasic;
import com.cricket.austin.zulfi.model.ScorecardBatting;
import com.cricket.austin.zulfi.model.ScorecardBattingDetails;
import com.cricket.austin.zulfi.model.ScorecardBowling;
import com.cricket.austin.zulfi.model.ScorecardBowlingDetails;
import com.cricket.austin.zulfi.model.ScorecardFowDetails;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.ScorecardTotalDetails;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.SorecardExtrasDetails;
import com.cricket.austin.zulfi.model.SubmitResults;
import com.cricket.austin.zulfi.model.Teams;
import com.cricket.austin.zulfi.service.TeamService;
import com.cricket.austin.zulfi.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AppController {

	@Autowired
	TeamService			teamServiceMatch;
	@Autowired
	UserService			userService;
	static final Logger	logger	= LoggerFactory.getLogger(AppController.class);

	@RequestMapping("/")
	public String sayHello() {

		return "Welcome to Austin Cricket !!";
	};

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ResponseEntity<List<Player>> getAllPlayers() {
		// List<Player> users = userService.getAllPlayers();
		/*
		 * if(users.isEmpty()){ return new
		 * ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide
		 * to return HttpStatus.NOT_FOUND }
		 */
		return new ResponseEntity<List<Player>>(HttpStatus.OK);
	}

	// Getting Players for Match Selection
	@RequestMapping(value = "/players/selection", method = RequestMethod.GET)
	public ResponseEntity<List<PlayerCtcl>> getTeamPlayers() {
		List<PlayerCtcl> players = userService.getTeamPlayers();
		return new ResponseEntity<List<PlayerCtcl>>(players, HttpStatus.OK);
	}

	@RequestMapping(value = "/playersCtcl/selection", method = RequestMethod.GET)
	public ResponseEntity<List<PlayerCtcl>> getTeamPlayersCtcl() {
		List<PlayerCtcl> players = userService.getTeamPlayersCtcl();
		return new ResponseEntity<List<PlayerCtcl>>(players, HttpStatus.OK);
	}

	// Getting Leagues List
	@RequestMapping(value = "/leagues/list", method = RequestMethod.GET)
	public ResponseEntity<List<Leagues>> getLeagues() {

		List<Leagues> league = userService.getLeaguesList();
		return new ResponseEntity<List<Leagues>>(league, HttpStatus.OK);
	}

	// Getting Seasons List
	@RequestMapping(value = "/seasons/list", method = RequestMethod.GET)
	public ResponseEntity<List<Seasons>> getSeasons(@RequestParam String seasonYear) {
		List<Seasons> league = userService.getSeasonsList(seasonYear);
		return new ResponseEntity<List<Seasons>>(league, HttpStatus.OK);
	}

	// Getting team List
	@RequestMapping(value = "/teams/list", method = RequestMethod.GET)
	public ResponseEntity<List<Teams>> getTeams() {
		List<Teams> league = userService.getTeamsList();
		return new ResponseEntity<List<Teams>>(league, HttpStatus.OK);
	}

	// Getting season groups
	@RequestMapping(value = "/season/groups", method = RequestMethod.GET)
	public ResponseEntity<List<Seasons>> seasonGroups() {
		String year = "2017";
		List<Seasons> league = teamServiceMatch.getSeasonGroups(year);
		return new ResponseEntity<List<Seasons>>(league, HttpStatus.OK);
	}

	// Getting team List
	@RequestMapping(value = { "/team/position/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Ladder>> TeamPosition(@RequestParam String seasonYear, String seasonName) {
		List<Ladder> position = teamServiceMatch.getTeamPosition(seasonYear, seasonName);
		return new ResponseEntity<List<Ladder>>(position, HttpStatus.OK);
	}

	// Getting team Id and teams abbrv name
	@RequestMapping(value = { "/team/teamsIdTeamsAbbrv/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Ladder>> teamsIdTeamsAbbrv(@RequestParam String seasonYear, String seasonName) {
		List<Ladder> position = teamServiceMatch.getTeamsIdTeamsAbbrv(seasonYear, seasonName);
		return new ResponseEntity<List<Ladder>>(position, HttpStatus.OK);
	}

	// Getting schedule
	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public ResponseEntity<List<Teams>> getSchedule() {
		List<Teams> league = userService.getScheduleList();
		return new ResponseEntity<List<Teams>>(league, HttpStatus.OK);
	}

	// Getting season groups
	@RequestMapping(value = "/matches/schedule", method = RequestMethod.GET)
	public ResponseEntity<List<Schedule>> MatchesSchedule(@RequestParam String seasonId) {

		List<Schedule> schedule = teamServiceMatch.getSchedule(seasonId);
		return new ResponseEntity<List<Schedule>>(schedule, HttpStatus.OK);
	}

	// Getting points
	@RequestMapping(value = "/team/ptable", method = RequestMethod.GET)
	public ResponseEntity<List<Ladder>> teamPoints(@PathVariable Ladder postion) {
		List<Ladder> points = userService.getTeamPoints(postion.getTeamName(), "2017 35 Overs League");
		return new ResponseEntity<List<Ladder>>(points, HttpStatus.OK);
	}
	/*
	 * -------------------Submitting availability for team
	 * Selection--------------------------------------------------------
	 */

	@RequestMapping(value = "/submit/availability", method = RequestMethod.POST)
	public ResponseEntity<List<PlayerCtcl>> submitPlayerForSelection(@RequestBody PlayerCtcl player,
			UriComponentsBuilder ucBuilder) {
		System.out.println("In Spring MVC controller for Submitting availability for team Selection");
		userService.savePlayerForSelection(player);
		List<PlayerCtcl> players = userService.getTeamPlayers();
		return new ResponseEntity<List<PlayerCtcl>>(players, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/open/availability", method = RequestMethod.POST)
	public ResponseEntity<List<PlayerCtcl>> openAvailability(@RequestBody PlayerCtcl player) {
		System.out.println("In Spring MVC controller for open availability ");
		userService.openAvailability(player);
		List<PlayerCtcl> players = userService.getTeamPlayers();
		return new ResponseEntity<List<PlayerCtcl>>(players, HttpStatus.CREATED);
	}

	/* Submitting and retrieving selected player for Playing XI */
	@RequestMapping(value = "/submit/playingXI", method = RequestMethod.POST)
	public ResponseEntity<List<PlayerCtcl>> playingXI(@RequestBody PlayerCtcl[] player) {
		System.out.println("In Spring MVC controller for Subumitting Playing XI");
		userService.saveplayingXI(player);
		// for returning update team players list
		List<PlayerCtcl> players = userService.getTeamPlayers();
		return new ResponseEntity<List<PlayerCtcl>>(players, HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/player/registration" }, method = RequestMethod.POST)
	public ResponseEntity<List<Player>> submitPlayerRegist(@RequestBody Player player) {
		System.out.println("IN App Controller : SubmitPlayerRegist Mathod");
		userService.savePlayerInfo(player);
		return new ResponseEntity<List<Player>>(HttpStatus.CREATED);
	}

	// Updating existing player
	@RequestMapping(value = { "/player/exist" }, method = RequestMethod.POST)
	public ResponseEntity<List<Player>> getPlayerInfo(@RequestBody Player player) {
		System.out.println("IN App Controller : getPlayerInfo Mathod");
		List<Player> existingPlayer = userService.getPlayerInfo(player);
		return new ResponseEntity<List<Player>>(existingPlayer, HttpStatus.OK);
	}

	// Match Basic score information

	@RequestMapping(value = { "/basic/scorecard/" }, method = RequestMethod.GET)
	public ResponseEntity<List<ScoreCardBasic>> basicScoreCard(@RequestParam int seasonId) {
		logger.info("In AppController.basicScoreCard(" + seasonId + ")");
		List<ScoreCardBasic> position = teamServiceMatch.getbasicScoreCard(seasonId);
		return new ResponseEntity<List<ScoreCardBasic>>(position, HttpStatus.OK);
	}

	// Match Detailed score information
	@RequestMapping(value = { "/detailed/scorecard/batting" }, method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> detailedScoreCard(@RequestParam int gameId) {
		logger.info("In AppController.basicScoreCard(" + gameId + ")");
		List<Map<String, Object>> detailedScore = teamServiceMatch.getDetailedScore(gameId);
		return new ResponseEntity<List<Map<String, Object>>>(detailedScore, HttpStatus.OK);
	}

	// Match Detailed bowling information
	@RequestMapping(value = { "/detailed/scorecard/bowling/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> bowlingDetails(@RequestParam int gameId) {
		logger.info("In AppController.bowlingDetails(" + gameId + ")");
		List<Map<String, Object>> detailedBowling = teamServiceMatch.getBowlingDetails(gameId);
		return new ResponseEntity<List<Map<String, Object>>>(detailedBowling, HttpStatus.OK);
	}

	// Match Detailed extra score information
	@RequestMapping(value = { "/detailed/scorecard/extras/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> extrasScoreDetails(@RequestParam int gameId) throws Exception {
		logger.info("In AppController.extrasScoreDetails(" + gameId + ")");
		List<Map<String, Object>> extrasDetails = teamServiceMatch.getExtraScoreDetails(gameId);
		return new ResponseEntity<List<Map<String, Object>>>(extrasDetails, HttpStatus.OK);
	}

	@RequestMapping(value = { "/teams/namue/list" }, method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> teamsName() throws Exception {
		// logger.info("In AppController.extrasScoreDetails(" + gameId + ")");
		List<Map<String, Object>> extrasDetails = teamServiceMatch.getTeamsName();
		return new ResponseEntity<List<Map<String, Object>>>(extrasDetails, HttpStatus.OK);
	}

	/************************ Start **********************************/
	/************* Submitting Game score details **********************/
	/************************ Start **********************************/

	@RequestMapping(value = { "/submit/score/step1" }, method = RequestMethod.POST)
	public ResponseEntity<Void> submitScore_step1(@RequestBody ScorecardGameDetails gameDetails,
			SubmitResults home_team, SubmitResults away_team) {
		System.out.println("In App Controller : submitScore_step1 Method");

		// Insert into the scorecard_game_details table
		teamServiceMatch.updateScorecardGameDetails(gameDetails);
		// submitting details for home teams
		home_team.setTeamID(gameDetails.getHometeam());
		away_team.setTeamID(gameDetails.getAwayteam());

		// When game is ForFeit
		if (gameDetails.getForfeit() == 1) {
			home_team.setPlayed(1);
			away_team.setPlayed(1);

			if (gameDetails.getResultWonId() != 0) {
				home_team.setTied(0);
				home_team.setNr(0);
				away_team.setTied(0);
				away_team.setNr(0);
			}

			if (gameDetails.getResultWonId() == gameDetails.getHometeam()) {
				home_team.setWon(1);
				home_team.setLost(0);
				away_team.setWon(0);
				away_team.setLost(1);

			} else if (gameDetails.getResultWonId() == gameDetails.getAwayteam()) {
				home_team.setWon(0);
				home_team.setLost(1);
				away_team.setWon(1);
				away_team.setLost(0);

			}
			teamServiceMatch.submitResults(home_team);
			teamServiceMatch.submitResults(away_team);
		}

		// submitting details for away teams

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = { "/submit/score/scorecardGameDetails" }, method = RequestMethod.POST)
	public ResponseEntity<Void> updateScorecardGameDetails(@RequestBody ScorecardGameDetails gameDetails)
			throws Exception {
		logger.info("In AppController.updateScorecardGameDetails" + gameDetails);
		int rows = teamServiceMatch.updateScorecardGameDetails(gameDetails);
		logger.info("rows#  " + rows);

		SubmitResults home_team = new SubmitResults();
		SubmitResults away_team = new SubmitResults();

		home_team.setTeamID(gameDetails.getHometeam());
		away_team.setTeamID(gameDetails.getAwayteam());
		/********* Default Values **********/
		/** HOME TEAM **/
		home_team.setPlayed(1);
		home_team.setWon(0);
		home_team.setLost(0);
		home_team.setTied(0);
		home_team.setNr(0);
		/** AWAY TEAM **/
		away_team.setPlayed(1);
		away_team.setWon(0);
		away_team.setLost(0);
		away_team.setTied(0);
		away_team.setNr(0);

		// Completed, ForFeit , Cancelled , Cancelled with some play or TIED
		if (gameDetails.getForfeit() == 1 || gameDetails.getCompleted() == 1) {
			if (gameDetails.getResultWonId() == gameDetails.getHometeam()) {
				home_team.setWon(1);
				away_team.setLost(1);
			} else if (gameDetails.getResultWonId() == gameDetails.getAwayteam()) {
				home_team.setLost(1);
				away_team.setWon(1);
			}
		} else if (gameDetails.getCancelled() == 1 || gameDetails.getCancelledplay() == 1) {
			home_team.setNr(1);
			away_team.setNr(1);

		} else if (gameDetails.getTied() == 1) {
			home_team.setTied(1);
			away_team.setTied(1);
		}

		teamServiceMatch.submitResults(home_team);
		teamServiceMatch.submitResults(away_team);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// Retrieving Match for next submit level i.e. to get game id
	@RequestMapping(value = { "/findMatchByPlayingTeamsAndDate" }, method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> findMatchByPlayingTeamsAndDate(@RequestParam int homeTeam,
			int awayTeam, Date matchDate) throws Exception {
		logger.info("In AppController.findMatchByPlayingTeamsAndDate(" + matchDate + ")");
		List<Map<String, Object>> match = teamServiceMatch.findMatchByDate(homeTeam, awayTeam, matchDate);
		return new ResponseEntity<List<Map<String, Object>>>(match, HttpStatus.OK);
	}

	// Retrieving players for score card
	@RequestMapping(value = { "/submit/score/players" }, method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> playersById() throws Exception {
		logger.info("In AppController.playersById");
		List<Map<String, Object>> playersList = teamServiceMatch.findPlayer();
		return new ResponseEntity<List<Map<String, Object>>>(playersList, HttpStatus.OK);
	}

	// Retrieving how out for score card
	@RequestMapping(value = { "/submit/score/howout" }, method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> howOut() throws Exception {
		logger.info("In AppController.playersById");
		List<Map<String, Object>> howOut = teamServiceMatch.findHowOut();
		return new ResponseEntity<List<Map<String, Object>>>(howOut, HttpStatus.OK);
	}

	@RequestMapping(value = { "/teams/players/teamsIds" }, method = RequestMethod.POST)
	public ResponseEntity<List<Map<String, Object>>> playerByIds(@RequestBody List<Integer> ids) throws Exception {
		logger.info("In AppController.playersByIds");
		List<Map<String, Object>> playersList = teamServiceMatch.findPlayerByIds(ids);
		return new ResponseEntity<List<Map<String, Object>>>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardExtrasDetails" }, method = RequestMethod.PUT)
	public ResponseEntity<Integer> updateScorecardExtrasDetails(@RequestBody SorecardExtrasDetails details)
			throws Exception {
		logger.info("In AppController.updateScorecardExtrasDetails");
		int playersList = teamServiceMatch.updateInsertScorecardExtrasDetails(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardTotalDetails" }, method = RequestMethod.PUT)
	public ResponseEntity<Integer> updateScorecardTotalDetails(@RequestBody ScorecardTotalDetails details)
			throws Exception {
		logger.info("In AppController.updateScorecardTotalDetails");
		int playersList = teamServiceMatch.updateInsertScorecardTotalDetails(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardFowDetails" }, method = RequestMethod.PUT)
	public ResponseEntity<Integer> updateScorecardFowDetails(@RequestBody ScorecardFowDetails details)
			throws Exception {
		logger.info("In AppController.updateScorecardFowDetails");
		int playersList = teamServiceMatch.updateInsertScorecardFowDetails1(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardBattingDetailss" }, method = RequestMethod.POST)
	public ResponseEntity<Integer> updateScorecardBattingDetails(
			@RequestBody ScorecardBatting battingdetails)
			throws Exception {
		int playersList = 0;
		logger.info("In AppController.ScorecardBattingDetails");
		/*
		 * for (ScorecardBatting details : battingdetails) {
		 * playersList = teamServiceMatch.updateScorecardBattingDetails(details.
		 * getBattingDetails());
		 * }
		 */
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardBowlingDetails" }, method = RequestMethod.PUT)
	public ResponseEntity<Integer> updateScorecardBowlingDetails(@RequestBody ScorecardBowling details)
			throws Exception {
		logger.info("In AppController.ScorecardBowlingDetails");
		Integer playersList = null;
		// int playersList =
		// teamServiceMatch.updateScorecardBowlingDetails(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}
}
