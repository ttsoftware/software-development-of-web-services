package services;

import bank.CreditCardInfoType;
import models.BookingType;
import models.FlightReservation;
import models.Hotel;
import models.Itinerary;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by troels on 11/14/16.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface TravelAgencyInterface {

    @WebMethod(operationName = "getFlights")
    @WebResult(name="FlightReservation")
    FlightReservation[] getFlights(@WebParam(name = "from") String from,
                                            @WebParam(name = "destination") String destination,
                                            @WebParam(name = "date")Date date);

    @WebMethod(operationName = "getHotels")
    @WebResult(name="hotels")
    Hotel[] getHotels(@WebParam(name = "city") String city,
                      @WebParam(name = "arrivalDate") Date arrivalDate,
                      @WebParam(name = "departureDate") Date departureDate) throws SQLException;

    @WebMethod(operationName = "createItinerarie")
    int createItinerarie() throws Exception;

    @WebMethod(operationName = "getItinerarie")
    @WebResult(name="itinerary")
    Itinerary getItinerarie(@WebParam(name = "id") int id) throws Exception;

    @WebMethod(operationName = "cancelItinerarie")
    boolean cancelItinerarie(@WebParam(name = "id") int id);

    @WebMethod(operationName = "bookItinerarie")
    boolean bookItinerarie(@WebParam(name = "id") int id);

    @WebMethod(operationName = "createBooking")
    boolean createBooking(@WebParam(name = "itinerarieId") String itinerarieId,
                          @WebParam(name = "bookingNumber") String bookingNumber,
                          @WebParam(name = "cardInformation") CreditCardInfoType cardInformation,
                          @WebParam(name = "bookingType") BookingType bookingType);

    @WebMethod(operationName = "cancelBooking")
    boolean cancelBooking(@WebParam(name = "itinerarieId") String itinerarieId,
                          @WebParam(name = "bookingNumber") String bookingNumber,
                          @WebParam(name = "cardInformation") CreditCardInfoType cardInformation,
                          @WebParam(name = "bookingType") BookingType bookingType);
}
