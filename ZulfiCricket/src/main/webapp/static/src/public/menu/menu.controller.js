(function () {
"use strict";

angular.module('public')
.controller('MenuController', MenuController);

MenuController.$inject = ['results'];
function MenuController(results) {
  var $ctrl = this;
  $ctrl.menuCategories = results;
}


})();
