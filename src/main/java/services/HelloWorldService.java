package services;

import models.HelloWorldModel;

import javax.jws.WebService;

@WebService(name = "HelloWorldService", endpointInterface = "services.HelloWorld")
public class HelloWorldService implements HelloWorld {

    public HelloWorldModel getHelloWorld(String name) {
        HelloWorldModel model = new HelloWorldModel();
        return model;
    }
}
