package com.zulfi.springmvc.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Leagues;
import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.PlayerCtcl;
import com.zulfi.springmvc.model.Schedule;
import com.zulfi.springmvc.model.ScoreCardBasic;
import com.zulfi.springmvc.model.ScorecardBattingDetails;
import com.zulfi.springmvc.model.ScorecardBowlingDetails;
import com.zulfi.springmvc.model.ScorecardFowDetails;
import com.zulfi.springmvc.model.ScorecardGameDetails;
import com.zulfi.springmvc.model.ScorecardTotalDetails;
import com.zulfi.springmvc.model.Seasons;
import com.zulfi.springmvc.model.SorecardExtrasDetails;
import com.zulfi.springmvc.model.SubmitResults;
import com.zulfi.springmvc.model.Teams;
import com.zulfi.springmvc.model.User;
import com.zulfi.springmvc.model.UserProfile;
import com.zulfi.springmvc.model.UserSession;
import com.zulfi.springmvc.security.CustomUserDetailsService;
import com.zulfi.springmvc.service.TeamService;
import com.zulfi.springmvc.service.UserProfileService;
import com.zulfi.springmvc.service.UserService;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
// @SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService								userService;

	@Autowired
	UserProfileService						userProfileService;

	@Autowired
	TeamService								teamServiceMatch;

	@Autowired
	UserSession								userSession;

	@Autowired
	MessageSource							messageSource;

	/*
	 * @Autowired CustomUserDetailsService customUserDetailsService;
	 */

	@Autowired
	PersistentTokenBasedRememberMeServices	persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver				authenticationTrustResolver;

	static final Logger						logger	= LoggerFactory.getLogger(AppController.class);

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<User>> listUsers(Model model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		Object userSession = getUserSessionInfo();
		model.addAttribute("session", userSession);
		return new ResponseEntity(model, HttpStatus.OK);
	}

	@RequestMapping(value = { "user/sign" }, method = RequestMethod.POST)
	public String userLogin() {
		String userr = "Ahmad";

		User user = userService.findBySSO("Ahmad");

		CustomUserDetailsService play = new CustomUserDetailsService();

		UserDetails users = play.loadUserByUser(user);
		if (isCurrentAuthenticationAnonymous()) {
			System.out.println(" NOT authenticated ");
		} else {
			System.out.println(" Authenticated ");
		}
		;

		System.out.println("users  = " + users);
		System.out.println("loggedinuser  = " + getPrincipal());
		return "userslist";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be
		 * implementing custom @Unique annotation and applying it on field [sso]
		 * of Model class [User].
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 */
		if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId",
					new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "registrationsuccess";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in
		 * UI which is a unique key to a User.
		 * if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
		 * FieldError ssoError =new
		 * FieldError("user","ssoId",messageSource.getMessage(
		 * "non.unique.ssoId", new String[]{user.getSsoId()},
		 * Locale.getDefault())); result.addError(ssoError); return
		 * "registration"; }
		 */

		userService.updateUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/list";
		}
	}

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
		int playersList = teamServiceMatch.updateScorecardExtrasDetails(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardTotalDetails" }, method = RequestMethod.PUT)
	public ResponseEntity<Integer> updateScorecardTotalDetails(@RequestBody ScorecardTotalDetails details)
			throws Exception {
		logger.info("In AppController.updateScorecardTotalDetails");
		int playersList = teamServiceMatch.updateScorecardTotalDetails(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardFowDetails" }, method = RequestMethod.PUT)
	public ResponseEntity<Integer> updateScorecardFowDetails(@RequestBody ScorecardFowDetails details)
			throws Exception {
		logger.info("In AppController.updateScorecardFowDetails");
		int playersList = teamServiceMatch.updateScorecardFowDetails(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardBattingDetailss" }, method = RequestMethod.PUT)
	public ResponseEntity<Integer> updateScorecardBattingDetails(@RequestBody ScorecardBattingDetails details)
			throws Exception {
		logger.info("In AppController.ScorecardBattingDetails");
		int playersList = teamServiceMatch.updateScorecardBattingDetails(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateScorecardBowlingDetails" }, method = RequestMethod.PUT)
	public ResponseEntity<Integer> updateScorecardBowlingDetails(@RequestBody ScorecardBowlingDetails details)
			throws Exception {
		logger.info("In AppController.ScorecardBowlingDetails");
		int playersList = teamServiceMatch.updateScorecardBowlingDetails(details);
		return new ResponseEntity<Integer>(playersList, HttpStatus.OK);
	}
	/*
	 * updateScorecardExtrasDetails
	 * @RequestMapping(value = { "/submit/score/step1/gameDetails" }, method =
	 * RequestMethod.POST)
	 * public ResponseEntity<Void> submitScore_step1_gameDetails(@RequestBody
	 * ScorecardGameDetails gameDetails, SubmitResults away_team) {
	 * System.out.println(
	 * "In App Controller : submitScore_step1_gameDetails Method");
	 * // Insert into the scorecard_game_details table
	 * teamServiceMatch.submitScore_step1(gameDetails);
	 * return new ResponseEntity<Void>(HttpStatus.OK);
	 * }
	 */

	/************************ End **********************************/
	/************* Submitting Game score details **********************/
	/************************ End **********************************/

	// Getting session for existing player
	@RequestMapping(value = { "/user/session" }, method = RequestMethod.POST)
	public ResponseEntity<UserSession> getUserSessionInfo() {
		System.out.println("In App Controller : getUserSessionInfo Method");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			boolean isAccountNonLocked = ((UserDetails) principal).isAccountNonLocked();

			Collection<? extends GrantedAuthority> getAuthorities = ((UserDetails) principal).getAuthorities();

			boolean isCredentialsNonExpired = ((UserDetails) principal).isCredentialsNonExpired();
			boolean isEnabled = ((UserDetails) principal).isEnabled();
			String username = ((UserDetails) principal).getUsername();

			userSession.setAccountNonExpired(isAccountNonLocked);
			userSession.setAuthorities(getAuthorities);
			userSession.setCredentialsNonExpired(isCredentialsNonExpired);
			userSession.setEnabled(isEnabled);
			userSession.setUsername(username);
		} else {
			userSession.setAccountNonExpired(false);
			userSession.setAuthorities(null);
			userSession.setCredentialsNonExpired(false);
			userSession.setEnabled(false);
			userSession.setUsername(principal.toString());
		}

		if (isCurrentAuthenticationAnonymous()) {
			userSession.setLoggedin(false);
			userSession.setLoggedout(true);

		} else {
			userSession.setLoggedin(true);
			userSession.setLoggedout(false);
		}
		return new ResponseEntity(userSession, HttpStatus.OK);
	}

	/**
	 * This method handles logout requests. Toggle the handlers if you are
	 * RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response,
			// auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in],
	 * else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

}