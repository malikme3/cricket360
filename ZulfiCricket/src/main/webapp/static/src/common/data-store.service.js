(function() {
	"use strict";
	angular.module('common').service('DataStoreService', DataStoreService).service('UserSessionInfo', UserSessionInfo);

	function DataStoreService() {
		var service = {
			loggedUser : "",
			roles : [],
			setLoggedUser : setLoggedUser,
			getLoggedUser : getLoggedUser,
			setRoles : setRoles,
			getRoles : getRoles
		};

		return service;

		function setLoggedUser(loggedinuser) {
			service.loggedUser = loggedinuser;
		}
		;

		function getLoggedUser() {
			return service.roles.push();
		}
		;

		function setRoles(loggedinuser) {
			service.roles = loggedinuser;
		}
		;

		function getRoles() {
			return service.roles;
		}
		;

	}
	;
	function UserSessionInfo() {
		var service = {
			accountNonExpired : false,
			accountNonLocked : false,
			credentialsNonExpired : false,
			enabled : true,
			username : "",
			authorities : [],
			setUserSession : setUserSession,
			getUserSession : getUserSession
		}
		return service;

		function setUserSession(session) {
			service.accountNonExpired = session.accountNonExpired;
			service.accountNonLocked = session.accountNonLocked;
			service.credentialsNonExpired = session.credentialsNonExpired;
			service.enabled = session.enabled;
			service.username = session.username;
			angular.forEach(session.authorities, function(auth) {
				service.authorities.push(auth.authority);
			})
		}
		function getUserSession() {
			return service;
		}

	}
	;

}());