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

	$scope.style = {};
	$scope.styles = [];
	$scope.categories = [];
	$scope.size = 0;
	$scope.offset = 0;
	$scope.length = 5;

	var init = function() {
		$scope.selected = false;
		$scope.creating = false;
		$scope.updating = false;
		$scope.style = angular.copy(empty);
	}

	$scope.clean = function() {
		init();
	}

	$scope.select = function(style) {
		if (style) {
			$scope.creating = false;
			$scope.updating = true;
			$scope.style = angular.copy(style);
		} else {
			$scope.creating = true;
			$scope.updating = false;
			$scope.style = angular.copy(empty);
		}
		$scope.selected = true;
	}

	$scope.create = function() {
		var datacontent = "name=" + $scope.style.name + "&category=" + $scope.style.category.id;
		$http({method:'POST',url:'./styles/create',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			size();
			$scope.offset = 0;
			page();
		}, function onError(response) {

		}); 
	}

	$scope.update = function() {
		var datacontent = "id=" + $scope.style.id + "&name=" + $scope.style.name + "&category=" + $scope.style.category.id; 
		$http({method:'POST',url:'./styles/update',data:datacontent,headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			page();
		}, function onError(response) {

		}); 
	}

	$scope.remove = function() {
		var datacontent = "id=" + $scope.style.id; 
		$http({method:'POST',url:'./styles/delete',data:datacontent,headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
		.then(function onSuccess(response) {
			init();
			size();
			$scope.offset = 0;
			page();
		}, function onError(response) {

		}); 
	}

	var list = function() {
		$http({method:'GET',url:'./styles/list'})
		.then(function onSuccess(response) {
			$scope.categories = response.data;
		}, function onError(response) {
			$scope.categories = [];
		});
	}

	var size = function() {
		$http({method:'GET',url:'./styles/size'})
		.then(function onSuccess(response) {
			$scope.size = response.data;
		}, function onError(response) {
			$scope.size = 0;
		});  			
	}

	var page = function() {
		$http({method:'GET',url:'./styles/page',params:{"offset":$scope.offset,"length":$scope.length}})
		.then(function onSuccess(response) {
			$scope.styles = response.data;
		}, function onError(response) {
			$scope.styles = [];
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

	$scope.category = function(category) {
		$scope.style.category = category;
	}

	init();
	list();
	size();
	page();

});