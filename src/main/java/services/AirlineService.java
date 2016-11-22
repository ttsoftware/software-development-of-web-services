package services;

import bank.*;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import models.Flight;
import models.FlightReservation;
import models.CustomDate;
import services.exceptions.BookingNumberException;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@WebService(
        name = "AirlineService",
        serviceName = "AirlineService",
        endpointInterface = "services.AirlineInterface"
)
public class AirlineService implements AirlineInterface {

    private List<FlightReservation> availableFlights;

    @Override
    public FlightReservation[] getFlights(String from, String destination, CustomDate customDate) {
        try {

            QueryBuilder<Flight, ?> qbFlight = DatabaseService.getDao(Flight.class).queryBuilder();

            Date date = customDate.toDate();

            qbFlight.where()
                    .eq("startAirport", from)
                    .and().eq("destinationAirport", destination)
                    .and().eq("start", date.getTime());

            QueryBuilder<FlightReservation, ?> qbFlightRes = DatabaseService.getDao(FlightReservation.class).queryBuilder();

            qbFlightRes.leftJoin(qbFlight);

            PreparedQuery<FlightReservation> preparedFlightRes = qbFlightRes.prepare();

            List<FlightReservation> results = DatabaseService.getDao(FlightReservation.class).query(preparedFlightRes);

            if(results.size() == 0) return new FlightReservation[0];
            //Keep it simple and just take the first

            Iterator<FlightReservation> itr = results.iterator();
            FlightReservation[] resultArray = new FlightReservation[results.size()];
            for (int i = 0; i < results.size(); i++) {
                resultArray[i] = results.get(i);
            }

            return resultArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new FlightReservation[0];
    }

    @Override
    public boolean bookFlight(String bookingNumber, CreditCardInfoType cardInformation) throws CreditCardFaultMessage, BookingNumberException {
        FlightReservation flightReservation = null;
        List<FlightReservation>  flightReservations = null;
        try {
            flightReservations = DatabaseService.getDao(FlightReservation.class).queryForEq("bookingNumber", bookingNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(flightReservations.size() == 0) throw new BookingNumberException("Booking number does not exists");
        flightReservation = flightReservations.get(0);

        URL bankServiceUrl = null;
        try {
            bankServiceUrl = new URL("http://fastmoney.imm.dtu.dk:8080/BankService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BankService bs = new BankService(bankServiceUrl);
        BankPortType port = bs.getBankPort();

        AccountType flightAccount = new AccountType();
        flightAccount.setName("LameDuck");
        flightAccount.setNumber("50208812");

        port.chargeCreditCard(22, cardInformation, flightReservation.getPrice(), flightAccount);

        return true;
    }

    @Override
    public boolean cancelFlight(String bookingNumber, float price, CreditCardInfoType cardInformation) throws CreditCardFaultMessage, BookingNumberException {
        FlightReservation flightReservation = null;
        List<FlightReservation>  flightReservations = null;
        try {
            flightReservations = DatabaseService.getDao(FlightReservation.class).queryForEq("bookingNumber", bookingNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(flightReservations.size() == 0) throw new BookingNumberException("Booking number does not exists");
        flightReservation = flightReservations.get(0);

        float flightPrice = flightReservation.getPrice();


        AccountType flightAccount = new AccountType();
        flightAccount.setName("LameDuck");
        flightAccount.setNumber("50208812");

        URL bankServiceUrl = null;
        try {
            bankServiceUrl = new URL("http://fastmoney.imm.dtu.dk:8080/BankService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BankService bs = new BankService(bankServiceUrl);
        BankPortType port = bs.getBankPort();

        int returnMoney = (int) Math.floor(flightPrice/2);
        port.refundCreditCard(22, cardInformation, returnMoney, flightAccount);

        return true;
    }
}
