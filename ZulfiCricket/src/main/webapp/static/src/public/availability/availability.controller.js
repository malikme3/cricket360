(function() {
	"use strict";

	angular.module('public').controller('availabilityController', availabilityController);

	availabilityController.$inject = [ 'MenuService', 'DataStoreService', 'UserSessionInfo', 'UserService' ];
	function availabilityController(MenuService, DataStoreService, UserSessionInfo, UserService) {
		var $ctrl = this;
		$ctrl.options = [ "Available", "Not-Available", "Tentive", "Out of town" ];
		$ctrl.playerAvailablity = "Available";
		$ctrl.playerName = [];
		$ctrl.key_search = "";
		$ctrl.availablePlayer = [];
		$ctrl.notAvailablePlayer = [];
		$ctrl.OutOfTownPlayer = [];
		$ctrl.tentivePlayer = [];
		$ctrl.Admin = false;
		$ctrl.isTeamSelected = false;
		$ctrl.Dba = false;
		$ctrl.User = false;
		$ctrl.isAvailabilityOn = false;

		/* Date Start */
		$ctrl.checkDate = new Date();
		$ctrl.day = $ctrl.checkDate.getDay();
		$ctrl.fullDate = $ctrl.checkDate.toDateString();

		/* Date End */

		$ctrl.submittButton = false;
		$ctrl.isCaptain = true;

		/* For Team Selection */
		$ctrl.selectedPlayers = [];
		$ctrl.NotSelectedPlayers = [];
		$ctrl.checked_selected_player = function checked_selected_player(fieldName) {
				var idx = $ctrl.selectedPlayers.indexOf(fieldName);
				$ctrl.players.forEach(function(component) {
					if (idx > -1) {
						if (component.playerFName == fieldName) {
							var idxUnchecked = $ctrl.selectedPlayers.indexOf(fieldName);
							if (idxUnchecked != -1) {
								$ctrl.selectedPlayers.splice(idx, 1);
								component.editable = false;
								component.disabled = true;
							}
						}
					} else {
						if (component.playerFName == fieldName) {
							var idxChecked = $ctrl.selectedPlayers.indexOf(fieldName);
							if (idxChecked == -1) {
								$ctrl.selectedPlayers.push(fieldName);
								component.editable = true;
								component.disabled = false;
							}
						}
					}
				});
		};
		$ctrl.checked_not_selected_player = function checked_not_selected_player(fieldName) {
			var idx = $ctrl.NotSelectedPlayers.indexOf(fieldName);
			$ctrl.players.forEach(function(component) {
				if (idx > -1) {
					if (component.playerFName == fieldName) {
						var idxUnchecked = $ctrl.NotSelectedPlayers.indexOf(fieldName);
						if (idxUnchecked != -1) {
							$ctrl.NotSelectedPlayers.splice(idx, 1);
							component.editable = false;
							component.disabled = true;
						}
					}
				} else {
					if (component.playerFName == fieldName) {
						var idxChecked = $ctrl.NotSelectedPlayers.indexOf(fieldName);
						if (idxChecked == -1) {
							$ctrl.NotSelectedPlayers.push(fieldName);
							component.editable = true;
							component.disabled = false;
						}
					}
				}
			});
	};
		MenuService.getTeamPlayers().then(function(response) {
			$ctrl.players = response;
		});

		$ctrl.playerClicked = function(player) {
			$ctrl.playerData = player;
			$ctrl.key_search = $ctrl.playerData.playerFName + " " + $ctrl.playerData.playerLName;
			$ctrl.submittButton = true;
		}

		$ctrl.submitAvailability = function(availability) {
			var player = $ctrl.playerData;
			MenuService.playerForSelection(player, availability).then(function(response) {
				$ctrl.players = response;
			});
			MenuService.getTeamPlayers().then(function(response) {
				$ctrl.players = response;
			});
		}
		$ctrl.submitSelection = function(player) {
			angular.forEach(player, function(aPlayer) {
				angular.forEach($ctrl.selectedPlayers, function(select) {
					if (aPlayer.playerFName == select) {
						aPlayer.playerAvailability = "In Playing XI"
					}
				})
				angular.forEach($ctrl.NotSelectedPlayers, function(select) {
					if (aPlayer.playerFName == select) {
						aPlayer.playerAvailability = "Not Playing XI"
					}
				})

			})
/*			angular.forEach(player, function(aPlayer) {
				if (aPlayer.playerAvailability != "In Playing XI") {
					aPlayer.playerAvailability = "Not in Playing Xi"
				}
			})*/
			MenuService.submittingPlayingXI(player).then(function(response) {
				$ctrl.players = response;
				$ctrl.isTeamSelected = true;
			});

		};

		var userSession = UserSessionInfo.getUserSession();
		$ctrl.roles = userSession.authorities;

		angular.forEach($ctrl.roles, function(role) {

			if (role.authority == "ROLE_ADMIN") {
				$ctrl.Admin = true;
			} else if (role == "ROLE_DBA") {
				$ctrl.Dba = true;
			} else {
				$ctrl.User = true;
			}
		})

		$ctrl.logoutSubmit = function logoutSubmit() {

			console.log("Submitting request for logout");
			UserService.goLogout().then(function(response) {
				$ctrl.players = response;
				$ctrl.loggedout = true;
				// For setting user Session
				UserService.getUserSessionInfo().then(function(response) {
					console.log("In Availability Controller : User session from middle tier is : " + response);
					if (!response == undefined) {
						UserSessionInfo.setUserSession(response);
					}

				});
				$ctrl.userSession = UserSessionInfo.getUserSession();
			});
		}

	}

})();
