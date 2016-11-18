
package flight;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AirlineInterface", targetNamespace = "http://services/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AirlineInterface {


    /**
     * 
     * @param date
     * @param destination
     * @param from
     * @return
     *     returns flight.FlightReservationArray
     */
    @WebMethod
    @WebResult(name = "flightRevervations", partName = "flightRevervations")
    @Action(input = "http://services/AirlineInterface/getFlightsRequest", output = "http://services/AirlineInterface/getFlightsResponse")
    public FlightReservationArray getFlights(
        @WebParam(name = "from", partName = "from")
        String from,
        @WebParam(name = "destination", partName = "destination")
        String destination,
        @WebParam(name = "date", partName = "date")
        PenisDate date);

    /**
     * 
     * @param cardInformation
     * @param bookingNumber
     * @return
     *     returns boolean
     * @throws BookingNumberException_Exception
     * @throws CreditCardFaultMessage
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/AirlineInterface/bookFlightRequest", output = "http://services/AirlineInterface/bookFlightResponse", fault = {
        @FaultAction(className = CreditCardFaultMessage.class, value = "http://services/AirlineInterface/bookFlight/Fault/CreditCardFaultMessage"),
        @FaultAction(className = BookingNumberException_Exception.class, value = "http://services/AirlineInterface/bookFlight/Fault/BookingNumberException")
    })
    public boolean bookFlight(
        @WebParam(name = "bookingNumber", partName = "bookingNumber")
        String bookingNumber,
        @WebParam(name = "cardInformation", partName = "cardInformation")
        CreditCardInfoType cardInformation)
        throws BookingNumberException_Exception, CreditCardFaultMessage
    ;

    /**
     * 
     * @param price
     * @param cardInformation
     * @param bookingNumber
     * @return
     *     returns boolean
     * @throws BookingNumberException_Exception
     * @throws CreditCardFaultMessage
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://services/AirlineInterface/cancelFlightRequest", output = "http://services/AirlineInterface/cancelFlightResponse", fault = {
        @FaultAction(className = CreditCardFaultMessage.class, value = "http://services/AirlineInterface/cancelFlight/Fault/CreditCardFaultMessage"),
        @FaultAction(className = BookingNumberException_Exception.class, value = "http://services/AirlineInterface/cancelFlight/Fault/BookingNumberException")
    })
    public boolean cancelFlight(
        @WebParam(name = "bookingNumber", partName = "bookingNumber")
        String bookingNumber,
        @WebParam(name = "price", partName = "price")
        float price,
        @WebParam(name = "cardInformation", partName = "cardInformation")
        CreditCardInfoType cardInformation)
        throws BookingNumberException_Exception, CreditCardFaultMessage
    ;

}
