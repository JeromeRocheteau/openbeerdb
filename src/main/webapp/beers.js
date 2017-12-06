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

	$scope.beer = {};
	$scope.beers = [];
	$scope.styles = [];
	$scope.breweries = [];
	$scope.brands = [];
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
			$scope.updating = beer.user === $scope.username;
			$scope.beer = angular.copy(beer);
			if ($scope.beer.brewery) {
			  brands($scope.beer.brewery);
			}
		} else {
			$scope.creating = true;
			$scope.updating = false;
			$scope.beer = angular.copy(empty);
		}
		$scope.selected = true;
	}

	$scope.create = function() {
		var datacontent = "name=" + $scope.beer.name + "&abv=" + $scope.beer.abv + "&brewery=" + $scope.beer.brewery.id
		+ ($scope.beer.brand ? "&brand=" + $scope.beer.brand.id : "");
		$http({method:'POST',url:'./beers/create',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
		  var id = response.data;
	      angular.forEach($scope.beer.styles, function (style) { feature(id, style.id); });
		  init();
		  size();
		  $scope.last();
		}, function onError(response) {

		}); 
	}

	$scope.update = function() {
		var datacontent = "id=" + $scope.beer.id + "&name=" + $scope.beer.name + "&abv=" + $scope.beer.abv + "&brewery=" + $scope.beer.brewery.id
		+ ($scope.beer.brand ? "&brand=" + $scope.beer.brand.id : "");
		$http({method:'POST',url:'./beers/update',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
	      clear($scope.beer.id);
	      angular.forEach($scope.beer.styles, function (style) { feature($scope.beer.id, style.id); });
		  init();
		  page();
		}, function onError(response) {

		}); 
	}

	$scope.remove = function() {
		var datacontent = "id=" + $scope.beer.id; 
		$http({method:'POST',url:'./beers/delete',data:datacontent,headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
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
		$http({method:'GET',url:'./styles/list'})
		.then(function onSuccess(response) {
			$scope.styles = response.data;
		}, function onError(response) {
			$scope.styles = [];
		});
	}

	var size = function() {
		$http({method:'GET',url:'./beers/size'})
		.then(function onSuccess(response) {
			$scope.size = response.data;
		}, function onError(response) {
			$scope.size = 0;
		});  			
	}

	var page = function() {
	  $http({method:'GET',url:'./beers/page',params:{"offset":$scope.offset,"length":$scope.length}})
	  .then(function onSuccess(response) {
	    $scope.beers = response.data;
	    angular.forEach($scope.beers, function (beer) { styles(beer); });
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

	var brands = function(brewery) {
	  var content = "?brewery=" + brewery.id; 
	  $http({method:'GET',url:'./brands/list' + content})
	  .then(function onSuccess(response) {
	    $scope.brands = response.data;
	  }, function onError(response) {
	    $scope.brands = [];
	  });
	}
	
	var styles = function(beer) {
	  $http({method:'GET',url:'./features/list',params:{"beer":beer.id}})
	  .then(function onSuccess(response) {
	    beer.styles = response.data;
	  }, function onError(response) {
		  beer.styles = [];
	  });
	}

	var clear = function(beer) {
	  var datacontent = "beer=" + beer; 
	  $http({method:'POST',url:'./features/delete',data:datacontent,headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
	  .then(function onSuccess(response) {
	  }, function onError(response) {
	  }); 
	}
	
	var feature = function(beer, style) {
	  var datacontent = "beer=" + beer + "&style=" + style; 
	  $http({method:'POST',url:'./features/create',data:datacontent,headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
	  .then(function onSuccess(response) {
	  }, function onError(response) {
	  }); 
	}
	
	$scope.brewery = function(brewery) {
	  $scope.beer.brewery = brewery;
	  brands(brewery);
	}

	$scope.brand = function(brand) {
		$scope.beer.brand = brand;
	}
	
	$scope.labels = function(styles) {
		$scope.beer.styles = styles;
	}

	init();
	list();
	size();
	page();

});