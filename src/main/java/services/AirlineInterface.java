package services;

import bank.CreditCardFaultMessage;
import bank.CreditCardInfoType;
import models.FlightReservation;
import models.PenisDate;
import services.exceptions.BookingNumberException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface AirlineInterface {

    @WebMethod
    @WebResult(name = "flightRevervations")
    FlightReservation[] getFlights(@WebParam(name = "from") String from,
                                   @WebParam(name = "destination") String destination,
                                   @WebParam(name = "date") PenisDate date);


    @WebMethod
    boolean bookFlight(@WebParam(name = "bookingNumber") String bookingNumber,
                       @WebParam(name = "cardInformation") CreditCardInfoType cardInformation) throws CreditCardFaultMessage, BookingNumberException;

    @WebMethod
    boolean cancelFlight(@WebParam(name = "bookingNumber") String bookingNumber,
                         @WebParam(name = "price") float price,
                         @WebParam(name = "cardInformation") CreditCardInfoType cardInformation) throws CreditCardFaultMessage, BookingNumberException;
}
