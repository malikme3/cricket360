(function() {
	"use strict";

	angular.module('public').controller('availabilityController', availabilityController);

	availabilityController.$inject = [ 'MenuService' ];
	function availabilityController(MenuService) {
		var $ctrl = this;
		$ctrl.options = [ "Available", "Not-Available", "Tentive", "Out of town" ];
		$ctrl.playerAvailablity = "Available";
		$ctrl.playerName = [];
		$ctrl.key_search = "";
		$ctrl.availablePlayer = [];
		$ctrl.notAvailablePlayer = [];
		$ctrl.OutOfTownPlayer = [];
		$ctrl.tentivePlayer = [];

		/* Date Start */
		$ctrl.checkDate = new Date();
		$ctrl.day = $ctrl.checkDate.getDay();
		$ctrl.fullDate = $ctrl.checkDate.toDateString();

		/* Date End */

		$ctrl.submittButton = false;
		$ctrl.isCaptain = true;

		/* For Team Selection */
		$ctrl.selectedPlayers = [];
		$ctrl.checkedPlayer = function checkedPlayer(fieldName) {
			var idx = $ctrl.selectedPlayers.indexOf(fieldName);
			$ctrl.players.forEach(function(component) {
				if (idx > -1) {
					if (component.player_firstName == fieldName) {
						var idxUnchecked = $ctrl.selectedPlayers.indexOf(fieldName);
						if (idxUnchecked != -1) {
							$ctrl.selectedPlayers.splice(idx, 1);
							component.editable = false;
							component.disabled = true;
						}
					}
				} else {
					if (component.player_firstName == fieldName) {
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

		MenuService.getTeamPlayers().then(function(response) {
			$ctrl.players = response;
		});

		$ctrl.playerClicked = function(player) {
			$ctrl.playerData = player;
			$ctrl.key_search = $ctrl.playerData.player_firstName + " " + $ctrl.playerData.player_lastName;
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
					if (aPlayer.player_firstName == select) {
						aPlayer.player_availability = "In Playing XI"
					}
				})

			})
			angular.forEach(player, function(aPlayer) {
				if (aPlayer.player_availability != "In Playing XI") {
					aPlayer.player_availability = "Not in Playing Xi"
				}
			})
			MenuService.submittingPlayingXI(player).then(function(response) {
				$ctrl.players = response;
			});
		}

	}

})();
