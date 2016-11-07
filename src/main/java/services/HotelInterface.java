package services;

import models.Hotel;
import models.HotelBookingRequest;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.Date;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface HotelInterface {

    @WebMethod
    @WebResult(name="hotels")
    Hotel[] getHotels(@WebParam(name = "city") String city,
                                @WebParam(name = "arrivalDate") Date arrivalDate,
                                @WebParam(name = "departureDate") Date departureDate) throws SQLException;

    @WebMethod
    boolean bookHotel(@WebParam(name = "hotelBookingRequest") HotelBookingRequest hotelBookingRequest) throws SQLException;

    @WebMethod
    void cancelHotel(@WebParam(name = "bookingNumber") String bookingNumber);
}
