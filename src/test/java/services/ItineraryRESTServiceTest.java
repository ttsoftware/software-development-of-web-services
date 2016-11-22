package services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;


public class ItineraryRESTServiceTest {

    Client client = null;
    WebResource resource = null;

    @Before
    public void setup() {
        client = Client.create();
        resource = client.resource("http://localhost:8080/webservices/travelagency/hotels");
    }

    @Test
    public void hotelsTest() {

        models.Hotel[] hotels = resource
                .queryParam("city", "Copenhagen")
                .queryParam("arrivalDateYear", "2016")
                .queryParam("arrivalDateMonth", "12")
                .queryParam("arrivalDateDay", "28")
                .queryParam("departureDateYear", "2016")
                .queryParam("departureDateMonth", "12")
                .queryParam("departureDateDay", "30")
                .accept(MediaType.APPLICATION_XML)
                .get(models.Hotel[].class);

        assertEquals(hotels[0].getName(), "Slumpen");
    }
}
