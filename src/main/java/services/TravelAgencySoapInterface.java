package services;

import bank.CreditCardInfoType;
import models.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;

/**
 * Created by troels on 11/20/16.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface TravelAgencySoapInterface {
    @WebMethod(operationName = "getFlights")
    @WebResult(name="FlightReservation")
    FlightReservation[] getFlights(@WebParam(name = "from") String from,
                                   @WebParam(name = "destination") String destination,
                                   @WebParam(name = "date") PenisDate date);

    @WebMethod(operationName = "getHotels")
    @WebResult(name="hotels")
    Hotel[] getHotels(@WebParam(name = "city") String city,
                      @WebParam(name = "arrivalDate") PenisDate arrivalDate,
                      @WebParam(name = "departureDate") PenisDate departureDate) throws SQLException;
    @WebMethod(operationName = "createItinerarie")
    int createItinerarie() throws Exception;

    @WebMethod(operationName = "getItinerary")
    @WebResult(name="itinerary")
    Itinerary getItinerary(@WebParam(name = "id") int id) throws Exception;

    @WebMethod(operationName = "getItineraries")
    @WebResult(name="itineraries")
    Itinerary[] getItineraries() throws Exception;

    @WebMethod(operationName = "cancelItinerarie")
    boolean cancelItinerarie(@WebParam(name = "id") int id,
                             @WebParam(name = "cardInformation") CreditCardInfoType cardInformation) throws Exception;

    @WebMethod(operationName = "bookItinerarie")
    boolean bookItinerarie(@WebParam(name = "id") int id,
                           @WebParam(name = "cardInformation") CreditCardInfoType cardInformation);

    @WebMethod(operationName = "createBooking")
    boolean createBooking(@WebParam(name = "itinerarieId") int itinerarieId,
                          @WebParam(name = "booking") Booking booking);
}
