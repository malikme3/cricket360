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

			if (service.roles) {
				var user_roles = service.roles.push()
			}
			return user_roles;
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
			userSession : "",
			setUserSession : setUserSession,
			getUserSession : getUserSession
		}
		return service;

		function setUserSession(session) {
			service.userSession = session;

		}
		function getUserSession() {
			return service.userSession;
		}

	}
	;

}());