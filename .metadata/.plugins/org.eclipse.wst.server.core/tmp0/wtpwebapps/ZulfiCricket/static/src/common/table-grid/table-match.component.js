(function () {
    "use strict";
    angular.module('common').component('matchTable', {
        templateUrl: 'src/common/table-grid/match-table.html'
        , bindings: {
            data: '<'
        }
    }).component('pointTable', {
        templateUrl: 'src/common/table-grid/point-table.html'
        , bindings: {
            data: '<'
        }
    }).component('photoColumn', {
        templateUrl: 'src/common/table-grid/photo-column.html'
        , bindings: {
            data: '<'
        }
    }).component('photoColumn', {
        templateUrl: 'src/common/table-grid/photo-column.html'
        , bindings: {
            data: '<'
        }
    }).component('dynamicTable', {
        templateUrl: 'src/common/table-grid/dynamic-table.html'
        , bindings: {
            category: '<'
            , tables: '<'
            , header: '<'
        }
    });
})();