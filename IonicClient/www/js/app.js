// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
var app = angular.module('starter', ['ionic', 'ngCordova'])

app.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})


app.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
  .state('login', {
    url: '/login',
    templateUrl: 'templates/login.html',
    'controller': 'LoginCtrl'
  })

  .state('tab', {
    url: "/tab",
    abstract: true,
    templateUrl: "templates/menu.html",
    controller: 'AppCtrl'
  })

  // setup an abstract state for the tabs directive
/*  .state('tab', {
    url: '/tab',
    abstract: true,
    templateUrl: 'templates/tabs.html'
  })*/

  // Each tab has its own nav history stack:

  .state('tab.home', {
    url: '/home',
    views: {
      'tab-home': {
        templateUrl: 'templates/tab-home.html',
        controller: 'HomeCtrl'
      }
    }
  })

  .state('tab.list', {
      url: '/list',
      views: {
        'tab-list': {
          templateUrl: 'templates/tab-list.html',
          controller: 'InterventionsEnAttenteCtrl'
        }
      }
    })
    .state('tab.list-detail', {
      url: '/list/:id',
      views: {
        'tab-list': {
          templateUrl: 'templates/customer-edit.html'
        }
      }
    })

  .state('tab.customer', {
    url: '/customer',
    views: {
      'tab-customer-add': {
        templateUrl: 'templates/customer-add.html',
        controller: 'InterventionsEncoursCtrl'
      }
    }
  });

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/login');

});

app.controller('AppCtrl', function($scope,$http,$state) {
  $scope.deconnexion = function(){
    localStorage.clear();
    $state.go('login');
  };
});

app.controller('HomeCtrl', function($scope,$http,$state) {

  if(localStorage.getItem("token")==null){
    $state.go('login');
  }


  $scope.getLog = function(){
    $http({
      method: 'GET',
      url: '/backendapi/findLog',
      headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': "*"}
    })
    .success(function(data) {
      $scope.timeline = data;
    })
    .error(function(data) {
      console.log("sorry !");
    });
  };

});

app.controller('LoginCtrl', ['$scope', '$location', '$http','$state','$cordovaInAppBrowser',
  function($scope, $location, $http, $state, $cordovaInAppBrowser) {
    if(localStorage.getItem("token")!=null){
      $state.go('tab.home');
    }

    $scope.login = function(data){
      var data = {
        username: data.username,
        password: data.password
      };
      $http({
        method: 'POST',
        url: '/webapi/login',
        headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': "*"},
        data: data
      })
      .success(function(data, status, headers, config) {
        if(data.status == 'error'){
          alert('error');
        }
        else if(data.status == 'success'){
          localStorage.setItem("username",data.username);
          localStorage.setItem("discrim_user",data.discrim_user);
          localStorage.setItem("token",data.token);
          $state.go('tab.home');
        }
        console.log(data);
      })
      .error(function(data, status, header, config) {
        alert("Erreur");
        console.log(data);
      });

    };
}]);

app.controller('InterventionsEnAttenteCtrl', ['$scope', '$location', '$http','$state', '$ionicPopup',
  function($scope, $location, $http, $state, $ionicPopup) {

    $scope.getInterventions = function(data){      
      $http({
        method: 'GET',
        url: '/backendapi/interventions/op/naima.ja/0',
        headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': "*"}
      })
      .success(function(data) {
        $scope.interventions = data;
      })
      .error(function(data) {

        console.log("sorry !");
      });

    };

    $scope.doRefresh = function() {
      $http({
        method: 'GET',
        url: '/backendapi/interventions/op/naima.ja/0',
        headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': "*"}
      })
      .success(function(data) {
        $scope.interventions = data;
        //Stop the ion-refresher from spinning
        $scope.$broadcast('scroll.refreshComplete');
      })
      .error(function(data) {

        console.log("sorry !");
      }); 
    };


    $scope.showPopUpInfos = function(data){

      var intervention = data;
      var state = null;

      if(intervention.etat_intervention==0) state='En attente';
      else if (intervention.etat_intervention==1) state='En cours';
      else if(intervention.etat_intervention==2) state='Terminée';

      console.log(intervention);

      var alertPopup = $ionicPopup.alert({
        title: 'Informations de l\'intervention',
        template: 'Date de demande: Le '+intervention.date_demande+'<br/>Modèle: '+intervention.interveModel+'<br/>Fournisseur: '+intervention.intervFournisseur+'<br/>Etat de l\'intervention: '+state+'<br/>Utilisateur: '+intervention.utilisateur.firstName+' '+intervention.utilisateur.lastName,
        subTitle: intervention.titre_intervention,
        okType: 'button-energized'
      });

      alertPopup.then(function(res) {

        console.log('Thanks');

      });
    };

    $scope.showPopUpStart = function(data){

      var intervention = data;

      $http({
        method: 'PUT',
        url: '/backendapi/intervenir',
        headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': "*"},
        data: intervention
      })
      .success(function(data) {
        var logData = {
          userType: 'OP',
          userCode: 'naima.ja',
          action: "Démarrage de l'intervention <<"+intervention.titre_intervention+">>"
        };
        
        $http({
            method: 'PUT',
            url: '/backendapi/addLog',
            headers: {'Content-Type': 'application/json','Accept': 'application/json'},
            data: logData
        })
        .success(function(data, status, headers, config) {
        });

        $scope.getInterventions();
      })
      .error(function(data) {
        console.log("sorry !");
      });

      var alertPopup = $ionicPopup.alert({
        title: 'Alerte',
        template: '<center>Intervention démarée avec succés</center>',
        subTitle: intervention.titre_intervention,
        okType: 'button-balanced'
      });

      alertPopup.then(function(res) {
        
      });
    };
}]);

app.controller('InterventionsEncoursCtrl', ['$scope', '$location', '$http','$state', '$ionicPopup',
  function($scope, $location, $http, $state, $ionicPopup) {

    $scope.getInterventions = function(data){      
      $http({
        method: 'GET',
        url: '/backendapi/interventions/op/naima.ja/1',
        headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': "*"}
      })
      .success(function(data) {
        $scope.interventions = data;
      })
      .error(function(data) {

        console.log("sorry !");
      });

    };

    $scope.doRefresh = function() {
      $http({
        method: 'GET',
        url: '/backendapi/interventions/op/naima.ja/1',
        headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': "*"}
      })
      .success(function(data) {
        $scope.interventions = data;
        //Stop the ion-refresher from spinning
        $scope.$broadcast('scroll.refreshComplete');
      })
      .error(function(data) {

        console.log("sorry !");
      }); 
    };

    $scope.showPopUpStop = function(intervention) {
      $scope.item = intervention

      var myPopup = $ionicPopup.show({

          template: '<center><select ng-model="item.niveau_intervention" ng-options="niveau.id as niveau.val for niveau in [{ id: 0, val: \'Non défini\' }, { id: 1, val: \'Résolution impossible\' }, { id: 2, val: \'Intervention abandonnée\' }, { id: 3, val: \'Résolue par opérateur\' }, { id: 4, val: \'Résolue par fournisseur\' }, { id: 5, val: \'Hors périmètre\' }, { id: 6, val: \'Dossier en doublon\' }]" ng-change="onChangeSearchNiveau(searchNiveau)" ng-cloak></select></center><br/>Votre observation: <textarea ng-model="item.observation_intervention" style="padding:2%;background-color: rgba(255, 0, 0, 0.15);" name="textarea" rows="10" cols="20">Saisir un texte ici.</textarea>',
          title: 'Clôturer l\'intervention',
          subTitle: 'Veuillez choisir votre code de clôture:',
          scope: $scope,
          buttons: [{
             text: 'Annuler'
          }, {
             text: '<b>Clotûrer</b>',
             type: 'button-assertive',
             onTap: function(e) {
                if ($scope.item == null) {
                    e.preventDefault();
                } else {
                  return $scope.item;
                }
             }
          }, ]
       });

       myPopup.then(function(res) {
          if (res) {
            $http({
              method: 'PUT',
              url: '/backendapi/terminer',
              headers : {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': "*"},
              data: intervention
            })
            .success(function(data) {
              var lvlIntervention = null;
              if(intervention.niveau_intervention==1) lvlIntervention="Résolution impossible";
              else if(intervention.niveau_intervention==2) lvlIntervention="Intervention abandonnée";
              else if(intervention.niveau_intervention==3) lvlIntervention="Résolue par opérateur";
              else if(intervention.niveau_intervention==4) lvlIntervention="Résolue par fournisseur";
              else if(intervention.niveau_intervention==5) lvlIntervention="Hors périmètre";
              else if(intervention.niveau_intervention==6) lvlIntervention="Dossier en doublon";
              var logData = {
                userType: 'OP', 
                userCode: 'naima.ja',
                action: "L'intervention <<"+intervention.titre_intervention+">> a été cloturée avec le code <<"+lvlIntervention+">>, par l'opérateur: "+intervention.operateur.username.toUpperCase()
              };
              
              $http({
                  method: 'PUT',
                  url: '/backendapi/addLog',
                  headers: {'Content-Type': 'application/json','Accept': 'application/json'},
                  data: logData
              })
              .success(function(data, status, headers, config) {
              })
              .error(function(data) {
                console.log("sorry bad !");
              });
              $scope.getInterventions();
            })
            .error(function(data) {
              console.log("sorry !");
            });
          } else {
            console.log('PopUp Cancelled!');
          }
       });
    };

    $scope.showPopUpInfos = function(data){

      var intervention = data;
      var state = null;

      if(intervention.etat_intervention==0) state='En attente';
      else if (intervention.etat_intervention==1) state='En cours';
      else if(intervention.etat_intervention==2) state='Terminée';

      console.log(intervention);

      var alertPopup = $ionicPopup.alert({
        title: 'Informations de l\'intervention',
        template: 'Date de demande: Le '+intervention.date_demande+'<br/>Modèle: '+intervention.interveModel+'<br/>Fournisseur: '+intervention.intervFournisseur+'<br/>Etat de l\'intervention: '+state+'<br/>Utilisateur: '+intervention.utilisateur.firstName+' '+intervention.utilisateur.lastName,
        subTitle: intervention.titre_intervention,
        okType: 'button-energized'
      });

      alertPopup.then(function(res) {

        console.log('Thanks');

      });
    };
}]);
