package services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import models.BookingStatus;
import models.CreditCardInfoType;
import models.Itinerary;
import models.ItineraryChangeRequest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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
        flight.FlightReservation[] flights = resource
                .queryParam("destination", "Berlin")
                .queryParam("from", "Copenhagen")
                .queryParam("day", "7")
                .queryParam("month", "11")
                .queryParam("year", "2016")
                .accept(MediaType.APPLICATION_XML)
                .get(flight.FlightReservation[].class);

        assertEquals(flights[0].getAirlineName(), "LameDuck");
    }

    @Test
    public void hotelsTest() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/hotels");
        hotel.Hotel[] hotels = resource
                .queryParam("city", "Copenhagen")
                .queryParam("arrivalDateYear", "2016")
                .queryParam("arrivalDateMonth", "12")
                .queryParam("arrivalDateDay", "28")
                .queryParam("departureDateYear", "2016")
                .queryParam("departureDateMonth", "12")
                .queryParam("departureDateDay", "30")
                .accept(MediaType.APPLICATION_XML)
                .get(hotel.Hotel[].class);

        assertEquals(hotels[0].getName(), "Slumpen");
    }

    @Test
    public void createTest() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary");
        int id = Integer.valueOf(
                resource
                        .accept(MediaType.TEXT_PLAIN)
                        .post(String.class)
        );

        assertTrue(id > 0);
    }

    @Test
    public void showTest() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/1");
        Itinerary itinerary = resource
                .accept(MediaType.APPLICATION_XML)
                .get(Itinerary.class);

        assertEquals(itinerary.getId(), 1);
        assertEquals(itinerary.getBookings().iterator().next().getBookingStatus(), BookingStatus.CONFIRMED);
    }

    @Test(expected = UniformInterfaceException.class)
    public void showTestException() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/-1");
        Itinerary itinerary = resource
                .accept(MediaType.APPLICATION_XML)
                .get(Itinerary.class);
    }

    @Test
    public void cancelTest() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary");
        String id = resource
                .accept(MediaType.TEXT_PLAIN)
                .post(String.class);

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + id);
        Itinerary itinerary = resource
                .accept(MediaType.APPLICATION_XML)
                .get(Itinerary.class);

        CreditCardInfoType creditCard = new CreditCardInfoType();

        creditCard.setExpirationMonth(9);
        creditCard.setExpirationYear(10);
        creditCard.setNumber("50408823");
        creditCard.setName("Tobiasen Inge");

        ItineraryChangeRequest changeRequest = new ItineraryChangeRequest();
        changeRequest.setCardInformation(creditCard);
        changeRequest.setItinerary(itinerary);

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + id + "/cancel");
        boolean success = Boolean.valueOf(
                resource
                        .accept(MediaType.TEXT_PLAIN)
                        .entity(changeRequest, MediaType.APPLICATION_XML)
                        .put(String.class)
        );

        assertTrue(success);
    }

    @Test(expected = UniformInterfaceException.class)
    public void cancelTestException() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/-1/cancel");
        Itinerary itinerary = resource
                .accept(MediaType.TEXT_PLAIN)
                .entity(null, MediaType.APPLICATION_XML)
                .put(Itinerary.class);
    }
}
