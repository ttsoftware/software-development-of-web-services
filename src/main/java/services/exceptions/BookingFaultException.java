package services.exceptions;

import javax.xml.ws.WebFault;


@WebFault(name = "BookingNumberException")
public class BookingFaultException extends Exception {
    public BookingFaultException(String message) {
        super(message);
    }
}
