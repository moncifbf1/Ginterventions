var myApp = angular.module('ResponsableApp', ['angular.morris-chart']);
myApp.controller('controllerCtrl',['$http','$scope','$window','$timeout', function($http,$scope,$window,$timeout) {
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
	
	$scope.getTermineesInterventions = function(){
		$timeout(function(){
			$http.get("/TermineeIntervs")
			.success(function(data){
				$scope.TermineeIntervs = data;
			});
		},10);
	};
	
	$scope.chargerLog = function(){
		$http.get('/findLog')
		.success(function(data){
			$scope.Logs = data;
		})
		.error(function(data) {
			alert("Erreur chargement des interventions");
		});
		$timeout(function(){
	      $scope.chargerLog();
	    },10000)
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

myApp.controller('InterventionsCtrl',['$http','$scope','$filter','$window','$location','$timeout','$filter','Excel', function($http,$scope,$filter,$window,$location,$timeout,$filter,Excel) {
	$scope.Interventions = [];
	$scope.Operateurs = [];
	$scope.Utilisateurs = [];
	var codeLoggedUser = null;
    
	$scope.exportToExcel=function(tableId){ // ex: '#my-table'
        var exportHref=Excel.tableToExcel(tableId,'sheet name');
        $timeout(function(){location.href=exportHref;},100); // trigger download
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
	}
	
	$scope.chargerInterventions = function(){
		$http.get('/interventions')
		.success(function(data){
			$scope.Interventions = data;
		})
		.error(function(data) {
			alert("Erreur chargement des interventions");
		});
	};
	
	$scope.onChangeSearchNiveau= function(searchNiveau){
		$scope.searchNiveau=searchNiveau;
	};
	
	$scope.onChangeSearchEtat= function(searchEtat){
		$scope.searchEtat=searchEtat;
	};
	
	$scope.search = function(){
		var searchNiveau = $scope.searchNiveau;
		var searchEtat = $scope.searchEtat;
		var searchDate = $filter('date')($scope.searchDate, "yyyy-MM-dd");
		
		if(searchNiveau==null && searchEtat==null && searchDate!=null)
		{
			$http.get('/searchQuery1/'+searchDate)
			.success(function(data){
				$scope.Interventions = data;
			})
			.error(function(data) {
				alert("Erreur chargement des interventions");
			});
		}
		else if(searchNiveau==null && searchEtat!=null && searchDate==null)
		{
			$http.get('/searchQuery2/'+searchEtat)
			.success(function(data){
				$scope.Interventions = data;
			})
			.error(function(data) {
				alert("Erreur chargement des interventions");
			});
		}
		else if(searchNiveau==null && searchEtat!=null && searchDate!=null)
		{
			$http.get('/searchQuery3/'+searchEtat+'/'+searchDate)
			.success(function(data){
				$scope.Interventions = data;
			})
			.error(function(data) {
				alert("Erreur chargement des interventions");
			});
		}
		else if(searchNiveau!=null && searchEtat==null && searchDate==null)
		{
			$http.get('/searchQuery4/'+searchNiveau)
			.success(function(data){
				$scope.Interventions = data;
			})
			.error(function(data) {
				alert("Erreur chargement des interventions");
			});
		}
		else if(searchNiveau!=null && searchEtat==null && searchDate!=null)
		{
			$http.get('/searchQuery5/'+searchNiveau+'/'+searchDate)
			.success(function(data){
				$scope.Interventions = data;
			})
			.error(function(data) {
				alert("Erreur chargement des interventions");
			});
		}	
		else if(searchNiveau!=null && searchEtat!=null && searchDate==null)
		{
			$http.get('/searchQuery6/'+searchNiveau+'/'+searchEtat)
			.success(function(data){
				$scope.Interventions = data;
			})
			.error(function(data) {
				alert("Erreur chargement des interventions");
			});
		}
		else{
			$http.get('/searchQuery7/'+searchNiveau+'/'+searchEtat+'/'+searchDate)
			.success(function(data){
				$scope.Interventions = data;
			})
			.error(function(data) {
				alert("Erreur chargement des interventions");
			});
		}
	};
		
	$scope.chargerOperateurs = function(){
		$http.get('/operateurs')
		.success(function(data){
			$scope.Operateurs = data;
		})
		.error(function(data) {
			alert("Erreur chargement des opérateurs");
		});
	};
	$scope.reinitialiser = function(){
		$scope.searchNiveau = null;
		$scope.searchEtat = null;
		$scope.searchDate = null;
		$scope.chargerInterventions();
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
	
	$scope.onChangeOper = function(item){
		$http.get("/utilisateur/"+item)
		.success(function(data){
			$scope.Intervention.operateur=data;
			$scope.Intervention.operateur.username = item;
		});
	};
		
	$scope.attribuerInterventions = function(){
		var dataObj = {
			id_intervention: $scope.Intervention.id_intervention,
			operateur : $scope.Intervention.operateur
		};	
		$http({
		    method: 'PUT',
		    url: '/affecterIntervention',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$scope.chargerInterventions();
			$scope.Intervention = null;
			$scope.alertSuccess = "Intervention affectée avec succés!";
		});
		var logData = {
			userType: 'RE',
			userCode: codeLoggedUser,
			action: "L'intervention <<"+$scope.Intervention.titre_intervention+">> a été attribuée à l'opérateur "+$scope.Intervention.operateur.username.toUpperCase()+" par le résponsable "+codeLoggedUser.toUpperCase()+"."
		};
		
		$http({
		    method: 'PUT',
		    url: '/addLog',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: logData
		});
	};
	
	$scope.modifierInterventions = function(){
		var dataObj = {
			id_intervention: $scope.Intervention.id_intervention,
			niveau_intervention : $scope.Intervention.niveau_intervention,
			etat_intervention : $scope.Intervention.etat_intervention
		};	
		$http({
		    method: 'PUT',
		    url: '/interventions',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$scope.chargerInterventions();
			$scope.alertSuccess = "Intervention modifiée avec succès!";
		})
		.error(function(data, status, header, config) {
        	$scope.alertError = "Erreur lors de la modification de l'intervention!";
		});
		var logData = {
		    userType: 'RE',
			userCode: codeLoggedUser,
			action: "L'intervention <<"+$scope.Intervention.titre_intervention+">> a été modifiée par le résponsable "+codeLoggedUser.toUpperCase()+"."
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
}])

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
	
	$scope.repFct = function(codeEmetteur){
		$scope.codeDest = codeEmetteur;
	}
	
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


myApp.controller('StatistiquesCtrl',['$http','$scope','$filter','$window','$location','$timeout', function($http,$scope,$filter,$window,$location,$timeout) {
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
	
	var months = ["Jan", "Fev", "Mar", "Avr", "Mai", "Jun", "Jul", "Aou", "Sep", "Oct", "Nov", "Dec"];
	$scope.xaxis = 'y';
	$scope.yaxis = '["a,b"]';
  
 	$scope.data = [
	    { y: "2016-01", a: 100, b: 90 },
	    { y: "2016-02", a: 75,  b: 65 },
	    { y: "2016-03", a: 50,  b: 40 },
	    { y: "2016-04", a: 75,  b: 65 },
	    { y: "2016-05", a: 50,  b: 40 },
	    { y: "2016-06", a: 75,  b: 65 },
	    { y: "2016-07", a: 75,  b: 65 },
	    { y: "2016-08", a: 75,  b: 65 },
	    { y: "2016-09", a: 75,  b: 65 },
	    { y: "2016-10", a: 75,  b: 65 },
	    { y: "2016-11", a: 75,  b: 65 },
	    { y: "2016-12", a: 100, b: 90 }
	];
  
 	$scope.xLabelFormat = function(x) { // <--- x.getMonth() returns valid index
 		var month = months[x.getMonth()];
 		return month;
 	}
  
 	$scope.dateFormat = function(x) {
 		var month = months[new Date(x).getMonth()];
 		return month;
 	}
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

myApp.factory('Excel',function($window){
    var uri='data:application/vnd.ms-excel;base64,',
        template='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><meta charset="UTF-8"><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center><h1>Liste des incidents</h1></center><table style="border: 1px solid green;"><thead style="border:1px solid green;"><tr><th style="background-color:green; color:white;">TITRE</th><th style="background-color:green; color:white;">MODEL</th><th style="background-color:green; color:white;">FOURNISSEUR</th><th style="background-color:green; color:white;">DATE DEMANDE</th><th style="background-color:green; color:white;">DATE INTERVENTION</th><th style="background-color:green; color:white;">DATE OUVERTURE</th><th style="background-color:green; color:white;">DATE CLOTURE</th><th style="background-color:green; color:white;">OPERATEUR</th><th style="background-color:green; color:white;">UTILISATEUR</th><th style="background-color:green; color:white;">OBSERVATION</th><th style="background-color:green; color:white;">ETAT</th></tr></thead>{table}</table></body></html>',
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