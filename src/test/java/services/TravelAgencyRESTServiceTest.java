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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TravelAgencyRESTServiceTest {

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

        resource = client.resource("http://localhost:8282/webservices/travelagency/flights");
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

        resource = client.resource("http://localhost:8282/webservices/travelagency/hotels");
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

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary");
        int id = Integer.valueOf(
                resource
                        .accept(MediaType.TEXT_PLAIN)
                        .post(String.class)
        );

        assertTrue(id > 0);
    }

    @Test
    public void showTest() {

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/1");
        Itinerary itinerary = resource
                .accept(MediaType.APPLICATION_XML)
                .get(Itinerary.class);

        assertEquals(itinerary.getId(), 1);
    }

    @Test(expected = UniformInterfaceException.class)
    public void showTestException() {

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/-1");
        Itinerary itinerary = resource
                .accept(MediaType.APPLICATION_XML)
                .get(Itinerary.class);
    }

    @Test
    public void cancelTest() {

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary");
        String id = resource
                .accept(MediaType.TEXT_PLAIN)
                .post(String.class);

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id);
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

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id + "/cancel");
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

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/-1/cancel");
        Itinerary itinerary = resource
                .accept(MediaType.TEXT_PLAIN)
                .entity(null, MediaType.APPLICATION_XML)
                .put(Itinerary.class);
    }

    @Test
    public void createBookingTest() {

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary");
        int id = Integer.valueOf(
                resource
                        .accept(MediaType.TEXT_PLAIN)
                        .post(String.class)
        );

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id);
        Itinerary itinerary = resource.accept(MediaType.APPLICATION_XML).get(Itinerary.class);

        resource = client.resource("http://localhost:8282/webservices/travelagency/flights");
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

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id + "/booking");
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

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary");
        String id = resource
                .accept(MediaType.TEXT_PLAIN)
                .post(String.class);

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id);
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

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id + "/book");
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

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/-1/book");
        Itinerary itinerary = resource
                .accept(MediaType.TEXT_PLAIN)
                .entity(null, MediaType.APPLICATION_XML)
                .put(Itinerary.class);
    }

    @Test
    public void P1() {
        try {

            int itineraryId = create();
            Itinerary itinerary = show(itineraryId);

            flight.FlightReservation[] flights = flights("Copenhagen", "Berlin", "7", "11", "2016");
            flight.FlightReservation flight = flights[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight.getFlight().getStart()));
            flightBooking1.setPrice(flight.getPrice());
            flightBooking1.setItinerary(itinerary);

            boolean success = createBooking(itineraryId, flightBooking1);
            assertTrue(success);

            hotel.Hotel[] hotels = hotels(
                    "Berlin",
                    "2016",
                    "11",
                    "7",
                    "2016",
                    "11",
                    "12"
            );
            hotel.Hotel hotel1 = hotels[0];
            Booking hotelBooking = new Booking();
            hotelBooking.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking.setBookingType(BookingType.HOTEL);
            hotelBooking.setPrice(hotel1.getPrice());
            hotelBooking.setDate(new Date(hotel1.getOpens()));
            hotelBooking.setItinerary(itinerary);

            success = createBooking(itineraryId, hotelBooking);
            assertTrue(success);

            flights = flights("Berlin", "London", "12", "11", "2016");
            flight = flights[0];
            Booking flightBooking2 = new Booking();

            flightBooking2.setBookingNumber(flight.getBookingNumber());
            flightBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking2.setBookingType(BookingType.FLIGHT);
            flightBooking2.setDate(new Date(flight.getFlight().getStart()));
            flightBooking2.setPrice(flight.getPrice());
            flightBooking2.setItinerary(itinerary);

            success = createBooking(itineraryId, flightBooking2);
            assertTrue(success);

            flights = flights("London", "Amsterdam", "20", "11", "2016");
            flight = flights[0];
            Booking flightBooking3 = new Booking();

            flightBooking3.setBookingNumber(flight.getBookingNumber());
            flightBooking3.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking3.setBookingType(BookingType.FLIGHT);
            flightBooking3.setDate(new Date(flight.getFlight().getStart()));
            flightBooking3.setPrice(flight.getPrice());
            flightBooking3.setItinerary(itinerary);

            success = createBooking(itineraryId, flightBooking3);
            assertTrue(success);

            Itinerary itineraryWithBookings = show(itineraryId);

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

            success = book(itineraryId, creditCard);

            assertTrue(success);

            Itinerary itineraryWithBookingsConfirmed = show(itineraryId);

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

    @Test
    public void P2() {
        int itineraryId = 0;
        try {
            itineraryId = create();
            Itinerary itinerary = show(itineraryId);
            flight.FlightReservation[] flights = flights("Copenhagen", "Berlin", "7", "11", "2016");
            flight.FlightReservation flight1 = flights[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight1.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking1.setPrice(flight1.getPrice());
            flightBooking1.setItinerary(itinerary);
            createBooking(itineraryId, flightBooking1);

            Itinerary itineraryWithBookings = show(itineraryId);
            Collection<Booking> bookings = itineraryWithBookings.getBookings();

            Iterator<Booking> iterator = bookings.iterator();

            assertTrue(bookings.contains(flightBooking1));

            while (iterator.hasNext()) {
                Booking b = iterator.next();
                assertEquals(b.getBookingStatus(), BookingStatus.UNCONFIRMMED);
            }

            cancel(itineraryId, creditCard);

            Itinerary itineraryWithBookingsConfirmed = show(itineraryId);
            Collection<Booking> bookingsConfirmed = itineraryWithBookingsConfirmed.getBookings();

            Iterator<Booking> iterator2 = bookingsConfirmed.iterator();

            while (iterator2.hasNext()) {
                Booking b = iterator2.next();
                assertEquals(b.getBookingStatus(), BookingStatus.CANCELLED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void B() {
        int itineraryId = 0;
        try {
            itineraryId = create();
            Itinerary itinerary = show(itineraryId);
            hotel.Hotel[] hotel = hotels(
                    "Copenhagen",
                    "2016",
                    "11",
                    "7",
                    "2016",
                    "11",
                    "7"
            );
            //To expensive
            hotel.Hotel hotel1 = hotel[0];

            Booking hotelBooking = new Booking();
            hotelBooking.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking.setBookingType(BookingType.HOTEL);
            hotelBooking.setPrice(hotel1.getPrice());
            hotelBooking.setDate(new Date(hotel1.getOpens()));
            hotelBooking.setItinerary(itinerary);
            createBooking(itineraryId, hotelBooking);

            flight.FlightReservation[] flights = flights("Berlin", "London", "12", "11", "2016");
            flight.FlightReservation flight1 = flights[0];
            Booking flightBooking2 = new Booking();

            flightBooking2.setBookingNumber(flight1.getBookingNumber());
            flightBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking2.setBookingType(BookingType.FLIGHT);
            flightBooking2.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking2.setPrice(flight1.getPrice());
            flightBooking2.setItinerary(itinerary);
            createBooking(itineraryId, flightBooking2);

            flights = flights("London", "Amsterdam", "20", "11", "2016");
            flight1 = flights[0];
            Booking flightBooking3 = new Booking();

            flightBooking3.setBookingNumber(flight1.getBookingNumber());
            flightBooking3.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking3.setBookingType(BookingType.FLIGHT);
            flightBooking3.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking3.setPrice(flight1.getPrice());
            flightBooking3.setItinerary(itinerary);
            createBooking(itineraryId, flightBooking3);

            Itinerary itineraryWithBookings = show(itineraryId);
            Collection<Booking> bookings = itineraryWithBookings.getBookings();

            Iterator<Booking> iterator = bookings.iterator();

            while (iterator.hasNext()) {
                Booking b = iterator.next();
                assertEquals(b.getBookingStatus(), BookingStatus.UNCONFIRMMED);
            }

            try {
                book(itineraryId, creditCard);
            } finally {
                Itinerary itineraryWithBookingsConfirmed = show(itineraryId);
                Collection<Booking> bookingsConfirmed = itineraryWithBookingsConfirmed.getBookings();

                Iterator<Booking> iterator2 = bookingsConfirmed.iterator();

                while (iterator2.hasNext()) {
                    Booking b = iterator2.next();
                    if (b.getBookingNumber().equals(hotelBooking.getBookingNumber())) {
                        assertEquals(BookingStatus.CANCELLED, b.getBookingStatus());
                    } else {
                        assertEquals(BookingStatus.CONFIRMED, b.getBookingStatus());
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
            int itineraryId = create();
            Itinerary itinerary = show(itineraryId);
            flight.FlightReservation[] flights = flights("Copenhagen", "Berlin", "7", "11", "2016");
            flight.FlightReservation flight1 = flights[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight1.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking1.setPrice(flight1.getPrice());
            flightBooking1.setItinerary(itinerary);
            createBooking(itineraryId, flightBooking1);

            hotel.Hotel[] hotels = hotels("Berlin", "2016", "11", "7", "2016", "11", "12");
            hotel.Hotel hotel1 = hotels[0];
            Booking hotelBooking = new Booking();
            hotelBooking.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking.setBookingType(BookingType.HOTEL);
            hotelBooking.setPrice(hotel1.getPrice());
            hotelBooking.setDate(new Date(hotel1.getOpens()));
            hotelBooking.setItinerary(itinerary);
            createBooking(itineraryId, hotelBooking);

            flights = flights("Berlin", "London", "12", "11", "2016");
            flight1 = flights[0];
            Booking flightBooking2 = new Booking();

            flightBooking2.setBookingNumber(flight1.getBookingNumber());
            flightBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking2.setBookingType(BookingType.FLIGHT);
            flightBooking2.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking2.setPrice(flight1.getPrice());
            flightBooking2.setItinerary(itinerary);
            createBooking(itineraryId, flightBooking2);

            flights = flights("London", "Amsterdam", "20", "11", "2016");
            flight1 = flights[0];
            Booking flightBooking3 = new Booking();

            flightBooking3.setBookingNumber(flight1.getBookingNumber());
            flightBooking3.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking3.setBookingType(BookingType.FLIGHT);
            flightBooking3.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking3.setPrice(flight1.getPrice());
            flightBooking3.setItinerary(itinerary);
            createBooking(itineraryId, flightBooking3);

            Itinerary itineraryWithBookings = show(itineraryId);
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

            book(itineraryId, creditCard);

            Itinerary itineraryWithBookingsConfirmed = show(itineraryId);
            Collection<Booking> bookingsConfirmed = itineraryWithBookingsConfirmed.getBookings();

            Iterator<Booking> iterator2 = bookingsConfirmed.iterator();

            while (iterator2.hasNext()) {
                Booking b = iterator2.next();
                assertEquals(b.getBookingStatus(), BookingStatus.CONFIRMED);
            }

            cancel(itineraryId, creditCard);

            Itinerary itineraryWithBookingsCancelled = show(itineraryId);
            Collection<Booking> bookingsCancelled = itineraryWithBookingsCancelled.getBookings();

            Iterator<Booking> iterator3 = bookingsCancelled.iterator();

            while (iterator3.hasNext()) {
                Booking b = iterator3.next();
                assertEquals(b.getBookingStatus(), BookingStatus.CANCELLED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void C2() {
        int itineraryId = create();
        try {
            Itinerary itinerary = show(itineraryId);

            hotel.Hotel[] hotels = hotels("Berlin", "2016", "11", "7", "2016", "11", "12");
            hotel.Hotel hotel1 = hotels[0];
            Booking hotelBooking1 = new Booking();
            hotelBooking1.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking1.setBookingType(BookingType.HOTEL);
            hotelBooking1.setPrice(hotel1.getPrice());
            hotelBooking1.setDate(new Date(hotel1.getOpens()));
            hotelBooking1.setItinerary(itinerary);
            createBooking(itineraryId, hotelBooking1);

            flight.FlightReservation[] flights = flights("Berlin", "Copenhagen", "12", "11", "2016");
            flight.FlightReservation flight1 = flights[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight1.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking1.setPrice(flight1.getPrice());
            flightBooking1.setItinerary(itinerary);
            createBooking(itineraryId, flightBooking1);

            hotels = hotels("Copenhagen", "2016", "11", "7", "2016", "11", "7");
            hotel.Hotel hotel2 = hotels[1];
            Booking hotelBooking2 = new Booking();
            hotelBooking2.setBookingNumber(hotel2.getBookingNumber());
            hotelBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking2.setBookingType(BookingType.HOTEL);
            hotelBooking2.setPrice(hotel2.getPrice());
            hotelBooking2.setDate(new Date(hotel2.getOpens()));
            hotelBooking2.setItinerary(itinerary);
            createBooking(itineraryId, hotelBooking2);

            Itinerary itineraryWithBookings = show(itineraryId);
            Collection<Booking> bookings = itineraryWithBookings.getBookings();

            Iterator<Booking> iterator = bookings.iterator();

            while (iterator.hasNext()) {
                Booking b = iterator.next();
                assertEquals(b.getBookingStatus(), BookingStatus.UNCONFIRMMED);
            }

            book(itineraryId, creditCard);

            Itinerary itineraryBooked = show(itineraryId);
            Collection<Booking> bookingsBooked = itineraryBooked.getBookings();

            Iterator<Booking> iterator2 = bookingsBooked.iterator();

            while (iterator2.hasNext()) {
                Booking b = iterator2.next();
                assertEquals(b.getBookingStatus(), BookingStatus.CONFIRMED);
            }

            String correctNumber = creditCard.getNumber();
            creditCard.setNumber("stalin");
            cancel(itineraryId, creditCard);
            creditCard.setNumber(correctNumber);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Itinerary itinerary = show(itineraryId);

            List<Booking> bookings = (List<Booking>) itinerary.getBookings();

            assertEquals(bookings.get(0).getBookingStatus(), BookingStatus.CANCELLED);
            assertEquals(bookings.get(1).getBookingStatus(), BookingStatus.CONFIRMED);
            assertEquals(bookings.get(2).getBookingStatus(), BookingStatus.CANCELLED);
        }
    }

    private Itinerary show(int id) {
        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id);
        return resource.accept(MediaType.APPLICATION_XML).get(Itinerary.class);
    }

    private int create() {
        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/");
        int itineraryId = Integer.valueOf(resource.accept(MediaType.TEXT_PLAIN).post(String.class));
        return itineraryId;
    }

    private boolean cancel(int id, CreditCardInfoType creditCard) {
        ItineraryChangeRequest changeRequest = new ItineraryChangeRequest();
        Itinerary itinerary = show(id);
        changeRequest.setCardInformation(creditCard);
        changeRequest.setItinerary(itinerary);

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id + "/cancel");
        return Boolean.valueOf(
                resource
                        .accept(MediaType.TEXT_PLAIN)
                        .entity(changeRequest, MediaType.APPLICATION_XML)
                        .put(String.class)
        );
    }

    private boolean book(int id, CreditCardInfoType creditCard) {
        ItineraryChangeRequest changeRequest = new ItineraryChangeRequest();
        Itinerary itinerary = show(id);
        changeRequest.setCardInformation(creditCard);
        changeRequest.setItinerary(itinerary);

        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id + "/book");
        return Boolean.valueOf(
                resource
                        .accept(MediaType.TEXT_PLAIN)
                        .entity(changeRequest, MediaType.APPLICATION_XML)
                        .put(String.class)
        );
    }

    private boolean createBooking(int id, Booking booking) {
        resource = client.resource("http://localhost:8282/webservices/travelagency/itinerary/" + id + "/booking");
        return Boolean.valueOf(
                resource
                        .entity(booking)
                        .accept(MediaType.TEXT_PLAIN)
                        .put(String.class)
        );
    }

    private flight.FlightReservation[] flights(String from,
                                               String destination,
                                               String day,
                                               String month,
                                               String year) {
        resource = client.resource("http://localhost:8282/webservices/travelagency/flights");
        return resource
                .queryParam("destination", destination)
                .queryParam("from", from)
                .queryParam("day", day)
                .queryParam("month", month)
                .queryParam("year", year)
                .accept(MediaType.APPLICATION_XML)
                .get(flight.FlightReservation[].class);
    }

    private hotel.Hotel[] hotels(String city,
                                 String arrivalDateYear,
                                 String arrivalDateMonth,
                                 String arrivalDateDay,
                                 String departureDateYear,
                                 String departureDateMonth,
                                 String departureDateDay) {

        resource = client.resource("http://localhost:8282/webservices/travelagency/hotels");
        return resource
                .queryParam("city", city)
                .queryParam("arrivalDateYear", arrivalDateYear)
                .queryParam("arrivalDateMonth", arrivalDateMonth)
                .queryParam("arrivalDateDay", arrivalDateDay)
                .queryParam("departureDateYear", departureDateYear)
                .queryParam("departureDateMonth", departureDateMonth)
                .queryParam("departureDateDay", departureDateDay)
                .accept(MediaType.APPLICATION_XML)
                .get(hotel.Hotel[].class);
    }
}
