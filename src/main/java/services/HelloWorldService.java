package services;

import models.HelloWorldModel;

import javax.jws.WebService;

@WebService
public class HelloWorldService implements HelloWorld {

    public HelloWorldModel getHelloWorld(String name) {
        return new HelloWorldModel(name, "akdngaijngaldnzg");
    }
}