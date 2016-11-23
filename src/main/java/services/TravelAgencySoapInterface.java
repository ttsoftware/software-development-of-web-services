package services;

import bank.CreditCardInfoType;
import models.Booking;
import models.CustomDate;
import models.Itinerary;
import services.exceptions.BookingFaultException;
import services.exceptions.CancleBookingException;
import services.exceptions.ItineraryDoesNotExistException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by troels on 11/20/16.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface TravelAgencySoapInterface {
    @WebMethod(operationName = "getFlights")
    @WebResult(name="FlightReservation")
    flight.FlightReservation[] getFlights(
                                    @WebParam(name = "id") int id,
                                    @WebParam(name = "from") String from,
                                   @WebParam(name = "destination") String destination,
                                   @WebParam(name = "date") CustomDate date);

    @WebMethod(operationName = "getHotels")
    @WebResult(name="hotels")
    hotel.Hotel[] getHotels(@WebParam(name = "id") int id,
                        @WebParam(name = "city") String city,
                      @WebParam(name = "arrivalDate") CustomDate arrivalDate,
                      @WebParam(name = "departureDate") CustomDate departureDate);
    @WebMethod(operationName = "createItinerary")
    int createItinerary() throws Exception;

    @WebMethod(operationName = "getItinerary")
    @WebResult(name="itinerary")
    Itinerary getItinerary(@WebParam(name = "id") int id) throws ItineraryDoesNotExistException;

    @WebMethod(operationName = "getItineraries")
    @WebResult(name="itineraries")
    Itinerary[] getItineraries() throws Exception;

    @WebMethod(operationName = "cancelItinerarie")
    boolean cancelItinerarie(@WebParam(name = "id") int id,
                             @WebParam(name = "cardInformation") CreditCardInfoType cardInformation) throws CancleBookingException;

    @WebMethod(operationName = "bookItinerarie")
    boolean bookItinerarie(@WebParam(name = "id") int id,
                           @WebParam(name = "cardInformation") CreditCardInfoType cardInformation) throws BookingFaultException;

    @WebMethod(operationName = "createBooking")
    boolean createBooking(@WebParam(name = "itinerarieId") int itinerarieId,
                          @WebParam(name = "booking") Booking booking);
}
