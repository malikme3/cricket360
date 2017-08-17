(function () {
    "use strict";
    angular.module('common').service('MenuService', MenuService);
    MenuService.$inject = ['$http', 'ApiPath', '$q'];

    function MenuService($http, ApiPath, $q) {
        var service = this;
        service.getTopScre = function () {
            var score = [
                {
                    "header": [
      "laboris"
      , "cillum"
      , "sunt"
      , "officia"
      , "ipsum"
      , "id"
      , "excepteur"
    ]
                    , "rows": [
      "labore"
      , "aliqua"
      , "eiusmod"
      , "aliqua"
      , "esse"
      , "ut"
      , "officia"
    ]
  }
];
        }
        service.getMatchesResult = function () {
            var data = []
            return data;
        };
    }
})();