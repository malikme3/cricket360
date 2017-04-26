(function() {
	"use strict";
	angular.module('public').controller('HomeController', HomeController);
	HomeController.$inject = [ 'DataStoreService', 'PointsDataService' ];
	function HomeController(DataStoreService, PointsDataService) {
		var $ctrl = this;

		$ctrl.tableType = "Shabash !"
		$ctrl.tableHeader = PointsDataService.getTableHeader();

		// 20 overs
		$ctrl.team_group_20_A = PointsDataService.getTablePoints_2O_A();
		$ctrl.team_group_20_B = PointsDataService.getTablePoints_2O_B();

		// 30 overs
		$ctrl.team_group_35_red = PointsDataService.getTablePoints_35_red();
		$ctrl.team_group_35_blue = PointsDataService.getTablePoints_35_blue();
		$ctrl.team_group_35_white = PointsDataService.getTablePoints_35_white();

	}
	;
})();
