package services;

import flight.FlightReservation;
import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.exceptions.BookingFaultException;
import services.exceptions.ItineraryDoesNotExistException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TravelAgencySoapServiceTest {

    private TravelAgencySoapInterface travelAgencyInterface;
    private bank.CreditCardInfoType creditCard;

    @Before
    public void setup() {
        try {
            URL url = new URL("http://localhost:8080/webservices/TravelAgencySoapService?wsdl");
            QName qname = new QName("http://services/", "TravelAgencySoapService");
            Service service = Service.create(url, qname);
            travelAgencyInterface = service.getPort(TravelAgencySoapInterface.class);

            creditCard = new bank.CreditCardInfoType();
            bank.CreditCardInfoType.ExpirationDate expDate = new bank.CreditCardInfoType.ExpirationDate();

            expDate.setMonth(9);
            expDate.setYear(10);

            creditCard.setExpirationDate(expDate);
            creditCard.setNumber("50408823");
            creditCard.setName("Tobiasen Inge");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getItineraryTest() {
        try {
            int id = travelAgencyInterface.createItinerary();
            Itinerary itinerary = travelAgencyInterface.getItinerary(id);
            Assert.assertNotNull(itinerary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getItinerariesTest() {
        try {
            Itinerary[] itinerary = travelAgencyInterface.getItineraries();
            Assert.assertNotNull(itinerary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void P1() {
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


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        int itineraryId = 0;
        try {
            itineraryId = travelAgencyInterface.createItinerary();
            Itinerary itinerary = travelAgencyInterface.getItinerary(itineraryId);

            hotel.Hotel[] hotels = travelAgencyInterface.getHotels("Berlin", new CustomDate(2016, 11, 7), new CustomDate(2016, 11, 12));
            hotel.Hotel hotel1 = hotels[0];
            Booking hotelBooking1 = new Booking();
            hotelBooking1.setBookingNumber(hotel1.getBookingNumber());
            hotelBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking1.setBookingType(BookingType.HOTEL);
            hotelBooking1.setPrice(hotel1.getPrice());
            hotelBooking1.setDate(new Date(hotel1.getOpens()));
            hotelBooking1.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, hotelBooking1);

            flight.FlightReservation[] flights = travelAgencyInterface.getFlights("Berlin", "Copenhagen", new CustomDate(2016, 11, 12));
            flight.FlightReservation flight1 = flights[0];
            Booking flightBooking1 = new Booking();

            flightBooking1.setBookingNumber(flight1.getBookingNumber());
            flightBooking1.setBookingStatus(BookingStatus.UNCONFIRMMED);
            flightBooking1.setBookingType(BookingType.FLIGHT);
            flightBooking1.setDate(new Date(flight1.getFlight().getStart()));
            flightBooking1.setPrice(flight1.getPrice());
            flightBooking1.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, flightBooking1);

            hotels = travelAgencyInterface.getHotels("Copenhagen", new CustomDate(2016, 11, 7), new CustomDate(2016, 11, 7));
            hotel.Hotel hotel2 = hotels[1];
            Booking hotelBooking2 = new Booking();
            hotelBooking2.setBookingNumber(hotel2.getBookingNumber());
            hotelBooking2.setBookingStatus(BookingStatus.UNCONFIRMMED);
            hotelBooking2.setBookingType(BookingType.HOTEL);
            hotelBooking2.setPrice(hotel2.getPrice());
            hotelBooking2.setDate(new Date(hotel2.getOpens()));
            hotelBooking2.setItinerary(itinerary);
            travelAgencyInterface.createBooking(itineraryId, hotelBooking2);

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
                Booking b = iterator2.next();
                Assert.assertEquals(b.getBookingStatus(), BookingStatus.CONFIRMED);
            }

            String correctNumber = creditCard.getNumber();
            creditCard.setNumber("stalin");
            travelAgencyInterface.cancelItinerarie(itineraryId, creditCard);
            creditCard.setNumber(correctNumber);

        } catch (Exception e) {
            // e.printStackTrace();
        } finally {

            Itinerary itinerary = null;
            try {
                itinerary = travelAgencyInterface.getItinerary(itineraryId);
            } catch (ItineraryDoesNotExistException e) {
                e.printStackTrace();
            }

            List<Booking> bookings = (List<Booking>) itinerary.getBookings();

            assertEquals(bookings.get(0).getBookingStatus(), BookingStatus.CANCELLED);
            assertEquals(bookings.get(1).getBookingStatus(), BookingStatus.CONFIRMED);
            assertEquals(bookings.get(2).getBookingStatus(), BookingStatus.CANCELLED);
        }
    }
}
