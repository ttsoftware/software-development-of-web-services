package services.exceptions;

import javax.xml.ws.WebFault;

/**
 * Created by troels on 11/21/16.
 */

@WebFault(name = "BookingNumberException")
public class BookingFaultException extends Exception {
    public BookingFaultException(String message) {
        super(message);
    }
}
