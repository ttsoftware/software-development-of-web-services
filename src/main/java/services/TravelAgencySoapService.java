package services;

import bank.CreditCardInfoType;
import models.Booking;
import models.CustomDate;
import models.Itinerary;
import services.exceptions.BookingFaultException;
import services.exceptions.CancleBookingException;
import services.exceptions.ItineraryDoesNotExistException;

import javax.jws.WebService;


@WebService(
        name = "TravelAgencySoapService",
        serviceName = "TravelAgencySoapService",
        endpointInterface = "services.TravelAgencySoapInterface"
)
public class TravelAgencySoapService implements TravelAgencySoapInterface {

    private TravelAgencyService travelAgencyService = new TravelAgencyService();

    @Override
    public flight.FlightReservation[] getFlights(int id, String from, String destination, CustomDate date) {
        return travelAgencyService.getFlights(from, destination, date);
    }

    @Override
    public hotel.Hotel[] getHotels(int id, String city, CustomDate arrivalDate, CustomDate departureDate) {
        return travelAgencyService.getHotels(city, arrivalDate, departureDate);
    }

    @Override
    public int createItinerary() throws Exception {
        return travelAgencyService.createItinerary();
    }

    @Override
    public Itinerary getItinerary(int id) throws ItineraryDoesNotExistException {
        return travelAgencyService.getItinerary(id);
    }

    @Override
    public Itinerary[] getItineraries() {
        return travelAgencyService.getItineraries();
    }

    @Override
    public boolean cancelItinerarie(int id, CreditCardInfoType cardInformation) throws CancleBookingException {
        return travelAgencyService.cancelItinerarie(id, cardInformation);
    }

    @Override
    public boolean bookItinerarie(int id, CreditCardInfoType cardInformation) throws BookingFaultException {
        return travelAgencyService.bookItinerarie(id, cardInformation);
    }

    @Override
    public boolean createBooking(int itinerarieId, Booking booking) {
        return travelAgencyService.createBooking(itinerarieId, booking);
    }
}
