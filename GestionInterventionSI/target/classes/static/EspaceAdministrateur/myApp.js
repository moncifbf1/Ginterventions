var myApp = angular.module('AdministrationApp',[]);

myApp.controller('indexCtrl', ['$http','$scope','$location','$window','$timeout', function($http,$scope,$location,$window,$timeout) {
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
				$scope.messageLength = data.length;
				$scope.messagesNotif = data;
			});
		},10);
	};
	
	$scope.loadData = function(){
		$timeout(function(){
			$http.get("/countOperateurs")
			.success(function(data){
				$scope.nbOperateurs = data;
			});
			$http.get("/TermineeIntervs")
			.success(function(data){
				$scope.nbTermineeIntervs = data;
			});
			$http.get("/EnAttenteIntervs")
			.success(function(data){
				$scope.nbEnAttenteIntervs = data;
			});
			$http.get("/EnCoursIntervs")
			.success(function(data){
				$scope.nbEnCoursIntervs = data;
			});
		},10);
	};
}]);

myApp.controller('utilisateursCtrl', ['$http','$scope','$window','$location','$timeout', function($http,$scope,$window,$location,$timeout) {
	//GLOBAL STUFF
	$scope.selectedRole = null;
	var codeLoggedUser = null;
	$scope.Users=[];
    $scope.roles = [];	
    
    $scope.searchUser = function(search){
    	var keyword = search;
    	$http.get("/searchUser/"+keyword)
		.success(function(data){
			console.log(keyword);
			console.log(data);
			$scope.Users = data;
		});
    }
    
    $scope.reinitialiserData = function(){
    	$scope.UserData = null;
    };
    
	$scope.go = function (path,code) {
		$window.location.href = path+'#?code='+code;
	};
	$scope.loadMessages = function(){
		$timeout(function(){
			$http.get("/getMessagesByStateSend/"+0+"/"+codeLoggedUser)
			.success(function(data){
				$scope.messageLength = data.length;
				$scope.messagesNotif = data;
			});
		},10);
	};
	$scope.onChangeTable = function(tableUserType){
		if(tableUserType=="Tous"){
			$http.get("/findUsers")
			.success(function(data){
				$scope.Users = data;
			});
		}
		else if(tableUserType=="operateurs"){
			$http.get("/operateurs")
			.success(function(data){
				$scope.Users = data;
			});
		}
		else if(tableUserType=="utilisateurs"){
			$http.get("/utilisateurs")
			.success(function(data){
				$scope.Users = data;
			});
		}
		else if(tableUserType=="responsables"){
			$http.get("/responsables")
			.success(function(data){
				$scope.Users = data;
			});
		}
		else if(tableUserType=="administrateurs"){
			$http.get("/administrateurs")
			.success(function(data){
				$scope.Users = data;
			});
		}
		else if(tableUserType.isUndefined()){
			$http.get("/findUsers")
			.success(function(data){
				$scope.Users = data;
			});
		}
	}
	
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
		
	
	$scope.chargerUsers = function(){
		$timeout(function(){
			$http.get("/findUsers")
			.success(function(data){
				$scope.Users = data;
			});
		},10);
	};
	
	//getRoles
	$http.get("/roles")
	.success(function(data){
		$scope.roles = data;
	});
	$scope.onChangeRole = function(data){
		$scope.UserData.roles[0].role = data;
	}
	$scope.updateUser = function(){
		var ro = $scope.UserData.roles[0].role;
		var ty = null;
		if(ro=="ADMINISTRATEUR"){
			ty="ADMINISTRATEUR";
		}
		else if (ro=="OPERATEUR") {
			ty="OPERATEUR";
		}
		else if (ro=="RESPONSABLE") {
			ty="RESPONSABLE";
		}
		else if(ro=="UTILISATEUR"){
			ty="UTILISATEUR";
		}
		var dataObj = {
				discrimUser: ty,
				firstName : $scope.UserData.firstName,
				lastName : $scope.UserData.lastName,
				username : $scope.UserData.username,
				username : $scope.UserData.username,
				password : $scope.UserData.password,
				actived : $scope.UserData.actived,
				telephone : $scope.UserData.telephone,
				dateCreation : $scope.UserData.dateCreation,
				roles : $scope.UserData.roles,
				type : ty
		};	
		$http({
		    method: 'PUT',
		    url: '/updateUser',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$scope.chargerUsers();
			$scope.reinitialiserData();
			$scope.alertSuccess = "Utilisateur modifié avec succés!";		
		})
		.error(function(data, status, header, config) {
			$scope.alertError = "Erreur lors de la modification de l'utilisateur!";
		});
	};			
	
    //DELETE USER
    $scope.deleteUser = function(username){
    	if ($window.confirm("Voulez-vous supprimer l'utilisateur: <<"+username+">>")){
    		$http.delete('/utilisateurs/' + username)
            .success(function (data, status, headers) {
            	$scope.chargerUsers();
            	$scope.reinitialiserData();
            	$scope.alertSuccess = "Utilisateur supprimé avec succès!";
            })
            .error(function (data, status, header, config) {
                $scope.alertError = "Problème lors de la suppression!";
            });
    	}			
	};
	
	$scope.addUser = function (){
		var tok = randomPassword(15);
		var ro = $scope.UserData.roles[0].role;
		var ty = null;
		if(ro=="ADMINISTRATEUR"){
			ty="ADMINISTRATEUR";
		}
		else if (ro=="OPERATEUR") {
			ty="OPERATEUR";
		}
		else if (ro=="RESPONSABLE") {
			ty="RESPONSABLE";
		}
		else {
			ty="UTILISATEUR";
		}
		var dataObj = {
			discrimUser: ty,
			firstName : $scope.UserData.firstName,
			lastName : $scope.UserData.lastName,
			username : $scope.UserData.username,
			password : $scope.UserData.password,
			mail: $scope.UserData.mail,
			roles : [{ role : ro}],
			telephone : $scope.UserData.telephone,
			token: tok,
			actived : $scope.UserData.actived,
			type : ty
		};	
		$http({
		    method: 'POST',
		    url: '/addUser/',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			if($scope.UserData.actived == 0){
				var destination = $scope.UserData.mail;
				
				var username = $scope.UserData.username;
				$http.get("/mail/"+destination+"/"+tok+"/"+$scope.UserData.username+"/"+$scope.UserData.password)
				.success(function(data){
					$scope.alertSuccess = "Utilisateur ajouté avec succès!";
					$scope.chargerUsers();
				});
			}
			$scope.alertSuccess = "Utilisateur ajouté avec succès!";
			$scope.chargerUsers();
		})
		.error(function(data, status, header, config) {
			$scope.alertError = "Erreur lors de l'ajout de l'utilisateur!";
		});
	};
	
	$scope.showData = function(item){
		var user = item;
		$http.get('/utilisateur/'+user.username)
		.success(function(data){
			$scope.UserData = data;
			$scope.chooseEtat=[
				{id : 0, value : "false"},
				{id : 1, value : "true"}
	        ];
		})
		.error(function(data) {
			alert("Erreur chargement des données de l'utilisateur");
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
				$scope.messageLength = data.length;
				$scope.messagesNotif = data;
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
			},1000);
		});
	};
	
	$scope.repFct = function(codeEmetteur){
		$scope.codeDest = codeEmetteur;
	}
	
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

myApp.controller('FournisseurCtrl', ['$http','$scope','$filter','$window','$location','$timeout', function($http,$scope,$filter,$window,$location,$timeout) {
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
				$scope.messageLength = data.length;
				$scope.messagesNotif = data;
			});
		},10);
	};
	//FOURNISSEURS
	
	$scope.chargerFournisseurs = function(){
		$http.get('/findFournisseurs')
		.success(function(data){
			$scope.Fournisseurs = data;
		})
		.error(function(data) {
			alert("Erreur chargement des données du message");
		});
	};
	
	$scope.addFournisseur = function(){
		var fournisseur = null;
		var dataObjF = {
			tel_fournisseur : $scope.Fournisseur.tel_fournisseur,
			mail_fournisseur : $scope.Fournisseur.mail_fournisseur,
			nom_fournisseur : $scope.Fournisseur.nom_fournisseur
		};
		
		$http({
		    method: 'POST',
		    url: '/addFournisseur',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObjF
		})
		.success(function(data, status, headers, config) {	
			$scope.chargerFournisseurs();
			alert("fournisseur ajouter");
		})
		.error(function(data, status, header, config) {
			alert("Erreur lors de l'ajout du fournisseur!");
		});
	};
	var modelFourni = null;
	$scope.showDataFournisseur = function(four){
		var Fournisseur = null;
		var nomFour = four.nom_fournisseur;
		$http.get('/getFournisseur/'+nomFour)
		.success(function(data){
			$scope.Fournisseur = data;
		})
		.error(function(data) {
			alert("Erreur chargement des données du fournisseur");
		});
		
		$http.get('/getModelByFournisseur/'+nomFour)
		.success(function(data){				
			$scope.modelFourni = data;
		});
	};
	
	$scope.updateFournisseur = function(item){
		var dataObj = item;
		$http({
		    method: 'PUT',
		    url: '/updateFournisseur',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$scope.chargerFournisseurs();
			$scope.alertSuccess = "Fournisseur modifié avec succés!";
		})
		.error(function(data, status, header, config) {
			$scope.alertError = "Erreur lors de la modification du fournisseur!";
		});
	};
	
	 $scope.deleteFournisseur = function(item){
    	if ($window.confirm("Voulez-vous supprimer ce fournisseur?")){
    		$http.delete('/fournisseur/' + item)
            .success(function (data, status, headers) {
            	$scope.chargerFournisseurs();
            	$scope.alertSuccess = "Fournisseur supprimé avec succès!";
            })
            .error(function (data, status, header, config) {
                $scope.alertError = "Problème lors de la suppression!";
            });
    	}			
	};
	
	//MODELS
	$scope.chargerModels = function(){
		$http.get('/getModels')
		.success(function(data){
			$scope.Models = data;
		})
		.error(function(data) {
			alert("Erreur chargement des models");
		});
	};
	
	$scope.showDataModel = function(mod){
		var model = null;
		var idmodel = mod.id_model;
		$http.get('/getModel/'+idmodel)
		.success(function(data){
			$scope.model = data;
			$scope.model.fournisseur.id_fournisseur = data.fournisseur.id_fournisseur;
		})
		.error(function(data) {
			alert("Erreur chargement des données du fournisseur");
		});
	};
	
	$scope.addModel = function(){
		var dataObj = {
				nom_model: $scope.model.nom_model,
				description_model: $scope.model.description_model,
				fournisseur: $scope.model.fournisseur
		};
		$http({
		    method: 'POST',
		    url: '/addModel',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {	
			$scope.chargerModels();
			alert("Model ajouté avec succès");
		})
		.error(function(data, status, header, config) {
			alert("Erreur lors de l'ajout du model!");
		});
	};
	
	$scope.updateModel = function(item){
		var dataObj = item;
		$http({
		    method: 'PUT',
		    url: '/updateModel',
		    headers: {'Content-Type': 'application/json','Accept': 'application/json'},
		    data: dataObj
		})
		.success(function(data, status, headers, config) {
			$scope.chargerModels();
			alert("Model modifié avec succés!");
		})
		.error(function(data, status, header, config) {
			alert("Erreur lors de la modification du Model!");
		});
	};
	
	$scope.deleteModel = function(item){
		$http.delete('/model/' + item)
        .success(function (data, status, headers) {
        	$scope.chargerModels();
			alert("Model supprimé avec succés!");
        })
        .error(function (data, status, header, config) {
        	alert("Erreur lors de la suppression du model!");
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
            if(item.title.toLowerCase().indexOf(searchString) !== -1){
            result.push(item);
        }
        });
        return result;
    };
});

function randomPassword(length) {
    var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOP1234567890";
    var pass = "";
    for (var x = 0; x < length; x++) {
        var i = Math.floor(Math.random() * chars.length);
        pass += chars.charAt(i);
    }
    return pass;
}