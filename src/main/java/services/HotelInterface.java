package services;

import models.HotelBookingRequest;
import models.HotelInformation;

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
public interface HotelInterface {

    @WebMethod
    Collection<HotelInformation> getHotels(@WebParam(name = "city") String city,
                                           @WebParam(name = "arrivalDate") Date arrivalDate,
                                           @WebParam(name = "departureDate") Date departureDate);

    @WebMethod
    boolean bookHotel(@WebParam(name = "hotelBookingRequest") HotelBookingRequest hotelBookingRequest);

    @WebMethod
    void cancelHotel(@WebParam(name = "bookingNumber") String bookingNumber);
}
