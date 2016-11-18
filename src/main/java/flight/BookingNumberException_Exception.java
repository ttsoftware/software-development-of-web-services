
package flight;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "BookingNumberException", targetNamespace = "http://services/")
public class BookingNumberException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private BookingNumberException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public BookingNumberException_Exception(String message, BookingNumberException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public BookingNumberException_Exception(String message, BookingNumberException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: flight.BookingNumberException
     */
    public BookingNumberException getFaultInfo() {
        return faultInfo;
    }

}
