package services.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "BookingNumberException")
public class BookingNumberException extends Exception {
    public BookingNumberException(String message) {
        super(message);
    }
}
