package services;

import bank.CreditCardFaultMessage;
import models.CustomDate;
import models.Hotel;
import models.HotelBookingRequest;
import services.exceptions.BookingNumberException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface HotelInterface {

    @WebMethod(operationName = "getHotels")
    @WebResult(name="hotels")
    Hotel[] getHotels(@WebParam(name = "city") String city,
                                @WebParam(name = "arrivalDate") CustomDate arrivalDate,
                                @WebParam(name = "departureDate") CustomDate departureDate);

    @WebMethod(operationName = "bookHotel")
    boolean bookHotel(@WebParam(name = "hotelBookingRequest") HotelBookingRequest hotelBookingRequest)throws CreditCardFaultMessage;

    @WebMethod(operationName = "cancelHotel")
    void cancelHotel(@WebParam(name = "bookingNumber") String bookingNumber) throws CreditCardFaultMessage, BookingNumberException;
}
