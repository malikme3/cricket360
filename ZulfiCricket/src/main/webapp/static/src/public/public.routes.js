(function () {
    'use strict';
    angular.module('public').config(routeConfig);
    /**
     * Configures the routes and views
     */
    routeConfig.$inject = ['$stateProvider'];

    function routeConfig($stateProvider) {
        // Routes
        $stateProvider.state('public', {
                absract: true
                , templateUrl: 'src/public/public.html'
            }).state('public.landing', {
                url: '/'
                , templateUrl: 'src/public/home/landing.html'
                , controller: 'HomeController'
                , controllerAs: 'homeCtrl'
            }).state('public.home', {
                url: '/home'
                , templateUrl: 'src/public/home/home.html'
            }).state('public.menu', {
                url: '/menu'
                , templateUrl: 'src/public/results/result.html'
                , controller: 'ResultController'
                , controllerAs: 'menuCtrl'
                , resolve: {
                    menuCategories: ['MenuService', function (MenuService) {
                        return MenuService.getCategories();
                    }]
                }
            }).state('public.matches', {
                url: '/matches'
                , templateUrl: 'src/public/results/result.html'
                , controller: 'ResultController'
                , controllerAs: 'menuCtrl'
                , resolve: {
                    results: ['MenuService', function (MenuService) {
                        return MenuService.getMatchesResult();
                    }]
                }
            })
            // For Club's teams
            .state('public.teams', {
                url: '/club/:teamName'
                , templateUrl: 'src/public/team/teams.html'
                , controller: 'TeamsController'
                , controllerAs: 'teamsCtrl'
                , resolve: {
                    results: ['TeamService', function (TeamService) {
                        return TeamService.getComingMatches();
                }]
                    , team: ['$stateParams', 'TeamService', function ($stateParams, TeamService) {
                        return TeamService.getSource($stateParams.teamName);
                }]
                }
            }).state('public.availability', {
                url: '/availability'
                , templateUrl: 'src/public/availability/availability.html'
                , controller: 'availabilityController'
                , controllerAs: 'availabilityCtrl'
            })
            // I add this - begin
            .state('public.myinfo', {
                url: '/register/myinfo'
                , templateUrl: 'src/public/registered/my-info.html'
                , controller: 'MyInfoController'
                , controllerAs: 'myInfCtrl'
                , resolve: {
                    favoriteItem: ['UserService', function (UserService) {
                        if ((UserService.userInfo.fave === undefined) || (UserService.userInfo.fave === "")) {
                            var favoriteItem;
                            favoriteItem.short_name = "";
                            favoriteItem.description = "";
                            favoriteItem.name = "";
                            return favoriteItem;
                        }
                        else {
                            return UserService.getMenuItem(UserService.userInfo.fave);
                        }
                    }]
                }
            }).state('public.signup', {
                url: '/register/signup'
                , templateUrl: 'src/public/registered/signup.html'
                , controller: 'SignUpController'
                , controllerAs: 'signUpCtrl'
            })
            //For login
            .state('public.login', {
                url: '/register/login'
                , templateUrl: 'src/public/registered/login.html'
                , controller: 'LogInController'
                , controllerAs: 'logInCtrl'
            })
            // I add this - end
            .state('public.menuitems', {
                url: '/menu/{category}'
                , templateUrl: 'src/public/menu-items/menu-items.html'
                , controller: 'MenuItemsController'
                , controllerAs: 'menuItemsCtrl'
                , resolve: {
                    menuItems: ['$stateParams', 'MenuService', function ($stateParams, MenuService) {
                        return MenuService.getMenuItems($stateParams.category);
                }]
                }
            });
    }
})();