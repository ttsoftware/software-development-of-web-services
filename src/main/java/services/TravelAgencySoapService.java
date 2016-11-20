package services;

import bank.CreditCardInfoType;
import models.*;

import javax.jws.WebService;
import java.util.Collection;
import java.util.Iterator;


@WebService(
        name = "TravelAgencySoapService",
        serviceName = "TravelAgencySoapService",
        endpointInterface = "services.TravelAgencySoapInterface"
)
public class TravelAgencySoapService implements TravelAgencySoapInterface {

    private TravelAgencyService travelAgencyService = new TravelAgencyService();

    @Override
    public FlightReservation[] getFlights(String from, String destination, CustomDate date) {
        return travelAgencyService.getFlights(from, destination, date);
    }

    @Override
    public Hotel[] getHotels(String city, CustomDate arrivalDate, CustomDate departureDate) {
        return travelAgencyService.getHotels(city, arrivalDate, departureDate);
    }

    @Override
    public int createItinerarie() throws Exception {
        ItineraryService itineraryService = new ItineraryService();
        int id = itineraryService.createItinerary();
        if (id == -1) throw new Exception();
        return id;
    }

    @Override
    public Itinerary getItinerarie(int id) throws Exception {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);
        if (itinerary == null) throw new Exception();
        return itinerary;
    }

    @Override
    public boolean cancelItinerarie(int id, CreditCardInfoType cardInformation) throws Exception {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);
        if (itinerary == null) return false;
        Collection<Booking> bookings = itinerary.getBookings();
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();

            if (booking.getBookingType().equals(BookingType.FLIGHT)) {
                this.travelAgencyService.cancelFlight(booking, cardInformation);
            }

            if (booking.getBookingType().equals(BookingType.HOTEL)) {
                this.travelAgencyService.cancelHotel(booking, cardInformation);
            }
            itineraryService.updateBooking(id, booking);
        }
        return true;
    }

    @Override
    public boolean bookItinerarie(int id, CreditCardInfoType cardInformation) {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(id);
        if (itinerary == null) return false;

        Collection<Booking> bookings = itinerary.getBookings();
        Iterator<Booking> iterator = bookings.iterator();

        while (iterator.hasNext()) {
            Booking booking = iterator.next();

            if (booking.getBookingType().equals(BookingType.FLIGHT)) {
                this.travelAgencyService.bookflight(booking, cardInformation);
            }

            if (booking.getBookingType().equals(BookingType.HOTEL)) {
                this.travelAgencyService.bookHotel(booking, cardInformation);
            }
            itineraryService.updateBooking(id, booking);
        }
        return true;
    }

    @Override
    public boolean createBooking(int itinerarieId, Booking booking) {
        ItineraryService itineraryService = new ItineraryService();
        Itinerary itinerary = itineraryService.getItinerary(itinerarieId);
        if (itinerary == null) return false;
        booking.setBookingStatus(BookingStatus.UNCONFIRMMED);
        itineraryService.addBookingToItinerary(itinerarieId, booking);
        return true;
    }
}
