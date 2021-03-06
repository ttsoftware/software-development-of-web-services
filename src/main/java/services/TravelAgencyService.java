package services;

import bank.CreditCardInfoType;
import flight.BookingNumberException_Exception;
import flight.CreditCardFaultMessage;
import models.*;
import services.exceptions.BookingFaultException;
import services.exceptions.CancleBookingException;
import services.exceptions.ItineraryDoesNotExistException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TravelAgencyService {

    // Dennis Olesen - s155996
    public flight.FlightReservation[] getFlights(String from, String destination, CustomDate date) {
        flight.AirlineInterface port = getFlightServicePort();
        flight.CustomDate customDate = new flight.CustomDate();
        customDate.setDay(date.getDay());
        customDate.setMonth(date.getMonth());
        customDate.setYear(date.getYear());
        List<flight.FlightReservation> flights = port.getFlights(from, destination, customDate).getItem();

        flight.FlightReservation[] flightsArray = new flight.FlightReservation[flights.size()];
        for (int i = 0; i < flights.size(); i++) {
            flightsArray[i] = flights.get(i);
        }

        return flightsArray;
    }

    // Troels Thomsen - s152165
    public hotel.Hotel[] getHotels(String city, CustomDate arrivalDate, CustomDate departureDate) {
        hotel.HotelInterface port = getHotelServicePort();
        hotel.CustomDate customArrivalDate = new hotel.CustomDate();
        customArrivalDate.setDay(arrivalDate.getDay());
        customArrivalDate.setMonth(arrivalDate.getMonth());
        customArrivalDate.setYear(arrivalDate.getYear());

        hotel.CustomDate customDepartureDate = new hotel.CustomDate();
        customDepartureDate.setDay(departureDate.getDay());
        customDepartureDate.setMonth(departureDate.getMonth());
        customDepartureDate.setYear(departureDate.getYear());

        List<hotel.Hotel> hotels = port.getHotels(city, customArrivalDate, customDepartureDate).getItem();
        hotel.Hotel[] hotelArray = new hotel.Hotel[hotels.size()];
        for (int i = 0; i < hotels.size(); i++) {
            hotelArray[i] = hotels.get(i);
        }
        return hotelArray;
    }

    // Troels Hessner Hansen - s123136
    public Itinerary getItinerary(int id) throws ItineraryDoesNotExistException {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);
        if (itinerary == null) throw new ItineraryDoesNotExistException("Initirary with id " + id + " does not exist");
        return itinerary;
    }

    // Allan Nielsen - s162874
    public int createItinerary() {
        ItineraryService itineraryService = new ItineraryService();
        int id = itineraryService.createItinerary();
        if (id == -1) throw new RuntimeException("Server error could not create new itinerary");
        return id;
    }

    // Dennis Olesen - s155996
    public Itinerary[] getItineraries() {
        ItineraryService itineraryService = new ItineraryService();
        List<Itinerary> itineraries = itineraryService.getItineraries();
        Itinerary[] itinerariesArray = new Itinerary[itineraries.size()];
        for (int i = 0; i < itineraries.size(); i++) {
            itinerariesArray[i] = itineraries.get(i);
        }

        return itinerariesArray;
    }

    // Troels Thomsen - s152165
    public boolean cancelItinerarie(int id, CreditCardInfoType cardInformation) throws CancleBookingException {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);
        if (itinerary == null) return false;
        Collection<Booking> bookings = itinerary.getBookings();
        boolean faultHappend = false;

        Iterator<Booking> iteratorForDate = bookings.iterator();
        Date now = new Date();
        while (iteratorForDate.hasNext()) {
            Booking b = iteratorForDate.next();
            if (b.getDate().getTime() >= now.getTime()) {
                throw new CancleBookingException("First booking started");
            }
        }

        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();

            if (booking.getBookingType().equals(BookingType.FLIGHT)) {
                try {
                    cancelFlight(booking, cardInformation);
                } catch (CancleBookingException e) {
                    faultHappend = true;
                }
            }

            if (booking.getBookingType().equals(BookingType.HOTEL)) {
                try {
                    cancelHotel(booking, cardInformation);
                } catch (CancleBookingException e) {
                    faultHappend = true;
                }
            }
            itineraryService.updateBooking(id, booking);
        }

        if (faultHappend) throw new CancleBookingException("One or more bookings failed");
        return true;
    }

    // Troels Hessner Hansen - s123136
    public boolean createBooking(int itinerarieId, Booking booking) {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(itinerarieId);
        if (itinerary == null) return false;
        booking.setBookingStatus(BookingStatus.UNCONFIRMMED);
        itineraryService.addBookingToItinerary(itinerarieId, booking);
        return true;
    }

    // Allan Nielsen - s162874
    public boolean bookItinerarie(int id, CreditCardInfoType cardInformation) throws BookingFaultException {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);

        boolean faultHappen = false;

        if (itinerary == null) return false;

        Collection<Booking> bookings = itinerary.getBookings();
        Iterator<Booking> iterator = bookings.iterator();
        try {
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
        } catch (BookingFaultException e) {
            faultHappen = true;
        }finally {
            if (faultHappen) {
                Itinerary itineraryFailed = itineraryService.getItinerary(id);
                Iterator<Booking> ite = bookings.iterator();
                while (ite.hasNext()) {
                    Booking booking = ite.next();
                    System.out.println(booking.getBookingNumber());
                    System.out.println(booking.getBookingStatus());
                    if (booking.getBookingStatus().equals(BookingStatus.CONFIRMED)) {
                        if (booking.getBookingType().equals(BookingType.FLIGHT)) {
                            try {
                                cancelFlight(booking, cardInformation);
                            } catch (CancleBookingException e) {

                            }
                        }

                        if (booking.getBookingType().equals(BookingType.HOTEL)) {
                            try {
                                cancelHotel(booking, cardInformation);
                            } catch (CancleBookingException e) {

                            }
                        }
                        itineraryService.updateBooking(id, booking);
                    }

                }
                throw new BookingFaultException("Booking failed");
            }
        }
        return true;
    }
    // Dennis Olesen - s155996
    public void cancelHotel(Booking booking, CreditCardInfoType cardInformation) throws CancleBookingException {
        hotel.HotelInterface hotelPort = getHotelServicePort();
        hotel.CreditCardInfoType ccit = mapCreditCardHotel(cardInformation);
        try {
            hotelPort.cancelHotel(booking.getBookingNumber());
            booking.setBookingStatus(BookingStatus.CANCELLED);
        } catch (hotel.BookingNumberException_Exception e) {
            throw new CancleBookingException("Booking number does not exists");
        } catch (hotel.CreditCardFaultMessage creditCardFaultMessage) {
            throw new CancleBookingException("Credit card not valid");
        }
    }

    // Troels Thomsen - s152165
    public void cancelFlight(Booking booking, CreditCardInfoType cardInformation) throws CancleBookingException {
        flight.AirlineInterface flightPort = getFlightServicePort();
        flight.CreditCardInfoType ccit = mapCreditCardFlight(cardInformation);
        try {
            boolean success = flightPort.cancelFlight(booking.getBookingNumber(), booking.getPrice(), ccit);
            if (success) {
                booking.setBookingStatus(BookingStatus.CANCELLED);
            } else {
                throw new CancleBookingException("");
            }
        } catch (BookingNumberException_Exception e) {
            throw new CancleBookingException("Booking number does not exists");
        } catch (CreditCardFaultMessage creditCardFaultMessage) {
            throw new CancleBookingException("Credit card not valid");
        }
    }

    // Troels Hessner Hansen - s123136
    public void bookflight(Booking booking, CreditCardInfoType cardInformation) throws BookingFaultException {
        flight.AirlineInterface flightPort = getFlightServicePort();

        flight.CreditCardInfoType ccit = mapCreditCardFlight(cardInformation);
        try {
            boolean success = flightPort.bookFlight(booking.getBookingNumber(), ccit);
            if (success) {
                booking.setBookingStatus(BookingStatus.CONFIRMED);
            } else {
                booking.setBookingStatus(BookingStatus.UNCONFIRMMED);
                throw new BookingFaultException("Booking failed");
            }
        } catch (BookingNumberException_Exception e) {
            booking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            throw new BookingFaultException("Booking failed");
        } catch (CreditCardFaultMessage creditCardFaultMessage) {
            booking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            throw new BookingFaultException("Booking failed");
        }
    }
    // Allan Nielsen - s162874
    public void bookHotel(Booking booking, CreditCardInfoType cardInformation) throws BookingFaultException {
        hotel.HotelInterface hotelPort = getHotelServicePort();

        hotel.CreditCardInfoType ccit = mapCreditCardHotel(cardInformation);
        hotel.HotelBookingRequest request = new hotel.HotelBookingRequest();
        request.setBookingNumber(booking.getBookingNumber());
        request.setCreditCardInformation(ccit);

        boolean success = false;
        try {
            success = hotelPort.bookHotel(request);
            if (success) {
                booking.setBookingStatus(BookingStatus.CONFIRMED);
            } else {
                booking.setBookingStatus(BookingStatus.UNCONFIRMMED);
                throw new BookingFaultException("Booking failed");
            }
        } catch (hotel.CreditCardFaultMessage creditCardFaultMessage) {
            booking.setBookingStatus(BookingStatus.UNCONFIRMMED);
            throw new BookingFaultException("Booking failed");
        }
    }


    // Dennis Olesen - s155996
    //-----------------------------------------PORT FUNC---------------------------------------------------
    public hotel.HotelInterface getHotelServicePort() {
        URL hotelServiceUrl = null;
        try {
            hotelServiceUrl = new URL("http://localhost:8282/webservices/HotelService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        hotel.HotelService bs = new hotel.HotelService(hotelServiceUrl);
        return bs.getHotelServicePort();
    }

    // Troels Thomsen - s152165
    public flight.AirlineInterface getFlightServicePort() {
        URL FlightServiceUrl = null;
        try {
            FlightServiceUrl = new URL("http://localhost:8282/webservices/AirlineService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        flight.AirlineService bs = new flight.AirlineService(FlightServiceUrl);
        return bs.getAirlineServicePort();
    }

    // Troels Hessner Hansen - s123136
    //-----------------------------------------MAPPING FUNC---------------------------------------------------
    public flight.CreditCardInfoType mapCreditCardFlight(bank.CreditCardInfoType credit) {
        flight.CreditCardInfoType ccit = new flight.CreditCardInfoType();
        flight.CreditCardInfoType.ExpirationDate date = new flight.CreditCardInfoType.ExpirationDate();
        date.setYear(credit.getExpirationDate().getYear());
        date.setMonth(credit.getExpirationDate().getMonth());
        ccit.setExpirationDate(date);
        ccit.setName(credit.getName());
        ccit.setNumber(credit.getNumber());
        return ccit;
    }

    // Allan Nielsen - s162874
    public hotel.CreditCardInfoType mapCreditCardHotel(bank.CreditCardInfoType credit) {
        hotel.CreditCardInfoType ccit = new hotel.CreditCardInfoType();
        ccit.setExpirationYear(credit.getExpirationDate().getYear());
        ccit.setExpirationMonth(credit.getExpirationDate().getMonth());
        ccit.setName(credit.getName());
        ccit.setNumber(credit.getNumber());
        return ccit;
    }
}
