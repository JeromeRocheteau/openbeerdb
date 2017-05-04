var app = angular.module('app',['chart.js']);

app.filter('ceil', function() {
	return function(input) {
		return Math.ceil(input);
	};
});

app.controller('controller', function ($scope, $http) {

	$scope.controls = {};
	$scope.controls.view = 'counts';
	$scope.controls.filters = [];
	$scope.controls.filtered = 0;
	
	$scope.labels = [];
	$scope.counts = [];
	$scope.averages = [];
	
	$scope.values = [];
	
	$scope.items = [];

	var stats = function() {
		var level = $scope.controls.filtered;
		var filter = level == 0 ? null : $scope.controls.filters[level - 1];
		$http({method:'GET',url:'/openbeerdb/stats',params:{"level":level,"filter":filter}})
		.then(function onSuccess(response) {
			$scope.items = response.data;
			process();
		}, function onError(response) {
			$scope.items = [];
		});
	}

	var process = function() {
		while ($scope.labels.length) { 
			$scope.labels.pop();
		}
		while ($scope.counts.length) {
			$scope.counts.pop();
		}
		while ($scope.averages.length) {
			$scope.averages.pop();
		}
		angular.forEach($scope.items, function(item, index) {
			$scope.labels.push(item.label);
			$scope.counts.push(item.count);
			$scope.averages.push(item.average);
		});
	}

	stats();

	$scope.select = function (points, evt) {
		var filter = points[0]._model.label;
		if ($scope.controls.filtered < 2) {
			$scope.controls.filters.push(filter);
			$scope.controls.filtered = $scope.controls.filtered + 1;
			stats();
		}
	};
	
	$scope.clear = function() {
		if ($scope.controls.filtered > 0) {
			$scope.controls.filters.pop();
			$scope.controls.filtered = $scope.controls.filtered - 1;
			stats();
		}
	}
	
	$scope.$watch('controls.view', function(n,o,s) {
		if ($scope.controls.view === 'counts') {
			$scope.values = $scope.counts;
		} else if ($scope.controls.view === 'averages') {
			$scope.values = $scope.averages;
		}
	});

});