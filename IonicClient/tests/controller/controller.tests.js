describe('Controllers unit testing success response', function(){
    var scope,stateParams,mockService,ionicPopupMock;

    // load the controller's module

    beforeEach(module('starter.controllers'));

    beforeEach(inject(function($rootScope, $controller) {
        
        stateParams = {
            id:1
        }
        
        mockService = {
           getAllCustomers : function (onComplete) {
                 var  responseMessage ={
                        messageCode:'1',
                        message:'Success',
                        data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
                 }
                 onComplete(responseMessage);
            },
            
            getCustomer: function(id, onComplete){
                 var  responseMessage ={
                        messageCode:'1',
                        message:'Success',
                        data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
                 }
                 onComplete(responseMessage);
            },
            
            addCustomer: function(customer,onComplete) {
                 var  responseMessage ={
                        messageCode:'1',
                        message:'Success',
                        data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
                 }
                 onComplete(responseMessage);
            },
               
            editCustomer: function(customer,onComplete){
                 var  responseMessage ={
                        messageCode:'1',
                        message:'Success',
                        data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
                 }
                 onComplete(responseMessage);
            },
            deleteCustomer: function(id,onComplete){
                 var  responseMessage ={
                        messageCode:'1',
                        message:'Success',
                        data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
                 }
                 onComplete(responseMessage);
            }
        }
        
        // mock $ionicPopup
        ionicPopupMock = jasmine.createSpyObj('$ionicPopup spy', ['alert']);

        scope = $rootScope.$new();
        $controller('CustomerCtrl', { '$scope': scope, '$stateParams':stateParams, '$ionicPopup':ionicPopupMock,'CustomerService': mockService} );
     }));

    
    
    // tests start here
    it('Failed --- Add records should return appropriate response message', function(){ 
        scope.addCustomer()
        expect(scope.responseMessage.data.length > 0).toEqual(true);
        expect(scope.responseMessage.messageCode == '1').toEqual(true); 
        expect(scope.responseMessage.message == 'Success').toEqual(true);   
    });
   
    it('Failed --- Edit should return appropriate response message', function(){ 
        scope.editCustomer()
        expect(scope.responseMessage.data.length > 0).toEqual(true);
        expect(scope.responseMessage.messageCode == '1').toEqual(true); 
        expect(scope.responseMessage.message == 'Success').toEqual(true);   
    });
    
    
    
    it('Failed --- Delete customer should return appropriate response message', function(){
        scope.formData.id = 1
        scope.deleteCustomer()
        expect(scope.responseMessage.data.length > 0).toEqual(true);
        expect(scope.responseMessage.messageCode == '1').toEqual(true); 
        expect(scope.responseMessage.message == 'Success').toEqual(true);   
    });
});

describe('Controllers unit testing failure response', function(){
    var scope,stateParams,mockService,ionicPopupMock;

    // load the controller's module

    beforeEach(module('starter.controllers'));

    beforeEach(inject(function($rootScope, $controller) {
        
        stateParams = {
            id:1
        }
        
        mockService = {
           getAllCustomers : function (onComplete) {
                 var  responseMessage ={
                        messageCode:'2',
                        message:'Record not found',
                        data :[]
          
                 }
                 onComplete(responseMessage);
            },
            
            getCustomer: function(id, onComplete){
                 var  responseMessage ={
                        messageCode:'2',
                        message:'Record not found',
                        data :[]
          
                 }
                 onComplete(responseMessage);
            },
            
            addCustomer: function(customer,onComplete) {
                 var  responseMessage ={
                        messageCode:'0',
                        message:'Failed',
                        data :[]
          
                 }
                 onComplete(responseMessage);
            },
               
            editCustomer: function(customer,onComplete){
                 var  responseMessage ={
                        messageCode:'0',
                        message:'Failed',
                        data :[]
          
                 }
                 onComplete(responseMessage);
            },
            deleteCustomer: function(id,onComplete){
                 var  responseMessage ={
                        messageCode:'0',
                        message:'Failed',
                        data :[]
                 }
                 onComplete(responseMessage);
            }
        }
        
        // mock $ionicPopup
        ionicPopupMock = jasmine.createSpyObj('$ionicPopup spy', ['alert']);

        scope = $rootScope.$new();
        $controller('CustomerCtrl', { '$scope': scope, '$stateParams':stateParams, '$ionicPopup':ionicPopupMock,'CustomerService': mockService} );
     }));

    
    
    // tests start here
    it('Failed --- Add records should return appropriate response message', function(){ 
        scope.addCustomer()
        expect(scope.responseMessage.data.length == 0).toEqual(true);
        expect(scope.responseMessage.messageCode == '0').toEqual(true); 
        expect(scope.responseMessage.message == 'Failed').toEqual(true);   
    });
   
    it('Failed --- Edit should return appropriate response message', function(){ 
        scope.editCustomer()
        expect(scope.responseMessage.data.length == 0).toEqual(true);
        expect(scope.responseMessage.messageCode == '0').toEqual(true); 
        expect(scope.responseMessage.message == 'Failed').toEqual(true);   
    });
    
    
    
});