package services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import models.*;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ItineraryRESTServiceTest {

    Client client = null;
    WebResource resource = null;
    CreditCardInfoType creditCard;

    @Before
    public void setup() {
        client = Client.create();

        creditCard = new CreditCardInfoType();

        creditCard.setExpirationMonth(9);
        creditCard.setExpirationYear(10);
        creditCard.setNumber("50408823");
        creditCard.setName("Tobiasen Inge");
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

    @Test
    public void createBookingTest() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary");
        int id = Integer.valueOf(
                resource
                        .accept(MediaType.TEXT_PLAIN)
                        .post(String.class)
        );

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + id);
        Itinerary itinerary = resource.accept(MediaType.APPLICATION_XML).get(Itinerary.class);

        resource = client.resource("http://localhost:8080/webservices/travelagency/flights");
        flight.FlightReservation[] flights = resource
                .queryParam("destination", "Berlin")
                .queryParam("from", "Copenhagen")
                .queryParam("day", "7")
                .queryParam("month", "11")
                .queryParam("year", "2016")
                .accept(MediaType.APPLICATION_XML)
                .get(flight.FlightReservation[].class);

        flight.FlightReservation flight = flights[0];
        Booking flightBooking = new Booking();

        flightBooking.setBookingNumber(flight.getBookingNumber());
        flightBooking.setBookingStatus(BookingStatus.UNCONFIRMMED);
        flightBooking.setBookingType(BookingType.FLIGHT);
        flightBooking.setDate(new Date(flight.getFlight().getStart()));
        flightBooking.setPrice(flight.getPrice());
        flightBooking.setItinerary(itinerary);

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + id + "/booking");
        boolean success = Boolean.valueOf(
                resource
                        .entity(flightBooking)
                        .accept(MediaType.TEXT_PLAIN)
                        .put(String.class)
        );

        assertTrue(success);
    }

    @Test
    public void bookTest() {

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

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + id + "/book");
        boolean success = Boolean.valueOf(
                resource
                        .accept(MediaType.TEXT_PLAIN)
                        .entity(changeRequest, MediaType.APPLICATION_XML)
                        .put(String.class)
        );

        assertTrue(success);
    }

    @Test(expected = UniformInterfaceException.class)
    public void bookTestException() {

        resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/-1/book");
        Itinerary itinerary = resource
                .accept(MediaType.TEXT_PLAIN)
                .entity(null, MediaType.APPLICATION_XML)
                .put(Itinerary.class);
    }

    @Test
    public void P1() {
        try {

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/");
            int itineraryId = Integer.valueOf(resource.accept(MediaType.TEXT_PLAIN).post(String.class));

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + itineraryId);
            Itinerary itinerary = resource.accept(MediaType.APPLICATION_XML).get(Itinerary.class);

            resource = client.resource("http://localhost:8080/webservices/travelagency/flights");
            flight.FlightReservation[] flights = resource
                    .queryParam("destination", "Berlin")
                    .queryParam("from", "Copenhagen")
                    .queryParam("day", "7")
                    .queryParam("month", "11")
                    .queryParam("year", "2016")
                    .accept(MediaType.APPLICATION_XML)
                    .get(flight.FlightReservation[].class);

            flight.FlightReservation flight = flights[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight.getFlight().getStart()));
            flightBooking1.setPrice(flight.getPrice());
            flightBooking1.setItinerary(itinerary);

            ItineraryChangeRequest changeRequest = new ItineraryChangeRequest();
            changeRequest.setCardInformation(creditCard);
            changeRequest.setItinerary(itinerary);

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + itineraryId + "/booking");
            boolean success = Boolean.valueOf(
                    resource
                            .entity(flightBooking1)
                            .accept(MediaType.TEXT_PLAIN)
                            .put(String.class)
            );

            assertTrue(success);

            resource = client.resource("http://localhost:8080/webservices/travelagency/hotels");
            hotel.Hotel[] hotels = resource
                    .queryParam("city", "Berlin")
                    .queryParam("arrivalDateYear", "2016")
                    .queryParam("arrivalDateMonth", "11")
                    .queryParam("arrivalDateDay", "7")
                    .queryParam("departureDateYear", "2016")
                    .queryParam("departureDateMonth", "11")
                    .queryParam("departureDateDay", "12")
                    .accept(MediaType.APPLICATION_XML)
                    .get(hotel.Hotel[].class);

            hotel.Hotel hotel1 = hotels[0];
            Booking hotelBooking = new Booking();
            hotelBooking.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking.setBookingType(BookingType.HOTEL);
            hotelBooking.setPrice(hotel1.getPrice());
            hotelBooking.setDate(new Date(hotel1.getOpens()));
            hotelBooking.setItinerary(itinerary);

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + itineraryId + "/booking");
            success = Boolean.valueOf(
                    resource
                            .entity(hotelBooking)
                            .accept(MediaType.TEXT_PLAIN)
                            .put(String.class)
            );

            assertTrue(success);

            resource = client.resource("http://localhost:8080/webservices/travelagency/flights");
            flights = resource
                    .queryParam("destination", "Berlin")
                    .queryParam("from", "London")
                    .queryParam("day", "12")
                    .queryParam("month", "11")
                    .queryParam("year", "2016")
                    .accept(MediaType.APPLICATION_XML)
                    .get(flight.FlightReservation[].class);

            flight = flights[0];
            Booking flightBooking2 = new Booking();

            flightBooking2.setBookingNumber(flight.getBookingNumber());
            flightBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking2.setBookingType(BookingType.FLIGHT);
            flightBooking2.setDate(new Date(flight.getFlight().getStart()));
            flightBooking2.setPrice(flight.getPrice());
            flightBooking2.setItinerary(itinerary);

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + itineraryId + "/booking");
            success = Boolean.valueOf(
                    resource
                            .entity(flightBooking2)
                            .accept(MediaType.TEXT_PLAIN)
                            .put(String.class)
            );

            assertTrue(success);

            resource = client.resource("http://localhost:8080/webservices/travelagency/flights");
            flights = resource
                    .queryParam("destination", "London")
                    .queryParam("from", "Amsterdam")
                    .queryParam("day", "20")
                    .queryParam("month", "11")
                    .queryParam("year", "2016")
                    .accept(MediaType.APPLICATION_XML)
                    .get(flight.FlightReservation[].class);
            flight = flights[0];
            Booking flightBooking3 = new Booking();

            flightBooking3.setBookingNumber(flight.getBookingNumber());
            flightBooking3.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking3.setBookingType(BookingType.FLIGHT);
            flightBooking3.setDate(new Date(flight.getFlight().getStart()));
            flightBooking3.setPrice(flight.getPrice());
            flightBooking3.setItinerary(itinerary);

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + itineraryId + "/booking");
            success = Boolean.valueOf(
                    resource
                            .entity(flightBooking3)
                            .accept(MediaType.TEXT_PLAIN)
                            .put(String.class)
            );

            assertTrue(success);

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + itineraryId);
            Itinerary itineraryWithBookings = resource
                    .accept(MediaType.APPLICATION_XML)
                    .get(Itinerary.class);

            Collection<Booking> bookings = itineraryWithBookings.getBookings();

            Iterator<Booking> iterator = bookings.iterator();

            assertTrue(bookings.contains(flightBooking1));
            assertTrue(bookings.contains(flightBooking2));
            assertTrue(bookings.contains(flightBooking3));
            assertTrue(bookings.contains(hotelBooking));

            while (iterator.hasNext()) {
                Booking b = iterator.next();
                assertEquals(b.getBookingStatus(), BookingStatus.UNCONFIRMMED);
            }

            changeRequest = new ItineraryChangeRequest();
            changeRequest.setCardInformation(creditCard);
            changeRequest.setItinerary(itinerary);

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + itineraryId + "/book");
            success = Boolean.valueOf(
                    resource
                            .accept(MediaType.TEXT_PLAIN)
                            .entity(changeRequest, MediaType.APPLICATION_XML)
                            .put(String.class)
            );

            assertTrue(success);

            resource = client.resource("http://localhost:8080/webservices/travelagency/itinerary/" + itineraryId);
            Itinerary itineraryWithBookingsConfirmed = resource
                    .accept(MediaType.APPLICATION_XML)
                    .get(Itinerary.class);

            Collection<Booking> bookingsConfirmed = itineraryWithBookingsConfirmed.getBookings();
            Iterator<Booking> iterator2 = bookingsConfirmed.iterator();

            while (iterator2.hasNext()) {
                Booking b = iterator2.next();
                assertEquals(b.getBookingStatus(), BookingStatus.CONFIRMED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    @Test
    public void P2() {
        int itineraryId = 0;
        try {
            itineraryId = travelAgencyInterface.createItinerary();
            Itinerary itinerary = travelAgencyInterface.getItinerary(itineraryId);
            flight.FlightReservation[] flight = travelAgencyInterface.getFlights("Copenhagen", "Berlin", new CustomDate(2016, 11, 7));
            flight.FlightReservation flight1 = flight[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight1.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking1.setPrice(flight1.getPrice());
            flightBooking1.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking1);


            Itinerary itineraryWithBookings = travelAgencyInterface.getItinerary(itineraryId);
            Collection<Booking> bookings = itineraryWithBookings.getBookings();

            Iterator<Booking> iterator = bookings.iterator();

            Assert.assertTrue(bookings.contains(flightBooking1));

            while (iterator.hasNext()) {
                Booking b = iterator.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.UNCONFIRMMED);
            }

            travelAgencyInterface.cancelItinerarie(itineraryId, creditCard);

            Itinerary itineraryWithBookingsConfirmed = travelAgencyInterface.getItinerary(itineraryId);
            Collection<Booking> bookingsConfirmed = itineraryWithBookingsConfirmed.getBookings();

            Iterator<Booking> iterator2 = bookingsConfirmed.iterator();

            while (iterator2.hasNext()) {
                Booking b = iterator2.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.CANCELLED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void B() {
        int itineraryId = 0;
        try {
            itineraryId = travelAgencyInterface.createItinerary();
            Itinerary itinerary = travelAgencyInterface.getItinerary(itineraryId);
            hotel.Hotel[] hotel = travelAgencyInterface.getHotels("Copenhagen", new CustomDate(2016, 11, 7), new CustomDate(2016, 11, 7));
            //To expensive
            hotel.Hotel hotel1 = hotel[0];


            Booking hotelBooking = new Booking();
            hotelBooking.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking.setBookingType(BookingType.HOTEL);
            hotelBooking.setPrice(hotel1.getPrice());
            hotelBooking.setDate(new Date(hotel1.getOpens()));
            hotelBooking.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, hotelBooking);


            flight.FlightReservation[] flight = travelAgencyInterface.getFlights("Berlin", "London", new CustomDate(2016, 11, 12));
            FlightReservation flight1 = flight[0];
            Booking flightBooking2 = new Booking();

            flightBooking2.setBookingNumber(flight1.getBookingNumber());
            flightBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking2.setBookingType(BookingType.FLIGHT);
            flightBooking2.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking2.setPrice(flight1.getPrice());
            flightBooking2.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking2);

            flight = travelAgencyInterface.getFlights("London", "Amsterdam", new CustomDate(2016, 11, 20));
            flight1 = flight[0];
            Booking flightBooking3 = new Booking();

            flightBooking3.setBookingNumber(flight1.getBookingNumber());
            flightBooking3.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking3.setBookingType(BookingType.FLIGHT);
            flightBooking3.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking3.setPrice(flight1.getPrice());
            flightBooking3.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking3);

            Itinerary itineraryWithBookings = travelAgencyInterface.getItinerary(itineraryId);
            Collection<Booking> bookings = itineraryWithBookings.getBookings();

            Iterator<Booking> iterator = bookings.iterator();

            while (iterator.hasNext()) {
                Booking b = iterator.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.UNCONFIRMMED);
            }

            try {
                travelAgencyInterface.bookItinerarie(itineraryId, creditCard);
            } catch (BookingFaultException e) {
                System.out.println("Exception thrown");
            } finally {
                Itinerary itineraryWithBookingsConfirmed = travelAgencyInterface.getItinerary(itineraryId);
                Collection<Booking> bookingsConfirmed = itineraryWithBookingsConfirmed.getBookings();

                Iterator<Booking> iterator2 = bookingsConfirmed.iterator();

                while (iterator2.hasNext()) {
                    Booking b = iterator2.next();
                    if (b.getBookingNumber().equals(hotelBooking.getBookingNumber())) {
                        Assert.assertEquals(BookingStatus.CANCELLED, b.getBookingStatus());
                    } else {
                        Assert.assertEquals(BookingStatus.CONFIRMED, b.getBookingStatus());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void C1() {
        try {
            int itineraryId = travelAgencyInterface.createItinerary();
            Itinerary itinerary = travelAgencyInterface.getItinerary(itineraryId);
            flight.FlightReservation[] flight = travelAgencyInterface.getFlights("Copenhagen", "Berlin", new CustomDate(2016, 11, 7));
            flight.FlightReservation flight1 = flight[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight1.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking1.setPrice(flight1.getPrice());
            flightBooking1.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking1);

            hotel.Hotel[] hotels = travelAgencyInterface.getHotels("Berlin", new CustomDate(2016, 11, 7), new CustomDate(2016, 11, 12));
            hotel.Hotel hotel1 = hotels[0];
            Booking hotelBooking = new Booking();
            hotelBooking.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking.setBookingType(BookingType.HOTEL);
            hotelBooking.setPrice(hotel1.getPrice());
            hotelBooking.setDate(new Date(hotel1.getOpens()));
            hotelBooking.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, hotelBooking);

            flight = travelAgencyInterface.getFlights("Berlin", "London", new CustomDate(2016, 11, 12));
            flight1 = flight[0];
            Booking flightBooking2 = new Booking();

            flightBooking2.setBookingNumber(flight1.getBookingNumber());
            flightBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking2.setBookingType(BookingType.FLIGHT);
            flightBooking2.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking2.setPrice(flight1.getPrice());
            flightBooking2.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking2);

            flight = travelAgencyInterface.getFlights("London", "Amsterdam", new CustomDate(2016, 11, 20));
            flight1 = flight[0];
            Booking flightBooking3 = new Booking();

            flightBooking3.setBookingNumber(flight1.getBookingNumber());
            flightBooking3.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking3.setBookingType(BookingType.FLIGHT);
            flightBooking3.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking3.setPrice(flight1.getPrice());
            flightBooking3.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking3);

            Itinerary itineraryWithBookings = travelAgencyInterface.getItinerary(itineraryId);
            Collection<Booking> bookings = itineraryWithBookings.getBookings();

            Iterator<Booking> iterator = bookings.iterator();

            Assert.assertTrue(bookings.contains(flightBooking1));
            Assert.assertTrue(bookings.contains(flightBooking2));
            Assert.assertTrue(bookings.contains(flightBooking3));
            Assert.assertTrue(bookings.contains(hotelBooking));

            while (iterator.hasNext()) {
                Booking b = iterator.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.UNCONFIRMMED);
            }

            travelAgencyInterface.bookItinerarie(itineraryId, creditCard);

            Itinerary itineraryWithBookingsConfirmed = travelAgencyInterface.getItinerary(itineraryId);
            Collection<Booking> bookingsConfirmed = itineraryWithBookingsConfirmed.getBookings();

            Iterator<Booking> iterator2 = bookingsConfirmed.iterator();

            while (iterator2.hasNext()) {
                Booking b = iterator2.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.CONFIRMED);
            }

            travelAgencyInterface.cancelItinerarie(itineraryId, creditCard);

            Itinerary itineraryWithBookingsCancelled = travelAgencyInterface.getItinerary(itineraryId);
            Collection<Booking> bookingsCancelled = itineraryWithBookingsCancelled.getBookings();

            Iterator<Booking> iterator3 = bookingsCancelled.iterator();

            while (iterator3.hasNext()) {
                Booking b = iterator3.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.CANCELLED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void C2() {
        try {
            int itineraryId = travelAgencyInterface.createItinerary();
            Itinerary itinerary = travelAgencyInterface.getItinerary(itineraryId);
            flight.FlightReservation[] flight = travelAgencyInterface.getFlights("Copenhagen", "Berlin", new CustomDate(2016, 11, 7));
            flight.FlightReservation flight1 = flight[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight1.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking1.setPrice(flight1.getPrice());
            flightBooking1.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking1);

            hotel.Hotel[] hotels = travelAgencyInterface.getHotels("Berlin", new CustomDate(2016, 11, 7), new CustomDate(2016, 11, 12));
            hotel.Hotel hotel1 = hotels[0];
            Booking hotelBooking = new Booking();
            hotelBooking.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking.setBookingType(BookingType.HOTEL);
            hotelBooking.setPrice(hotel1.getPrice());
            hotelBooking.setDate(new Date(hotel1.getOpens()));
            hotelBooking.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, hotelBooking);

            flight = travelAgencyInterface.getFlights("Berlin", "London", new CustomDate(2016, 11, 12));
            flight1 = flight[0];
            Booking flightBooking2 = new Booking();

            flightBooking2.setBookingNumber(flight1.getBookingNumber());
            flightBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking2.setBookingType(BookingType.FLIGHT);
            flightBooking2.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking2.setPrice(flight1.getPrice());
            flightBooking2.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking2);

            flight = travelAgencyInterface.getFlights("London", "Amsterdam", new CustomDate(2016, 11, 20));
            flight1 = flight[0];
            Booking flightBooking3 = new Booking();

            flightBooking3.setBookingNumber(flight1.getBookingNumber());
            flightBooking3.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking3.setBookingType(BookingType.FLIGHT);
            flightBooking3.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking3.setPrice(flight1.getPrice());
            flightBooking3.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking3);

            Itinerary itineraryWithBookings = travelAgencyInterface.getItinerary(itineraryId);
            Collection<Booking> bookings = itineraryWithBookings.getBookings();

            Iterator<Booking> iterator = bookings.iterator();

            while (iterator.hasNext()) {
                Booking b = iterator.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.UNCONFIRMMED);
            }

            travelAgencyInterface.bookItinerarie(itineraryId, creditCard);

            Itinerary itineraryBooked = travelAgencyInterface.getItinerary(itineraryId);
            Collection<Booking> bookingsBooked = itineraryBooked.getBookings();

            Iterator<Booking> iterator2 = bookingsBooked.iterator();

            while (iterator2.hasNext()) {
                Booking b = iterator.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.CONFIRMED);
            }

            travelAgencyInterface.cancelItinerarie(itineraryId, creditCard);

        } catch (Exception e) {

        }
    }
    */
}
