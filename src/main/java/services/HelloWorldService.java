package services;

import models.HelloWorldModel;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.JAXBException;

@WebService(name = "HelloWorldService", endpointInterface = "services.HelloWorldService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class HelloWorldService {

    @WebMethod
    public HelloWorldModel getHelloWorld() throws JAXBException {
        return new HelloWorldModel();
    }
}
