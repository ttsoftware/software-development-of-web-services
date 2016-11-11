package services;

import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class AirlineServiceTest {

    private AirlineService airlineService;

    @Before
    public void setup() {
        try {
            URL url = new URL("http://localhost:8080/web_services_war_exploded/AirlineService?wsdl");
            QName qname = new QName("http://services/", "AirlineService");
            Service service = Service.create(url, qname);
            airlineService = service.getPort(AirlineService.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFlightsTest() {
        airlineService.getFlights("Copenhagen", "Berlin", new Date(05, 11, 2016));
    }
}
