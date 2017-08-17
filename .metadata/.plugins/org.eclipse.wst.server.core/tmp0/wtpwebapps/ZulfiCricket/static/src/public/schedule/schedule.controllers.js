(function() {
	'use strict';
	angular.module('common').controller('ScheduleController', ScheduleController);

	ScheduleController.$inject = [ 'UserService', 'DataStoreService' ];
	function ScheduleController(UserService, DataStoreService) {
		var scheduleCtrl = this
		scheduleCtrl.match_schedule = [];
		UserService.getSchedule().then(function(response) {
			scheduleCtrl.match_schedule = response;
		});
		console.log("In Schedule controller");
	}

})();