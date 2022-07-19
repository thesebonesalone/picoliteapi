angular.module('PicoLiteMVC.services', [])
.factory('articleApiService', function($http) {
    var articleApi = {};

    articleApi.getArticles = function() {


        let test = $http.get('http://localhost:8080/picolitemvc/articles');
        return test;
    }

    articleApi.getArticle = function(id) {
        return $http.get('http://localhost:8080/picolitemvc/articles/' + id);
    }
    return articleApi;
})