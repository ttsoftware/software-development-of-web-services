package services;

import bank.CreditCardInfoType;
import flight.BookingNumberException_Exception;
import flight.CreditCardFaultMessage;
import hotel.SQLException_Exception;
import models.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TravelAgencyService {

    public models.FlightReservation[] getFlights(String from, String destination, CustomDate date) {
        flight.AirlineInterface port = getFlightServicePort();
        flight.PenisDate customDate = new flight.PenisDate();
        customDate.setDay(date.getDay());
        customDate.setMonth(date.getMonth());
        customDate.setYear(date.getYear());
        FlightReservation[] flights = (FlightReservation[]) port.getFlights(from, destination, customDate).getItem().toArray();
        return flights;
    }

    public Itinerary getItinerary(int id) throws Exception {
        ItineraryService itineraryService  = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);
        if (itinerary == null) throw new Exception();
        return itinerary;
    }

    public int createItinerary() throws Exception {
        ItineraryService itineraryService = new ItineraryService();
        int id = itineraryService.createItinerary();
        if (id == -1) throw new Exception();
        return id;
    }

    public Itinerary[] getItineraries() {
        ItineraryService itineraryService  = new ItineraryService();
        List<Itinerary> itineraries = itineraryService.getItineraries();
        Itinerary[] itinerariesArray = new Itinerary[itineraries.size()];
        for (int i = 0; i < itineraries.size(); i++) {
            itinerariesArray[i] = itineraries.get(i);
        }

        return itinerariesArray;
    }

    public boolean cancelItinerarie(int id, CreditCardInfoType cardInformation) throws Exception {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);
        if (itinerary == null) return false;
        Collection<Booking> bookings = itinerary.getBookings();
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();

            if (booking.getBookingType().equals(BookingType.FLIGHT)) {
                cancelFlight(booking, cardInformation);
            }

            if (booking.getBookingType().equals(BookingType.HOTEL)) {
                cancelHotel(booking, cardInformation);
            }
            itineraryService.updateBooking(id, booking);
        }
        return true;
    }

    public boolean createBooking(int itinerarieId, Booking booking) {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(itinerarieId);
        if (itinerary == null) return false;
        booking.setBookingStatus(BookingStatus.UNCONFIRMMED);
        itineraryService.addBookingToItinerary(itinerarieId, booking);
        return true;
    }

    public boolean bookItinerarie(int id, CreditCardInfoType cardInformation) {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);
        if (itinerary == null) return false;

        Collection<Booking> bookings = itinerary.getBookings();
        Iterator<Booking> iterator = bookings.iterator();

        while (iterator.hasNext()) {
            Booking booking = iterator.next();

            if (booking.getBookingType().equals(BookingType.FLIGHT)) {
                bookflight(booking, cardInformation);
            }

            if (booking.getBookingType().equals(BookingType.HOTEL)) {
                bookHotel(booking, cardInformation);
            }
            itineraryService.updateBooking(id, booking);
        }
        return true;
    }

    public Hotel[] getHotels(String city, CustomDate arrivalDate, CustomDate departureDate) {
        hotel.HotelInterface port = getHotelServicePort();
        hotel.PenisDate customArrivalDate = new hotel.PenisDate();
        customArrivalDate.setDay(arrivalDate.getDay());
        customArrivalDate.setMonth(arrivalDate.getMonth());
        customArrivalDate.setYear(arrivalDate.getYear());

        hotel.PenisDate customDepartureDate = new hotel.PenisDate();
        customDepartureDate.setDay(departureDate.getDay());
        customDepartureDate.setMonth(departureDate.getMonth());
        customDepartureDate.setYear(departureDate.getYear());
        try {
            Hotel[] hotels = (Hotel[]) port.getHotels(city, customArrivalDate, customDepartureDate).getItem().toArray();
            return hotels;
        } catch (SQLException_Exception e) {
            e.printStackTrace();
        }
        return new Hotel[]{};
    }

    public void cancelHotel(Booking booking, CreditCardInfoType cardInformation) {
        hotel.HotelInterface hotelPort = getHotelServicePort();
        hotel.CreditCardInfoType ccit = mapCreditCardHotel(cardInformation);
        try {
            hotelPort.cancelHotel(booking.getBookingNumber());
            booking.setBookingStatus(BookingStatus.CANCELLED);
        } catch (hotel.BookingNumberException_Exception e) {
            e.printStackTrace();
        } catch (hotel.CreditCardFaultMessage creditCardFaultMessage) {
            creditCardFaultMessage.printStackTrace();
        }
    }

    public void cancelFlight(Booking booking, CreditCardInfoType cardInformation) {
        flight.AirlineInterface flightPort = getFlightServicePort();
        flight.CreditCardInfoType ccit = mapCreditCardFlight(cardInformation);
        try {
            boolean success = flightPort.cancelFlight(booking.getBookingNumber(), booking.getPrice(), ccit);
            if (success) booking.setBookingStatus(BookingStatus.CANCELLED);
        } catch (BookingNumberException_Exception e) {
            e.printStackTrace();
        } catch (CreditCardFaultMessage creditCardFaultMessage) {
            creditCardFaultMessage.printStackTrace();
        }
    }

    public hotel.HotelInterface getHotelServicePort(){
        URL hotelServiceUrl = null;
        try {
            hotelServiceUrl = new URL("http://localhost:8080/webservices/HotelService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        hotel.HotelService bs = new hotel.HotelService(hotelServiceUrl);
        return bs.getHotelServicePort();
    }

    public flight.AirlineInterface getFlightServicePort(){
        URL FlightServiceUrl = null;
        try {
            FlightServiceUrl = new URL("http://localhost:8080/webservices/AirlineService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        flight.AirlineService bs = new flight.AirlineService(FlightServiceUrl);
        return bs.getAirlineServicePort();
    }

    public void bookflight(Booking booking, CreditCardInfoType cardInformation) {
        flight.AirlineInterface flightPort = getFlightServicePort();

        flight.CreditCardInfoType ccit = mapCreditCardFlight(cardInformation);
        try {
            boolean success = flightPort.bookFlight(booking.getBookingNumber(), ccit);
            if(success){
                booking.setBookingStatus(BookingStatus.CONFIRMED);
            }else{
                booking.setBookingStatus(BookingStatus.CANCELLED);
            }
        } catch (BookingNumberException_Exception e) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
        } catch (CreditCardFaultMessage creditCardFaultMessage) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
        }
    }

    public void bookHotel(Booking booking, CreditCardInfoType cardInformation) {
        hotel.HotelInterface hotelPort = getHotelServicePort();

        hotel.CreditCardInfoType ccit = mapCreditCardHotel(cardInformation);
        hotel.HotelBookingRequest request = new hotel.HotelBookingRequest();
        request.setBookingNumber(booking.getBookingNumber());
        request.setCreditCardInformation(ccit);

        boolean success = false;
        try {
            success = hotelPort.bookHotel(request);
            if(success){
                booking.setBookingStatus(BookingStatus.CONFIRMED);
            }else{
                booking.setBookingStatus(BookingStatus.CANCELLED);
            }
        } catch (SQLException_Exception e) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
        } catch (hotel.CreditCardFaultMessage creditCardFaultMessage) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
        }
    }

    public flight.CreditCardInfoType mapCreditCardFlight(bank.CreditCardInfoType credit){
        flight.CreditCardInfoType ccit = new flight.CreditCardInfoType();
        flight.CreditCardInfoType.ExpirationDate date = new flight.CreditCardInfoType.ExpirationDate();
        date.setYear(credit.getExpirationDate().getYear());
        date.setMonth(credit.getExpirationDate().getMonth());
        ccit.setExpirationDate(date);
        ccit.setName(credit.getName());
        ccit.setNumber(credit.getNumber());
        return ccit;
    }

    public hotel.CreditCardInfoType mapCreditCardHotel(bank.CreditCardInfoType credit){
        hotel.CreditCardInfoType ccit = new hotel.CreditCardInfoType();
        hotel.CreditCardInfoType.ExpirationDate date = new hotel.CreditCardInfoType.ExpirationDate();
        date.setYear(credit.getExpirationDate().getYear());
        date.setMonth(credit.getExpirationDate().getMonth());
        ccit.setExpirationDate(date);
        ccit.setName(credit.getName());
        ccit.setNumber(credit.getNumber());
        return ccit;
    }
}
