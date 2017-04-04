(function() {
"use strict";

/**
 * Restaurant module that includes the public module as a dependency
 */
angular.module('cricketApp', ['public'])
.config(config);

config.$inject = ['$urlRouterProvider', '$httpProvider'];
function config($urlRouterProvider, $httpProvider) {

  // If user goes to a path that doesn't exist, redirect to public root
  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
  $urlRouterProvider.otherwise('/');
}

})();
