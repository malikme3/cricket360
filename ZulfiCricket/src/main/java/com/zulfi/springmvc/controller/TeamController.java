package com.zulfi.springmvc.controller;

import java.util.Collection;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Leagues;
import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.PlayerCtcl;
import com.zulfi.springmvc.model.Seasons;
import com.zulfi.springmvc.model.Teams;
import com.zulfi.springmvc.model.User;
import com.zulfi.springmvc.model.UserProfile;
import com.zulfi.springmvc.model.UserSession;
import com.zulfi.springmvc.security.CustomUserDetailsService;
import com.zulfi.springmvc.service.UserProfileService;
import com.zulfi.springmvc.service.UserService;

@Controller(value = "TeamController")
@CrossOrigin(origins = "https://malikme3.github.io")
// @SessionAttributes("roles")
public class TeamController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UserSession userSession;

	@Autowired
	MessageSource messageSource;

	/*
	 * @Autowired CustomUserDetailsService customUserDetailsService;
	 */

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { "/teams/teams2" }, method = RequestMethod.GET)
	public Teams teambyId() {

		Teams team = null;
		System.out.println("IN Team Controller : Yahoo");

		return team;

	};

	/*
	 * @RequestMapping(value = { "/edit-user-{ssoId}" }, method =
	 * RequestMethod.GET) public String editUser(@PathVariable String ssoId,
	 * ModelMap model) { User user = userService.findBySSO(ssoId); return
	 * "registration"; }
	 * 
	 *//**
		 * This method will delete an user by it's SSOID value.
		 *//*
		 * @RequestMapping(value = { "/delete-user-{ssoId}" }, method =
		 * RequestMethod.GET) public String deleteUser(@PathVariable String
		 * ssoId) { userService.deleteUserBySSO(ssoId); return "redirect:/list";
		 * }
		 */

}