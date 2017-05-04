var app = angular.module('app',[]);

app.filter('ceil', function() {
	return function(input) {
		return Math.ceil(input);
	};
});

app.controller('controller', function ($scope, $http) {

	var empty = {};

	$scope.selected = false;
	$scope.creating = false;
	$scope.updating = false;

	$scope.beer = {};
	$scope.beers = [];
	$scope.breweries = [];
	$scope.size = 0;
	$scope.offset = 0;
	$scope.length = 5;

	var init = function() {
		$scope.selected = false;
		$scope.creating = false;
		$scope.updating = false;
		$scope.beer = angular.copy(empty);
	}

	$scope.clean = function() {
		init();
	}

	$scope.select = function(beer) {
		if (beer) {
			$scope.creating = false;
			$scope.updating = true;
			$scope.beer = beer;
		} else {
			$scope.creating = true;
			$scope.updating = false;
			$scope.beer = angular.copy(empty);
		}
		$scope.selected = true;
	}

	$scope.create = function() {
		var datacontent = "name=" + $scope.beer.name; 
		$http({method:'POST',url:'/openbeerdb/beers/create',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			size();
			$scope.offset = 0;
			page();
		}, function onError(response) {

		}); 
	}

	$scope.update = function() {
		var datacontent = "id=" + $scope.beer.id + "&name=" + $scope.beer.name + "&abv=" + $scope.beer.abv + "&brewery=" + $scope.brewery.id; 
		$http({method:'POST',url:'/openbeerdb/beers/update',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
		}, function onError(response) {

		}); 
	}

	$scope.remove = function() {
		var datacontent = "id=" + $scope.brewery.id; 
		$http({method:'POST',url:'/openbeerdb/beers/delete',data:datacontent,headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			size();
			$scope.offset = 0;
			page();
		}, function onError(response) {

		}); 
	}

	var list = function() {
		$http({method:'GET',url:'/openbeerdb/breweries/list'})
		.then(function onSuccess(response) {
			$scope.breweries = response.data;
		}, function onError(response) {
			$scope.breweries = [];
		});
	}

	var size = function() {
		$http({method:'GET',url:'/openbeerdb/beers/size'})
		.then(function onSuccess(response) {
			$scope.size = response.data;
		}, function onError(response) {
			$scope.size = 0;
		});  			
	}

	var page = function() {
		$http({method:'GET',url:'/openbeerdb/beers/page',params:{"offset":$scope.offset,"length":$scope.length}})
		.then(function onSuccess(response) {
			$scope.beers = response.data;
		}, function onError(response) {
			$scope.beers = [];
		});
	}

	$scope.prev = function() {
		if ($scope.length <= $scope.offset) {
			$scope.offset = $scope.offset - $scope.length;
			page();
		}
	}

	$scope.next = function() {
		if ($scope.offset + $scope.length < $scope.size) {
			$scope.offset = $scope.offset + $scope.length;
			page();			
		}
	}

	$scope.first = function() {
		$scope.offset = 0;
		page();
	}

	$scope.last = function() {
		$scope.offset = Math.floor(($scope.size - 1) / $scope.length) * $scope.length;
		page();
	}

	init();
	list();
	size();
	page();

});