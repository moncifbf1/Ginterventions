describe("Service unit test", function () {
  var  httpBackend,service;

  beforeEach(module("starter.services"));

  beforeEach(inject(function (_CustomerService_,$httpBackend,$httpParamSerializerJQLike) {
    httpBackend = $httpBackend;
    service=_CustomerService_
  }));

  it("Failed -- getAllCustomers should return the appropriate success response message", function () {
    httpBackend.whenGET("http://localhost:8080/getAllCustomers").respond({
        responseMessage: {
          messageCode:'1',
          message:'Success',
          data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
        }
    });
    
    service.getAllCustomers(function(response){  
       expect(response.responseMessage.data.length > 0 ).toEqual(true);
       expect(response.responseMessage.data[0].name == 'test' ).toEqual(true);
       expect(response.responseMessage.data[0].address == 'address' ).toEqual(true);
       expect(response.responseMessage.data[0].phoneNumber == '0101010' ).toEqual(true);
    });
    
    httpBackend.flush();
  });
  
  it("Failed -- getAllCustomers should return the appropriate failure response message", function () {
    httpBackend.whenGET("http://localhost:8080/getAllCustomers").respond({
        responseMessage: {
          messageCode:'2',
          message:'Record not found',
          data :[]
          
        }
    });
    
    service.getAllCustomers(function(response){  
       expect(response.responseMessage.data.length == 0 ).toEqual(true);
       expect(response.responseMessage.messageCode == '2').toEqual(true); 
       expect(response.responseMessage.message == 'Record not found').toEqual(true);   
    });
    
    httpBackend.flush();
  });
  
  it("Failed -- getCustomer should return the appropriate success response message", function () {
    httpBackend.whenGET("http://localhost:8080/getCustomer/?id=1").respond({
        responseMessage: {
          messageCode:'1',
          message:'Success',
          data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
        }
    });
    
    service.getCustomer(1,function(response){  
       expect(response.responseMessage.data.length > 0 ).toEqual(true);
       expect(response.responseMessage.data[0].name == 'test' ).toEqual(true);
       expect(response.responseMessage.data[0].address == 'address' ).toEqual(true);
       expect(response.responseMessage.data[0].phoneNumber == '0101010' ).toEqual(true);
    });
    
    httpBackend.flush();
  });
  
  it("Failed -- getCustomer should return the appropriate success response message", function () {
    httpBackend.whenGET("http://localhost:8080/getCustomer/?id=1").respond({
        responseMessage: {
          messageCode:'2',
          message:'Record not found',
          data :[]
          
        }
    });
    
    service.getCustomer(1,function(response){  
       expect(response.responseMessage.data.length == 0 ).toEqual(true);
       expect(response.responseMessage.messageCode == '2').toEqual(true);  
       expect(response.responseMessage.message == 'Record not found').toEqual(true);  
    });
    
    httpBackend.flush();
  });
  
  it("Failed -- addCustomer should return the appropriate success response message", function () {
    httpBackend.whenPOST("http://localhost:8080/addCustomer").respond({
        responseMessage: {
          messageCode:'1',
          message:'Success',
          data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
        }
    });
    
    service.addCustomer(null,function(response){  
       expect(response.responseMessage.data.length == 1 ).toEqual(true);
       expect(response.responseMessage.messageCode == '1').toEqual(true);  
       expect(response.responseMessage.message == 'Success').toEqual(true);  
        
    });
    
    httpBackend.flush();
  });
  
  it("Failed -- addCustomer should return the appropriate failure response message", function () {
    httpBackend.whenPOST("http://localhost:8080/addCustomer").respond({
        responseMessage: {
          messageCode:'0',
          message:'Creation of record failed',
          data :[]
          
        }
    });
    
    service.addCustomer(null,function(response){  
       expect(response.responseMessage.data.length == 0 ).toEqual(true);
       expect(response.responseMessage.messageCode == '0').toEqual(true);  
       expect(response.responseMessage.message == 'Creation of record failed').toEqual(true);  
        
    });
    
    httpBackend.flush();
  });

   it("Failed -- editCustomer should return the appropriate success response message", function () {
    httpBackend.whenPOST("http://localhost:8080/editCustomer").respond({
        responseMessage: {
           messageCode:'1',
          message:'Success',
          data :[{name:'test',address:'address',phoneNumber:'0101010'}]
          
        }
    });
    
    service.editCustomer(null,function(response){  
       expect(response.responseMessage.data.length == 1 ).toEqual(true);
       expect(response.responseMessage.messageCode == '1').toEqual(true);  
       expect(response.responseMessage.message == 'Success').toEqual(true);  
        
    });
    
    httpBackend.flush();
  });
  
  
   it("Failed -- editCustomer should return the appropriate failure response message", function () {
    httpBackend.whenPOST("http://localhost:8080/editCustomer").respond({
        responseMessage: {
          messageCode:'0',
          message:'Update of record failed',
          data :[]
          
        }
    });
    
    service.editCustomer(null,function(response){  
       expect(response.responseMessage.data.length == 0 ).toEqual(true);
       expect(response.responseMessage.messageCode == '0').toEqual(true);  
       expect(response.responseMessage.message == 'Update of record failed').toEqual(true);  
        
    });
    
    httpBackend.flush();
  });
  
  
  it("Failed -- deleteCustomer should return the appropriate failure response message", function () {
    httpBackend.whenGET("http://localhost:8080/deleteCustomer/?id=1").respond({
        responseMessage: {
          messageCode:'0',
          message:'Delete of record failed',
          data :[]
          
        }
    });
    
    service.deleteCustomer(1,function(response){  
       expect(response.responseMessage.data.length == 0 ).toEqual(true);
       expect(response.responseMessage.messageCode == '0').toEqual(true);  
       expect(response.responseMessage.message == 'Delete of record failed').toEqual(true);  
        
    });
    
    httpBackend.flush();
  });
  
   
  it("Failed -- deleteCustomer should return the appropriate success response message", function () {
    httpBackend.whenGET("http://localhost:8080/deleteCustomer/?id=1").respond({
        responseMessage: {
           messageCode:'1',
          message:'Success',
          data :[]
          
        }
    });
    
    service.deleteCustomer(1,function(response){  
       expect(response.responseMessage.data.length == 0 ).toEqual(true);
       expect(response.responseMessage.messageCode == '1').toEqual(true);  
       expect(response.responseMessage.message == 'Success').toEqual(true);  
        
    });
    
    httpBackend.flush();
  });
});