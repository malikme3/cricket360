(function() {
	"use strict";
	angular.module('common').service('DataStoreService', DataStoreService).service('UserSessionInfo', UserSessionInfo).service(
			'PointsDataService', PointsDataService);

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

		function setRoles(loggedinuser) {
			service.roles = loggedinuser;
		}

		function getRoles() {
			return service.roles;
		}

	}
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
	/** ***** Point Table START ******* */

	function PointsDataService() {
		var service = {

			setTablePoints_2O_A : setTablePoints_2O_A,
			getTablePoints_2O_A : getTablePoints_2O_A,

			setTablePoints_2O_B : setTablePoints_2O_B,
			getTablePoints_2O_B : getTablePoints_2O_B,

			setTablePoints_35_red : setTablePoints_35_red,
			getTablePoints_35_red : getTablePoints_35_red,

			setTablePoints_35_white : setTablePoints_35_white,
			getTablePoints_35_white : getTablePoints_35_white,

			setTablePoints_35_blue : setTablePoints_35_blue,
			getTablePoints_35_blue : getTablePoints_35_blue,

			getTableHeader : getTableHeader,

			tableHeaders : [ {
				"column_1" : "Team Name",
				"column_2" : "Played",
				"column_3" : "Won",
				"column_4" : "Lost",
				"column_5" : "Tied",
				"column_6" : "NRR",
				"column_7" : "Total Point",
				"column_8" : "Penality"
			} ]

		};
		return service;

		function setTablePoints_2O_A(tablePoints_2O_A) {
			service.tablePoints_2O_A = tablePoints_2O_A;
		}

		function getTablePoints_2O_A() {
			return service.tablePoints_2O_A;
		}

		function setTablePoints_2O_B(tablePoints_2O_B) {
			service.tablePoints_2O_B = tablePoints_2O_B;
		}

		function getTablePoints_2O_B() {
			return service.tablePoints_2O_B;
		}

		function setTablePoints_35_red(tablePoints_35_red) {
			service.tablePoints_35_red = tablePoints_35_red;
		}

		function getTablePoints_35_red() {
			return service.tablePoints_35_red;
		}

		function setTablePoints_35_white(tablePoints_35_white) {
			service.tablePoints_35_white = tablePoints_35_white;
		}

		function getTablePoints_35_white() {
			return service.tablePoints_35_white;
		}

		function setTablePoints_35_blue(tablePoints_35_blue) {
			service.tablePoints_35_blue = tablePoints_35_blue;
		}

		function getTablePoints_35_blue() {
			return service.tablePoints_35_blue;
		}

		function getTableHeader() {
			return service.tableHeaders;
		}

	}

}());