package services;

import bank.*;
import models.FlightReservation;
import models.Hotel;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by troels on 10/31/16.
 */
@WebService(
        name = "AirlineService",
        serviceName = "AirlineService",
        endpointInterface = "services.AirlineInterface"
)
public class AirlineService implements AirlineInterface {

    private HashMap<String, FlightReservation> customerBookings;
    private List<FlightReservation> availableFlights;

    @Override
    public FlightReservation[] getFlights(String from, String destination, String date) {
        try {
            List<Hotel> hotels = DatabaseService.getDao(Hotel.class).queryForEq("city", "hej");
            /*
            QueryBuilder<FlightReservation, ?> flightResQB = DatabaseService.getDao(FlightReservation.class).queryBuilder();
            QueryBuilder<Flight, ?> flightQB = DatabaseService.getDao(Flight.class).queryBuilder();
            flightQB.where().eq("startAirport", from).and().eq("destinationAripor", destination).and().eq("startDate", date);

            List<FlightReservation> results = flightResQB.join(flightQB).query();
            FlightReservation[] resultArray = (FlightReservation[]) results.toArray();
            */
            return new FlightReservation[0];
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new FlightReservation[0];
    }

    @Override
    public boolean bookFlight(String bookingNumber, CreditCardInfoType cardInformation) throws CreditCardFaultMessage, BookingNumberException {
        FlightReservation flightReservation = null;
        try {
            flightReservation = DatabaseService.getDao(FlightReservation.class).queryForEq("bookingNumber", bookingNumber).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(flightReservation == null) throw new BookingNumberException("Booking number does not exists");

        URL bankServiceUrl = null;
        try {
            bankServiceUrl = new URL("http://fastmoney.imm.dtu.dk:8080/BankService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BankService bs = new BankService(bankServiceUrl);
        BankPortType port = bs.getBankPort();

        AccountType flightAccount = new AccountType();
        flightAccount.setName("TravelGood");
        flightAccount.setNumber("50208813");;

        port.chargeCreditCard(1337, cardInformation, flightReservation.getPrice(), flightAccount);
        customerBookings.put(bookingNumber, flightReservation);

        return true;
    }

    @Override
    public boolean cancelFlight(String bookingNumber, float price, CreditCardInfoType cardInformation) throws BookingNumberException, CreditCardFaultMessage {
        FlightReservation flightReservation = null;
        try {
            flightReservation = DatabaseService.getDao(FlightReservation.class).queryForEq("bookingNumber", bookingNumber).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(flightReservation == null) throw new BookingNumberException("Booking number does not exists");

        float flightPrice = flightReservation.getPrice();


        AccountType flightAccount = new AccountType();
        flightAccount.setName("TravelGood");
        flightAccount.setNumber("50208813");;

        URL bankServiceUrl = null;
        try {
            bankServiceUrl = new URL("http://fastmoney.imm.dtu.dk:8080/BankService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BankService bs = new BankService(bankServiceUrl);
        BankPortType port = bs.getBankPort();

        int returnMoney = (int) Math.floor(flightPrice/2);
        port.refundCreditCard(1337, cardInformation, returnMoney, flightAccount);

        return true;
    }

    public class BookingNumberException extends Exception {
        public BookingNumberException(String message) {
            super(message);
        }
    }
}
