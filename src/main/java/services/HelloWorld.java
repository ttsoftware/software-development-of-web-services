package services;

import models.HelloWorldModel;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface HelloWorld {

    @WebMethod()
    HelloWorldModel getHelloWorld(@WebParam(name = "name") String name);
}
