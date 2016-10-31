package services;

import models.HelloWorldModel;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "HelloWorldService", endpointInterface = "services.HelloWorld")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface HelloWorld {

    @WebMethod(operationName = "getHelloWorld")
    HelloWorldModel getHelloWorld(@WebParam(name = "name") String name);
}