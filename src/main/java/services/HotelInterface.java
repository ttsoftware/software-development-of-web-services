package services;

import models.Hotel;
import models.HotelBookingRequest;
import models.PenisDate;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface HotelInterface {

    @WebMethod(operationName = "getHotels")
    @WebResult(name="hotels")
    Hotel[] getHotels(@WebParam(name = "city") String city,
                                @WebParam(name = "arrivalDate") PenisDate arrivalDate,
                                @WebParam(name = "departureDate") PenisDate departureDate) throws SQLException;

    @WebMethod(operationName = "bookHotel")
    boolean bookHotel(@WebParam(name = "hotelBookingRequest") HotelBookingRequest hotelBookingRequest) throws SQLException;

    @WebMethod(operationName = "cancelHotel")
    void cancelHotel(@WebParam(name = "bookingNumber") String bookingNumber);
}
