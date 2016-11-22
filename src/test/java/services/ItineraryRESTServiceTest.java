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
    }

    @Test
    public void flightsTest() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/flights");
        models.FlightReservation[] flights = resource
                .queryParam("destination", "Berlin")
                .queryParam("from", "Copenhagen")
                .queryParam("day", "7")
                .queryParam("month", "11")
                .queryParam("year", "2016")
                .accept(MediaType.APPLICATION_XML)
                .get(models.FlightReservation[].class);

        assertEquals(flights[0].getAirlineName(), "LameDuck");
    }

    @Test
    public void hotelsTest() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/hotels");
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
