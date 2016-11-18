package services;

import bank.CreditCardInfoType;
import models.BookingType;
import models.FlightReservation;
import models.Hotel;
import models.Itinerary;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by troels on 11/15/16.
 */
@WebService(
        name = "TravelAgencyService",
        serviceName = "TravelAgencyService",
        endpointInterface = "services.TravelAgencyInterface                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      "
)
public class TravelAgencyService implements TravelAgencyInterface {

    @Override
    public FlightReservation[] getFlights(String from, String destination, Date date) {

        return new FlightReservation[0];
    }

    @Override
    public Hotel[] getHotels(String city, Date arrivalDate, Date departureDate) throws SQLException {
        return new Hotel[0];
    }

    @Override
    public int createItinerarie() throws Exception {
        try {
            return DatabaseService.getDao(Itinerary.class).create(new Itinerary());
        } catch (SQLException e) {
            //Throw server error
            e.printStackTrace();
        }
        throw new Exception("Could not create itinerarie");
    }

    @Override
    public Itinerary getItinerarie(int id) throws Exception {
        try {
            List<Itinerary> Itineraries = DatabaseService.getDao(Itinerary.class).queryForEq("id", id);
            if(Itineraries.size() == 0) throw new Exception();
            return Itineraries.get(0);
        } catch (SQLException e) {
            //Throw server error
            e.printStackTrace();
        }
        throw new Exception();
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
