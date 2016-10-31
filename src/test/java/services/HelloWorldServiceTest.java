package services;

import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloWorldServiceTest {

    @Test
    public void getHelloWorldTest() throws MalformedURLException {

        // URL url = new URL("http://localhost:9999/ws/hello?wsdl");
        URL url = new URL("http://mars:8080/web_services_war_exploded/HelloWorldService?wsdl");

        QName qname = new QName("http://services/", "HelloWorldService");

        Service service = Service.create(url, qname);

        HelloWorld hello = service.getPort(HelloWorld.class);

        System.out.println(hello.getHelloWorld("...").getResponse());
    }
}