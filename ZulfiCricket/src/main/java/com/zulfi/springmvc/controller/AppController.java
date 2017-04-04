package com.zulfi.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.User;
import com.zulfi.springmvc.model.UserProfile;
import com.zulfi.springmvc.security.CustomUserDetailsService;
import com.zulfi.springmvc.service.UserProfileService;
import com.zulfi.springmvc.service.UserService;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "https://malikme3.github.io")
// @SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	/*@Autowired
	CustomUserDetailsService customUserDetailsService;*/

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	/**
	 * This method will list all existing users.
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET,  produces = "application/json")
	public ResponseEntity<List<User>> listUsers(Model model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return new ResponseEntity( model, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
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
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 * 
		 */
		if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
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

		model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
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

	@CrossOrigin(origins = "http://localhost:3000")
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
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/players/selection", method = RequestMethod.GET)
	public ResponseEntity<List<Player>> getTeamPlayers() {
		List<Player> players = userService.getTeamPlayers();
		return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
	}
	/*
	 * -------------------Submitting availability for team
	 * Selection--------------------------------------------------------
	 */

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/submit/availability", method = RequestMethod.POST)
	public ResponseEntity<List<Player>> submitPlayerForSelection(@RequestBody Player player, UriComponentsBuilder ucBuilder) {
		System.out.println("In Spring MVC controller for Submitting availability for team Selection");
		userService.savePlayerForSelection(player);
		List<Player> players = userService.getTeamPlayers();
		return new ResponseEntity<List<Player>>(players, HttpStatus.CREATED);
	}

	/* Submitting and retrieving selected player for Playing XI */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/submit/playingXI", method = RequestMethod.POST)
	public ResponseEntity<List<Player>> playingXI(@RequestBody Player[] player) {
		System.out.println("In Spring MVC controller for Subumitting Playing XI");
		userService.saveplayingXI(player);
		// for returning update team players list
		List<Player> players = userService.getTeamPlayers();
		return new ResponseEntity<List<Player>>(players, HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = { "/player/registration" }, method = RequestMethod.POST)
	public ResponseEntity<List<Player>> submitPlayerRegist(@RequestBody Player player) {
		System.out.println("IN App Controller : SubmitPlayerRegist Mathod");
		userService.savePlayerInfo(player);
		return new ResponseEntity<List<Player>>(HttpStatus.CREATED);
	}

	// Updating existing player
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = { "/player/exist" }, method = RequestMethod.POST)
	public ResponseEntity<List<Player>> getPlayerInfo(@RequestBody Player player) {
		System.out.println("IN App Controller : getPlayerInfo Mathod");
		List<Player> existingPlayer = userService.getPlayerInfo(player);
		return new ResponseEntity<List<Player>>(existingPlayer, HttpStatus.OK);
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