(function () {
"use strict";

angular.module('public')

.controller('TeamsController', TeamsController);

TeamsController.$inject = ['results', 'team', '$stateParams'];
function TeamsController(results, team, $stateParams) {
  var $ctrl = this;
  $ctrl.matchResults = results;
  $ctrl.team = team;
  $ctrl.name = $stateParams.teamName;
};



})();
