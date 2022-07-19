<!DOCTYPE html>
<html>
<html>
<head>
        <title>Pico Lite Blog</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="/picolitefe/app.css" />
</head>

<body ng-app="PicoLiteMVC">
<div class="container navbar">
        <div><a href="#!/">Home</a></div>
        <div><a href="#!/about">About</a></div>
</div>
<div class="container">
        <ng-view></ng-view>
</div>

<script src="/picolitefe/lib/angular/angular.js"></script>
<script src="/picolitefe/lib/angular/angular.js"></script>
<script src="/picolitefe/lib/angular-route/angular-route.js"></script>
<script src="/picolitefe/app.js"></script>
<script src="/picolitefe/services.js"></script>
<script src="/picolitefe/controllers.js"></script>
<script src="/picolitefe/lib/angular-sanitize/angular-sanitize.js"></script>
</body>
</html>