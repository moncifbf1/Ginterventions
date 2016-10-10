var myApp = angular.module('ResponsableApp', []);

myApp.controller('controllerCtrl',['$http','$scope','$window','$timeout', function($http,$scope,$window,$timeout) {
	var codeLoggedUser = null;
	$scope.getLoggedUser = function(){
		$http.get('/getLoggedUser')
		.success(function(data){
			codeLoggedUser = data.username;
			$http.get("/utilisateur/"+codeLoggedUser)
			.success(function(data){
				$scope.User=data;
				$http.get("/EnAttenteIntervsOper/"+$scope.User.username)
				.success(function(data){
					$scope.EnAttenteIntervs = data;
				});
				
				$http.get("/TermineeIntervsOper/"+$scope.User.username)
				.success(function(data){
					$scope.TermineeIntervs = data;
				});
			})
			.error(function(data) {
				alert("Erreur chargement des données de l'utilisateur");
			});			
		})
		.error(function(data) {
			alert("Erreur chargement code de l'utilisateur!");
		});
	};
	
	$scope.logout = function(){
		$http.get("/logout")
		.success(function(data){
			$window.location.href="http://localhost:8080/login";
		});
	};
	
	$scope.loadMessages = function(){
		$timeout(function(){
			$http.get("/getMessagesByStateSend/"+0+"/"+codeLoggedUser)
			.success(function(data){
				$scope.messagesNotif = data;
				$scope.messageLength = data.length;
			});
		},10);
	};
}])

myApp.controller('InterventionsCtrl',['$http','$scope','$filter','$window','$location','$timeout','Excel', function($http,$scope,$filter,$window,$location,$timeout,Excel) {
	$scope.Interventions = [];
	$scope.Utilisateurs = [];
	var User = null;
	var codeLoggedUser = null;
	$scope.IsVisible = false;
	
    $scope.ShowHide = function () {
        $scope.IsVisible = $scope.ShowContact;
    };
    
    $scope.loadMessages = function(){
		$timeout(function(){
			$http.get("/getMessagesByStateSend/"+0+"/"+codeLoggedUser)
			.success(function(data){
				$scope.messagesNotif = data;
				$scope.messageLength = data.length;
			});
		},10);
	};
    
    $scope.exportToExcel=function(tableId){ 
        var exportHref=Excel.tableToExcel(tableId,'sheet name');
        $timeout(function(){location.href=exportHref;},100); // trigger download
    };
    
    $scope.envoyerMail = function(){
    	var mailfournisseur = $scope.Intervention.mailFournisseur;
    	var sujetmail = $scope.Intervention.sujetmail;
    	var contenumail = $scope.Intervention.contenuMail;
    	
    	$http.get("/mailfournisseur/"+mailfournisseur+"/"+sujetmail+"/"+contenumail)
		.success(function(data){
			$scope.alertSuccess ="Mail envoyé avec succès!";
		});
    	
		var dataObj = {
			id_intervention: $scope.Intervention.id_intervention,
			observation_intervention: $scope.Intervention.observation_intervention
		};	
		$http({
		    method: 'PUT',
		    url: '/terminerOpFour',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$scope.chargerInterventions();
			$scope.Intervention = null;
			$scope.alertSuccess = "Le statut de l'intervention est: Terminée!";
		})
		.error(function(data, status, header, config) {
		});
		
		var logData = {
		    userType: 'OP',
			userCode: codeLoggedUser,
			action: "Intervention <<"+$scope.Intervention.titre_intervention+">> passée au fournisseur!"
		};
		
		$http({
		    method: 'PUT',
		    url: '/addLog',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: logData
		})
		.success(function(data, status, headers, config) {
		});
    };
    
	$scope.getLoggedUser = function(){
		$http.get('/getLoggedUser')
		.success(function(data){
			codeLoggedUser = data.username;
			$http.get("/utilisateur/"+codeLoggedUser)
			.success(function(data){
				User=data;
				$scope.User = data;
			})
			.error(function(data) {
				alert("Erreur chargement des données de l'utilisateur");
			});
		})
		.error(function(data) {
			alert("Erreur chargement code de l'utilisateur!");
		});
	};
	
	$scope.logout = function(){
		$http.get("/logout")
		.success(function(data){
			$window.location.href="http://localhost:8080/login";
		});
	}
	
	$scope.getEnAttenteInterventions = function(){
		$timeout(function(){
			$http.get("/EnAttenteIntervs")
			.success(function(data){
				$scope.EnAttenteIntervs = data;
			});
		},10);
	};
	
	$scope.getEnCoursInterventions = function(){
		$timeout(function(){
			$http.get("/EnCoursIntervs")
			.success(function(data){
				$scope.EnCoursIntervs = data;
			});
		},10);
	};
	
	$scope.chargerInterventions = function(){
		$timeout(function(){
			var dataObj = $scope.User.username;
			$http.get('/interventions/op/'+dataObj)
			.success(function(data){
				$scope.Interventions = data;
			})
			.error(function(data) {
				alert("Erreur chargement des interventions de l'utilisateur!");
			});
	    },1000);
	};
	
	$scope.onChangeSearchNiveau= function(searchNiveau){
		$scope.searchNiveau=searchNiveau;
	};
	
	$scope.showData = function(inter){
		var Intervention = null;
		var idInter = inter.id_intervention;
				
		$http.get('/interventions/'+idInter)
		.success(function(data){
			$scope.Intervention = data;
			
			$http.get('/getModel/'+data.idModel)
			.success(function(data){
				$scope.Intervention.idModel = data.id_model;
				$scope.Intervention.nomModel = data.nom_model;
				$scope.Intervention.mailFournisseur = data.fournisseur.mail_fournisseur;
			});
			
			$scope.chooseNiveaux=[
				{id : 1, value : "Résolution impossible"},
			    {id : 2, value : "Intervention abandonnée"},
			    {id : 3, value : "Résolue par opérateur"},
			    {id : 4, value : "Résolue par fournisseur"},
			    {id : 5, value : "Hors périmètre"},
			    {id : 6, value : "Dossier en doublon"}
            ];  
			
			$scope.chooseEtat=[
				{id : 0, value : "En attente"},
				{id : 1, value : "En cours"},
				{id : 2, value : "Terminée"}
			];
		})
		.error(function(data) {
			alert("Erreur chargement des données de l'intervention");
		});
	};
	
	$scope.intervenir = function(){
		var dataObj = $scope.Intervention;
		$http({
		    method: 'PUT',
		    url: '/intervenir',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$scope.chargerInterventions();
			$scope.Intervention = null;
			$scope.alertSuccess ="Le statut de l'intervention est: En Cours!";
		})
		.error(function(data, status, header, config) {
			$scope.alertError = "Erreur lors du lancement de l'intervention!";
		});
		var logData = {
			userType: 'OP',
			userCode: codeLoggedUser,
			action: "Démarrage de l'intervention <<"+$scope.Intervention.titre_intervention+">>"
		};
		
		$http({
		    method: 'PUT',
		    url: '/addLog',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: logData
		})
		.success(function(data, status, headers, config) {
		});
	};
	$scope.finir = function(){
		var dataObj = $scope.Intervention;
		$http({
		    method: 'PUT',
		    url: '/terminer',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$scope.chargerInterventions();
			$scope.Intervention = null;
			$scope.alertSuccess = "Le statut de l'intervention est: Terminée!";
		})
		.error(function(data, status, header, config) {
			$scope.alertError = "Erreur lors de la fin de l'intervention!";
		});
		
		//ETATS DES INTERVENTIONS ===> LOG
		var lvlIntervention = null;
		if($scope.Intervention.niveau_intervention==1) lvlIntervention="Résolution impossible";
		else if($scope.Intervention.niveau_intervention==2) lvlIntervention="Intervention abandonnée";
		else if($scope.Intervention.niveau_intervention==3) lvlIntervention="Résolue par opérateur";
		else if($scope.Intervention.niveau_intervention==4) lvlIntervention="Résolue par fournisseur";
		else if($scope.Intervention.niveau_intervention==5) lvlIntervention="Hors périmètre";
		else if($scope.Intervention.niveau_intervention==6) lvlIntervention="Dossier en doublon";
		var logData = {
			userType: 'OP',	
			userCode: codeLoggedUser,
			action: "L'intervention <<"+$scope.Intervention.titre_intervention+">> a été cloturée avec le code <<"+lvlIntervention+">>, par l'opérateur: "+$scope.Intervention.operateur.username.toUpperCase()
		};
		
		$http({
		    method: 'PUT',
		    url: '/addLog',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: logData
		})
		.success(function(data, status, headers, config) {
		});
	};	
	
//	$scope.passerNiveau = function(){
//		var dataObj = $scope.Intervention;
//		$http({
//		    method: 'PUT',
//		    url: '/passerniveau',
//		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
//		    data: dataObj
//		})
//		.success(function(data, status, headers, config) {
//			$scope.chargerInterventions();
//			$scope.alertSuccess = "Niveau de l'intervention => Niveau "+$scope.Intervention.niveau_intervention+1;
//		})
//		.error(function(data, status, header, config) {
//			$scope.alertError = "Erreur lors du changement de niveau de l'intervention!";
//		});
//		var logData = {
//			userCode: codeLoggedUser,
//			action: "L'intervention "+$scope.Intervention.titre_intervention+" a été passée au niveau "+$scope.Intervention.niveau_intervention+" par l'opérateur "+$scope.Intervention.operateur.username.toUpperCase()
//		};
//		
//		$http({
//		    method: 'PUT',
//		    url: '/addLog',
//		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
//		    data: logData
//		})
//		.success(function(data, status, headers, config) {
//		});
//	};
}]);

myApp.controller('MessagerieCtrl',['$http','$scope','$filter','$window','$location','$timeout', function($http,$scope,$filter,$window,$location,$timeout) {
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
	
	$scope.logout = function(){
		$http.get("/logout")
		.success(function(data){
			$window.location.href="http://localhost:8080/login";
		});
	};
	
	$scope.repFct = function(codeEmetteur){
		$scope.codeDest = codeEmetteur;
	}
	
	$scope.loadMessages = function(){
		$timeout(function(){
			$http.get("/getMessagesByStateSend/"+0+"/"+codeLoggedUser)
			.success(function(data){
				$scope.messagesNotif = data;
				$scope.messageLength = data.length;
			});
		},10);
	};
	
	
	$scope.chargerUsers = function(){
		$timeout(function(){
			$http.get('/findRestrictedUsers/'+codeLoggedUser)
			.success(function(data){
				$scope.Users = data;
			})
			.error(function(data) {
				alert("Erreur chargement des utilisateurs");
			});
		},2000);
	};
	
	$scope.chargerMessages = function(){
		$timeout(function(){
			$http.get('/getMessages/'+codeLoggedUser)
			.success(function(data){
				$scope.Messages = data;
			});
			$http.get('/getMessagesEnv/'+codeLoggedUser)
			.success(function(data){
				$scope.MessagesEnv = data;
			});
			$http.get('/getMessagesByStateSend/'+0+"/"+codeLoggedUser)
			.success(function(data){
				$scope.MessagesWithState = data;
			});
		},10);
		$timeout(function(){
	      $scope.chargerMessages();
	    },1000);
	};
	$scope.envoyerMessage = function(){
		var dataObj = {
			objet_message: $scope.message.objet_message,
			text_message: $scope.message.text_message,
			codeEmetteur: codeLoggedUser,
			codeDestinataire: $scope.codeDest
		};
		$http({
		    method: 'PUT',
		    url: '/sendMessage',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$timeout(function(){
				$scope.alertSuccess = "Message envoyé avec succès!";
				$scope.message = null;
			},1000);
		});
	};
	
	$scope.showData = function(item){ 
		var messageData = null;
		var alert = null;
		$http.get('/getMessage/'+item)
		.success(function(data){
			$scope.messageData = data;
		})
		.error(function(data) {
			alert("Erreur chargement des données du message");
		});
	};
	
	$scope.changeState = function(item){
		$scope.messageLength = 0;
		var dataObj = item;
		$http({
		    method: 'PUT',
		    url: '/changeState',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			
		});
	};
}]);


myApp.filter('cut', function () {
    return function (value, wordwise, max, tail) {
        if (!value) return '';

        max = parseInt(max, 10);
        if (!max) return value;
        if (value.length <= max) return value;

        value = value.substr(0, max);
        if (wordwise) {
            var lastspace = value.lastIndexOf(' ');
            if (lastspace != -1) {
              //Also remove . and , so its gives a cleaner result.
              if (value.charAt(lastspace-1) == '.' || value.charAt(lastspace-1) == ',') {
                lastspace = lastspace - 1;
              }
              value = value.substr(0, lastspace);
            }
        }

        return value + (tail || ' …');
    };
});

myApp.filter('searchFor', function(){
    return function(arr, searchString){
        if(!searchString){
            return arr;
        }
        var result = [];
        searchString = searchString.toLowerCase();
        angular.forEach(arr, function(item){
            if(item.niveau_intervention.indexOf(searchString) !== -1){
            result.push(item);
        }
        });
        return result;
    };
});

myApp.factory('Excel',function($window){
    var uri='data:application/vnd.ms-excel;base64,',
        template='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><meta charset="UTF-8"><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center><h1>Liste des incidents</h1></center><table style="border: 1px solid green;"><thead style="border:1px solid green;"><tr><th style="background-color:green; color:white;">TITRE</th><th style="background-color:green; color:white;">MODEL</th><th style="background-color:green; color:white;">FOURNISSEUR</th><th style="background-color:green; color:white;">NIVEAU</th><th style="background-color:green; color:white;">DATE DE DEMANDE</th><th style="background-color:green; color:white;">DATE INTERVENTION</th> <th style="background-color:green; color:white;">UTILISATEUR</th><th style="background-color:green; color:white;">OBSERVATION</th><th style="background-color:green; color:white;">ETAT</th></tr></thead>{table}</table></body></html>',
        base64=function(s){return $window.btoa(unescape(encodeURIComponent(s)));},
        format=function(s,c){return s.replace(/{(\w+)}/g,function(m,p){return c[p];})};
    return {
        tableToExcel:function(tableId,worksheetName){
            var table=$(tableId),
                ctx={worksheet:worksheetName,table:table.html()},
                href=uri+base64(format(template,ctx));
            return href;
        }
    };
});