package services;

import bank.CreditCardInfoType;
import models.BookingType;
import models.FlightReservation;
import models.Hotel;
import models.Itinerary;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;

@WebService(
        name = "TravelAgencyService",
        serviceName = "TravelAgencyService",
        endpointInterface = "services.TravelAgencyInterface                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      "
)
public class TravelAgencyService implements TravelAgencyInterface {

    @Override
    public FlightReservation[] getFlights(String from, String destination, Date date) {
        URL FlightServiceUrl = null;
        try {
            FlightServiceUrl = new URL("http://localhost:8080/FlightService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        flight.AirlineService bs = new flight.AirlineService(FlightServiceUrl);
        flight.AirlineInterface port = bs.getAirlineServicePort();
        return port.getFlights(from, destination, date);
    }

    @Override
    public Hotel[] getHotels(String city, Date arrivalDate, Date departureDate) throws SQLException {
        URL hotelServiceUrl = null;
        try {
            hotelServiceUrl = new URL("http://localhost:8080/HotelService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        hotel.HotelService bs = new hotel.HotelService(hotelServiceUrl);
        hotel.HotelInterface port = bs.getHotelServicePort();
        return port.getHotels(city, arrivalDate, departureDate);
    }

    @Override
    public int createItinerarie() throws Exception {
        ItineraryService itineraryService  = new ItineraryService();
        int id = itineraryService.createItinerary();
        if(id == -1) throw new Exception();
        return id;
    }

    @Override
    public Itinerary getItinerarie(int id) throws Exception {
        return null;
    }

    @Override
    public boolean cancelItinerarie(int id) {
        return false;
    }

    @Override
    public boolean bookItinerarie(int id) {
        return false;
    }

    @Override
    public boolean createBooking(String itinerarieId, String bookingNumber, CreditCardInfoType cardInformation, BookingType bookingType) {
        return false;
    }

    @Override
    public boolean cancelBooking(String itinerarieId, String bookingNumber, CreditCardInfoType cardInformation, BookingType bookingType) {
        return false;
    }
}
