(function () {
    "use strict";
    angular.module('public').controller('HomeController', HomeController);
    //HomeController.$inject = [''];
    function HomeController() {
        var $ctrl = this;
        $ctrl.tableData = [
            {
                "id": "1"
                , "name": "ZABAIR"
                , "team": "Lion"
                , "score": 123
                , "matches": 8
                , "average": "30.2"
  }
            , {
                "id": "2"
                , "name": "MAJID"
                , "team": "Tiger Pro"
                , "score": 157
                , "matches": 9
                , "average": "25.5"
  }
            , {
                "id": "3"
                , "name": "BASIT"
                , "team": "Tiger Pro"
                , "score": 112
                , "matches": 11
                , "average": "22.9"
  }
]
        $ctrl.tableHeader = [
            {
                "id": "Postion"
                , "name": "Name"
                , "team": "Team"
                , "score": "Score"
                , "matches": "Matches"
                , "average": "Average"
  }
]
        $ctrl.tableType = "most-runs";
    };
})();
