angular.module('PicoLiteMVC.controllers', ['ngSanitize'])
.controller('articlesController', function($scope, articleApiService) {
    $scope.articles = [];

    $scope.shownArticles = [];

    $scope.warning = false;

    $scope.message = "";

    $scope.user = "";
    $scope.comment = "";

    articleApiService.getArticles().then(function(response) {
        $scope.articles = response.data.articles;
    }).finally(() => {
        $scope.update("");
    })

    $scope.update = (filter) => {
        $scope.shownArticles = $scope.articles.filter((e) => {
            return e.title.toLowerCase().includes(filter.toLowerCase());
        })
    }


})
.controller('articleController', ($scope, $routeParams, $route, articleApiService) => {
    $scope.id = $routeParams.id;
    $scope.articleObject = {article: null}

    articleApiService.getArticle($scope.id).then((resp) => {
        $scope.articleObject.article = resp.data;
    })

    $scope.comments = [];

    $scope.update = function(event, comment, user)
    {
        if ($scope.user === "" || $scope.comment === "")
        {
            $scope.warning = true;
            $scope.message = "Please fill out required forms!";
            event.preventDefault();
            return;
        }
        $scope.comments.push({comment: comment, name: user});
    }

    $scope.deferWarning = function()
    {
        $scope.warning = false;
        $scope.message = "";
    }
})