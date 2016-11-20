package services;

import bank.CreditCardInfoType;
import models.*;

import javax.jws.WebService;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


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
    public int createItinerary() throws Exception {
        return travelAgencyService.createItinerary();
    }

    @Override
    public Itinerary getItinerary(int id) throws Exception {
        return travelAgencyService.getItinerary(id);
    }

    @Override
    public Itinerary[] getItineraries() {
        return travelAgencyService.getItineraries();
    }

    @Override
    public boolean cancelItinerarie(int id, CreditCardInfoType cardInformation) throws Exception {
        return travelAgencyService.cancelItinerarie(id, cardInformation);
    }

    @Override
    public boolean bookItinerarie(int id, CreditCardInfoType cardInformation) {
        return travelAgencyService.bookItinerarie(id, cardInformation);
    }

    @Override
    public boolean createBooking(int itinerarieId, Booking booking) {
        return travelAgencyService.createBooking(itinerarieId, booking);
    }
}
