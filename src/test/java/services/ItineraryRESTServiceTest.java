package services;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ItineraryRESTServiceTest {

    @Before
    public void setup() {
        Client client = ClientFactory.newClient();
        WebTarget target = client.target("http://www.myserver.com/book");
        Invocation invocation = target.request(MediaType.TEXT_PLAIN).buildGet();
        Response response = invocation.invoke();
    }

    @Test
    public void flightsTest() {

    }
}
