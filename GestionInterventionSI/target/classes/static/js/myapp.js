var app = angular.module("myResponsableApp", []);
app.controller("myResponsableController", function($scope,$http) {
	$scope.Interventions=[];
	$scope.chargerInterventions = function(){
		$http.get("/interventions")
		.success(function(data){
			$scope.Interventions = data;
		});
	};
});
app.filter('dateToISO', function() {
  return function(input) {
    return new Date(input).toISOString();
  };
});