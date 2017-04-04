(function () {
"use strict";

angular.module('public')
//.controller('TeamsController', TeamsController)
.controller('ResultController', ResultController);

ResultController.$inject = ['results'];
function ResultController(results) {
  var $ctrl = this;
  $ctrl.matchResults = results;
};

// TeamsController.$inject = ['results', 'team'];
// function TeamsController(results, team) {
//   var $ctrl = this;
//   $ctrl.teamName="";
//   $ctrl.matchResults = results;
//   $ctrl.team = team;
// }


})();
