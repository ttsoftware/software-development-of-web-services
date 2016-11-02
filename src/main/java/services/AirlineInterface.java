package services;

import bank.CreditCardFaultMessage;
import bank.CreditCardInfoType;
import models.FlightInformation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.Date;
/**
 * Created by troels on 10/31/16.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface AirlineInterface {

    @WebMethod()
    Collection<FlightInformation> getFlights(@WebParam(name = "from") String from,
                                             @WebParam(name = "destination") String destination,
                                             @WebParam(name = "date")Date date);

    @WebMethod()
    boolean bookFlight(@WebParam(name = "bookingNumber") String bookingNumber,
                       @WebParam(name = "cardInformation") CreditCardInfoType cardInformation) throws CreditCardFaultMessage, AirlineService.BookingNumberException;

    @WebMethod()
    boolean cancelFlight(@WebParam(name = "bookingNumber") String bookingNumber,
                         @WebParam(name = "price") float price,
                         @WebParam(name = "cardInformation")CreditCardInfoType cardInformation) throws AirlineService.BookingNumberException, CreditCardFaultMessage;

}
