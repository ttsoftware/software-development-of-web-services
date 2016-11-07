package services;

import bank.*;
import models.FlightReservation;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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


    public FlightReservation getFlights(String from, String destination, Date date) {
        FlightReservation[] flightsFromQuery = (FlightReservation[]) availableFlights.stream()
               // .filter(fi -> fi.getFlight().getDestinationAripor().equals(destination) &&
                       // fi.getFlight().getStartAirport().equals(from) &&
                      //  fi.getFlight().getStart().equals(date))
                .collect(Collectors.toList()).toArray();
        return flightsFromQuery[0];
    }

    public boolean bookFlight(String bookingNumber, CreditCardInfoType cardInformation) throws CreditCardFaultMessage, BookingNumberException {
        FlightReservation flightReservation = availableFlights.stream().filter(fi -> fi.getBookingNumber().equals(bookingNumber))
                .findFirst()
                .get();

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

    public boolean cancelFlight(String bookingNumber, float price, CreditCardInfoType cardInformation) throws BookingNumberException, CreditCardFaultMessage {
        FlightReservation flightReservation = customerBookings.remove(bookingNumber);

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
