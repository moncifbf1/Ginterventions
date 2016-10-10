var myApp = angular.module('UtilisateurApp',[]);
myApp.controller('UtilisateurApp',['$http','$scope','$window','$location', function($http,$scope,$window,$location) {
	var codeLoggedUser = null;
	$scope.getLoggedUser = function(){
		$http.get('/getLoggedUser')
		.success(function(data){
			codeLoggedUser = data.username;
			$http.get("/utilisateur/"+codeLoggedUser)
			.success(function(data){
				$scope.User=data;
			})
			.error(function(data) {
				alert("Erreur chargement des données de l'utilisateur");
			});
		})
		.error(function(data) {
			alert("Erreur chargement code de l'utilisateur!");
		});
	};
	
	$scope.addIntervention = function(){
		var modelName = null;
		var fournisseurName = null;
		$http.get('/getModel/'+$scope.Intervention.model)
		.success(function(data){
			modelName = data.nom_model;
			fournisseurName = data.fournisseur.nom_fournisseur;
			var dataObj = {
					titre_intervention : $scope.Intervention.titre,
					commentIntervention : $scope.Intervention.comment,
					etat_intervention : 0,
					interveModel: modelName,
					intervFournisseur: fournisseurName,
					idModel: $scope.Intervention.model,
					utilisateur: $scope.User
				};	
			$http({
			    method: 'POST',
			    url: '/addIntervention',
			    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
			    data: dataObj
			})
			.success(function(data, status, headers, config) {
				console.log(dataObj);
				console.log(data);
				alert("Intervention ajoutée avec succès!");
			})
			.error(function(data, status, header, config) {
				alert("Erreur lors de l'ajout de l'intervention!");
			});
		});
	};
	
	$scope.chargerFournisseurs = function(){
		$http.get('/findFournisseurs')
		.success(function(data){
			$scope.Fournisseurs = data;
		})
		.error(function(data) {
			alert("Erreur chargement des données du message");
		});
	};
	
	$scope.chargerModels = function(item){
		$http.get('/getModelByFournisseur/'+item)
		.success(function(data){
			$scope.Models = data;
		})
		.error(function(data) {
			alert("Erreur chargement du fournisseur");
		});
	};
	
	$scope.onChangeFournisseur = function(item){
		$http.get('/findFournisseur/'+item)
		.success(function(data){
			$scope.nomFournisseur = data.nom_fournisseur;
			$scope.chargerModels($scope.nomFournisseur);
		})
		.error(function(data) {
			alert("Erreur chargement du fournisseur");
		});
	};
		
	$scope.deconnexion = function(){
		$http.get("/logout")
		.success(function(data){
			$window.location.href="http://localhost:8080/login";
		});
	}
}])