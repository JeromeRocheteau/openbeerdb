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
	  $http({method:'GET',url:'/openbeerdb/username'})
	  .then(function onSuccess(response) {
	    $scope.username = response.data;
	  }, function onError(response) {
	    $scope.username = null;
	  });
	  $http({method:'GET',url:'/openbeerdb/rolename'})
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

	$scope.brewery = {};
	$scope.breweries = [];
	$scope.size = 0;
	$scope.offset = 0;
	$scope.length = 5;

	var init = function() {
		$scope.selected = false;
		$scope.creating = false;
		$scope.updating = false;
		$scope.brewery = angular.copy(empty);
	}

	$scope.clean = function() {
		init();
	}

	$scope.select = function(brewery) {
		if (brewery) {
			$scope.creating = false;
			$scope.updating = brewery.user === $scope.username;
			$scope.brewery = angular.copy(brewery);
		} else {
			$scope.creating = true;
			$scope.updating = false;  			
			$scope.brewery = angular.copy(empty);
		}
		$scope.selected = true;
	}

	$scope.create = function() {
		var datacontent = "name=" + $scope.brewery.name + ($scope.brewery.address ? "&address=" + $scope.brewery.address : "") + "&city=" + $scope.brewery.city + "&country=" + $scope.brewery.country; 
		$http({method:'POST',url:'/openbeerdb/breweries/create',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			size();
			$scope.last();
			page();
		}, function onError(response) {

		}); 
	}

	$scope.update = function() {
		var datacontent = "id=" + $scope.brewery.id + "&name=" + $scope.brewery.name + ($scope.brewery.address ? "&address=" + $scope.brewery.address : "") + "&city=" + $scope.brewery.city + "&country=" + $scope.brewery.country; 
		$http({method:'POST',url:'/openbeerdb/breweries/update',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			page();
		}, function onError(response) {

		}); 
	}

	$scope.remove = function() {
		var datacontent = "id=" + $scope.brewery.id; 
		$http({method:'POST',url:'/openbeerdb/breweries/delete',data:datacontent,headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
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

	var size = function() {
		$http({method:'GET',url:'/openbeerdb/breweries/size'})
		.then(function onSuccess(response) {
			$scope.size = response.data;
		}, function onError(response) {
			$scope.size = 0;
		});  			
	}

	var page = function() {
		$http({method:'GET',url:'/openbeerdb/breweries/page',params:{"offset":$scope.offset,"length":$scope.length}})
		.then(function onSuccess(response) {
			$scope.breweries = response.data;
		}, function onError(response) {
			$scope.breweries = [];
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

	init();
	size();
	page();

});