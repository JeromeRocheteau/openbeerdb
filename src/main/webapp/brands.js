var app = angular.module('app',[]);

app.filter('ceil', function() {
	return function(input) {
		return Math.ceil(input);
	};
});

app.controller('controller', function ($scope, $http) {

	$scope.username = null;
	$scope.grants = {};
	$scope.grants.admin = false;

	var check = function() {
	  $http({method:'GET',url:'./username'})
	  .then(function onSuccess(response) {
	    $scope.username = response.data;
	  }, function onError(response) {
	    $scope.username = null;
	  });
	  $http({method:'GET',url:'./rolename'})
	  .then(function onSuccess(response) {
	    $scope.grants.admin = response.data;
	  }, function onError(response) {
	    $scope.grants.admin = false;
	  });
	}

	check();
	
	var empty = {};

	$scope.selected = false;
	$scope.creating = false;
	$scope.updating = false;

	$scope.brand = {};
	$scope.brands = [];
	$scope.breweries = [];
	$scope.size = 0;
	$scope.offset = 0;
	$scope.length = 5;

	var init = function() {
		$scope.selected = false;
		$scope.creating = false;
		$scope.updating = false;
		$scope.brand = angular.copy(empty);
	}

	$scope.clean = function() {
		init();
	}

	$scope.select = function(brand) {
		if (brand) {
			$scope.creating = false;
			$scope.updating = true;
			$scope.brand = angular.copy(brand);
		} else {
			$scope.creating = true;
			$scope.updating = false;  			
			$scope.brand = angular.copy(empty);
		}
		$scope.selected = true;
	}

	$scope.create = function() {
		var datacontent = "name=" + $scope.brand.name + "&brewery=" + $scope.brand.brewery.id; 
		$http({method:'POST',url:'./brands/create',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			size();
			$scope.last();
		}, function onError(response) {

		}); 
	}

	$scope.update = function() {
		var datacontent = "id=" + $scope.brand.id + "&name=" + $scope.brand.name + "&brewery=" + $scope.brand.brewery.id; 
		$http({method:'POST',url:'./brands/update',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			page();
		}, function onError(response) {

		}); 
	}

	$scope.remove = function() {
		var datacontent = "id=" + $scope.brand.id; 
		$http({method:'POST',url:'./brands/delete',data:datacontent,headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			size();
            if (Math.ceil($scope.size / $scope.length) < $scope.offset) {
			  $scope.offset = $scope.offset - $scope.length;
		    }
			page();
		}, function onError(response) {

		}); 
	}

	var list = function() {
		$http({method:'GET',url:'./breweries/list'})
		.then(function onSuccess(response) {
			$scope.breweries = response.data;
		}, function onError(response) {
			$scope.breweries = [];
		});
	}

	var size = function() {
		$http({method:'GET',url:'./brands/size'})
		.then(function onSuccess(response) {
			$scope.size = response.data;
		}, function onError(response) {
			$scope.size = 0;
		});  			
	}

	var page = function() {
		$http({method:'GET',url:'./brands/page',params:{"offset":$scope.offset,"length":$scope.length}})
		.then(function onSuccess(response) {
			$scope.brands = response.data;
		}, function onError(response) {
			$scope.brands = [];
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
		if ($scope.offset > 0) {
			$scope.offset = 0;
			page();
		}
	}

	$scope.last = function() {
		if ($scope.offset < Math.floor(($scope.size - 1) / $scope.length) * $scope.length) {
			$scope.offset = Math.floor(($scope.size - 1) / $scope.length) * $scope.length;
			page();
		}
	}

	$scope.brewery = function(brewery) {
		$scope.brand.brewery = brewery;
	}

	init();
	list();
	size();
	page();

});