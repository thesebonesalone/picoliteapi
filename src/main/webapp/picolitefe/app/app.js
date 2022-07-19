angular.module('PicoLiteMVC', ['PicoLiteMVC.controllers', 'PicoLiteMVC.services', 'ngRoute'])
    .config(function($sceDelegateProvider, $routeProvider) {
    $sceDelegateProvider.resourceUrlWhitelist(['self','http://localhost:8080/**']);
    $routeProvider.when("/home", {templateUrl: "templates/home.html", controller: "articlesController"})
        .when("/article/:id", {templateUrl: "templates/article.html", controller: "articlesController"})
        .when("/about", {templateUrl: "templates/about.html"})
        .otherwise({redirectTo: '/home'});
})
